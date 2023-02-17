package com.junmooo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.common.exception.AdminException;
import com.junmooo.springbootdemo.entity.auth.Operator;
import com.junmooo.springbootdemo.entity.auth.Resource;
import com.junmooo.springbootdemo.entity.auth.ResourceWrapper;
import com.junmooo.springbootdemo.entity.auth.Role;
import com.junmooo.springbootdemo.entity.token.OperToken;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.auth.ResourceService;
import com.junmooo.springbootdemo.service.auth.RoleService;
import com.junmooo.springbootdemo.service.auth.UserService;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    RoleService roleService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("login")
    public JSONObject login(@RequestBody Operator operator) {
        try {
            Operator retOperator = userService.getOperatorByOpername(operator);

            if (retOperator == null) {
                return CommonResponse.fail(ErrorCode.LOGINFAIL, "用户名或密码错误");
            }
            if (retOperator.getDeleteFlag().equals("0")) {
                return CommonResponse.fail(ErrorCode.LOGINFAIL, "用户已注销");
            }
            if (!operator.getOperPwd().equals(retOperator.getOperPwd())) {
                return CommonResponse.fail(ErrorCode.LOGINFAIL, "用户名或密码错误");
            }

            String token = TokenUtils.generateToken(new OperToken(operator.getOperId(), operator.getOperName(), operator.getOperEmail()), 60);
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            opsForValue.set(token, "1", 10, TimeUnit.MINUTES);
            JSONObject res = new JSONObject();
            res.put("token", token);
            return CommonResponse.success(res);
        } catch (AdminException e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.WRONGTOKEN, "token 失效");
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.fail(ErrorCode.SQLERR, "登陆异常");
        }
    }

    @PostMapping("register")
    public JSONObject register(@RequestBody Operator operator) {
        try {
            Operator operator1 = userService.addOperator(operator);
            JSONObject res = CommonResponse.success(operator1);
            return res;
        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "user insert err");
        }
    }

    @PostMapping("operList")
    public JSONObject operList(@RequestBody Operator operator) {

        try {
            List<Operator> operators = userService.getOperList(operator);
            JSONObject res = CommonResponse.success(operators);
            return res;
        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "user query err");
        }
    }

    @PostMapping("resourceList")
    public JSONObject resourceList(@RequestBody Resource resource) {
        try {
            List<Resource> resources = resourceService.getResourceList(resource);
            JSONObject res = CommonResponse.success(resources);
            return res;
        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "resource query err");
        }
    }

    @PostMapping("allResources")
    public JSONObject allResources() {
        try {
            List<ResourceWrapper> resources = resourceService.getAllResources();
            JSONObject res = CommonResponse.success(resources);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.SQLERR, "resource query err");
        }
    }

    @PostMapping("addResource")
    public JSONObject addResource(@RequestBody Resource resource) {
        try {
            resource.setTimeCreated(new Date().getTime());
            resource.setDeleteFlag("1");
            int i = resourceService.addResource(resource);
            return CommonResponse.success(i);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.SQLERR, "resource insert err");
        }
    }

    @PostMapping("addRole")
    public JSONObject addRole(@RequestBody Role role) {
        try {
            role.setTimeCreated(new Date().getTime());
            role.setDeleteFlag("1");
            int i = roleService.addRole(role);
            return CommonResponse.success(i);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.SQLERR, "Role insert err");
        }
    }


    @PostMapping("updateRole")
    public JSONObject updateRole(@RequestBody Role role) {
        try {
            role.setTimeUpdated(new Date().getTime());
            int i = roleService.updateRole(role);
            return CommonResponse.success(i);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.SQLERR, "Role update err");
        }
    }

    @PostMapping("getRoles")
    public JSONObject getRoles(@RequestBody Role role) {
        try {
            List<Role> roles = roleService.getRoles(role);
            return CommonResponse.success(roles);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(ErrorCode.SQLERR, "Role update err");
        }
    }

    @RequestMapping("delOper")
    public JSONObject delOper(@Param("operId") String operId) {
        try {
            int i = userService.delOper(operId);
            if (i == 1) {
                return CommonResponse.success(null);
            }
            return CommonResponse.fail(ErrorCode.SQLERR, "del fail");

        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "user del err");
        }
    }


    @RequestMapping("delResource")
    public JSONObject delResource(@Param("resourceId") String resourceId) {
        try {
            if (resourceService.deleteWithRecursiveById(resourceId) >= 1) {
                return CommonResponse.success(null);
            }
            return CommonResponse.fail(ErrorCode.SQLERR, "del fail");
        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "Resource del err");
        }
    }

    @PostMapping("updateOper")
    public JSONObject updateOper(@RequestBody Operator operator) {
        try {
            int i = userService.updateOpers(operator);
            if (i >= 0) {
                return CommonResponse.success(null);
            }
            return CommonResponse.fail(ErrorCode.SQLERR, "update fail");

        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "user update err");
        }
    }

    @PostMapping("updateResource")
    public JSONObject updateResource(@RequestBody Resource resource) {
        try {
            resource.setTimeUpdated(new Date().getTime());
            int i = resourceService.updateResource(resource);
            if (i > 0) {
                return CommonResponse.success(null);
            }
            return CommonResponse.fail(ErrorCode.SQLERR, "update fail");

        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SQLERR, "resource update err");
        }
    }
}
