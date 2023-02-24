package com.junmooo.springbootdemo.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.junmooo.springbootdemo.entity.User;
import com.junmooo.springbootdemo.entity.auth.Operator;
import com.junmooo.springbootdemo.mapper.auth.OperatorMapper;
import com.junmooo.springbootdemo.mapper.auth.UserMapper;
import com.junmooo.springbootdemo.service.auth.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OperatorMapper operatorMapper;


    public List<User> getUserList() {
        ArrayList res = new ArrayList();
        List<User> users = userMapper.selectList(null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "rachel");
        List<User> user1 = userMapper.selectByMap(map);
        System.out.println(user1);
        return users;
    }

    public User getUserByUsername(User user) throws Exception {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        User retUser = userMapper.selectOne(wrapper);
        if (retUser.getDel() == 0) {
            return retUser;
        }
        throw new Exception();
    }

    @Override
    public Operator getOperatorByOpername(String operName) throws Exception {
        QueryWrapper<Operator> wrapper = new QueryWrapper<>();
        wrapper.eq("OPER_NAME", operName);
        try {
            return operatorMapper.selectOne(wrapper);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Operator> getOperList(Operator operator) throws Exception {
        QueryWrapper<Operator> wrapper = new QueryWrapper<>();
        String operEmail = operator.getOperEmail();
        String operName = operator.getOperName();
        String operStatus = operator.getOperStatus();
        String deleteFlag = operator.getDeleteFlag();
        String phoneNo = operator.getPhoneNo();
        String remark = operator.getRemark();
        assembleNotNull(wrapper,"OPER_EMAIL",operEmail);
        assembleNotNull(wrapper,"OPER_NAME",operName);
        assembleNotNull(wrapper,"OPER_STATUS",operStatus);
        assembleNotNull(wrapper,"DELETE_FLAG",deleteFlag);
        assembleNotNull(wrapper,"PHONE_NO",phoneNo);
        assembleNotNull(wrapper,"remark",remark);
        List<Operator> operators = operatorMapper.selectList(wrapper);
        return operators;
    }

    @Override
    public int delOper(String operId) throws Exception {
        QueryWrapper<Operator> wrapper = new QueryWrapper<>();
        wrapper.eq("OPER_ID", operId);
        int i = operatorMapper.delete(wrapper);
        return i;
    }

    private QueryWrapper assembleNotNull(QueryWrapper wrapper, String eqKey, String eqVal){
        if (StringUtils.isNotEmpty(eqVal)){
            wrapper.eq(eqKey, eqVal);
        }
        return wrapper;
    }
    @Override
    public int updateOpers(Operator operator) throws Exception {
        UpdateWrapper<Operator> wrapper = new UpdateWrapper<>();
        String id = operator.getOperId();
        wrapper.eq("OPER_ID",id);
        int i = operatorMapper.update(operator,wrapper);
        return i;
    }

    public User addUser(User user) throws Exception {
        user.setCreateTime(Calendar.getInstance().getTimeInMillis() + "");
        int insert = userMapper.insert(user);
        if (insert == 1) {
            return user;
        }
        throw new Exception("user insert failed");
    }

    public Operator addOperator(Operator operator) throws Exception {
        operator.setTimeCreated(new Date(Calendar.getInstance().getTimeInMillis()));
        operator.setOperId(UUID.randomUUID().toString());
        int insert = operatorMapper.insert(operator);
        if (insert == 1) {
            return operator;
        }
        throw new Exception("user insert failed");
    }
}
