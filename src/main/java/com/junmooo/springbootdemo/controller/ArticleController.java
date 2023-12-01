package com.junmooo.springbootdemo.controller;
import com.alibaba.fastjson.JSONObject;
import com.junmooo.springbootdemo.common.constant.ErrorCode;
import com.junmooo.springbootdemo.entity.atical.Article;
import com.junmooo.springbootdemo.entity.atical.ArticleTree;
import com.junmooo.springbootdemo.entity.token.UserToken;
import com.junmooo.springbootdemo.entity.vo.CommonResponse;
import com.junmooo.springbootdemo.service.article.ArticleService;
import com.junmooo.springbootdemo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("article")
@CrossOrigin(origins = "*")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //单个文件的上传
    @PostMapping("/save")
    public JSONObject save(@RequestBody Article article, HttpServletRequest request) {
        UserToken userToken = (UserToken) request.getAttribute("userToken");
        if (userToken == null){
            try {
                userToken = TokenUtils.getInfoFromUserToken(request.getHeader("token"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return CommonResponse.success(articleService.save(article, userToken));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "save 失败");
    }

    @PostMapping("/saveTree")
    public JSONObject saveTree(@RequestBody ArticleTree tree, HttpServletRequest request) {
        UserToken userToken = (UserToken) request.getAttribute("userToken");
        try {
            return CommonResponse.success(articleService.saveTree(tree, userToken));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "save 失败");
    }

    @GetMapping("/getTreeByUid")
    public JSONObject getTreeByUid(@RequestParam String uid) {
        try {
            ArticleTree tree = articleService.getTreeByUid(uid);
            return CommonResponse.success(tree);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "save 失败");
    }

    @GetMapping("/all")
    @CrossOrigin
    public JSONObject all() {
        try {
            return CommonResponse.success(articleService.all());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "save 失败");
    }

    @GetMapping("/search")
    @CrossOrigin
    public JSONObject search(@RequestParam String type, @RequestParam String regex,@RequestParam int curr,@RequestParam int size) {
        try {
            return CommonResponse.success(articleService.search(type,regex,curr,size));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "save 失败");
    }

    @GetMapping("/getArticleById")
    @CrossOrigin
    public JSONObject getArticleById(@RequestParam String id) {
        try {
            return CommonResponse.success(articleService.getArticleById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "获取 失败");
    }

    @GetMapping("/del")
    public JSONObject del(@RequestParam String id) {
        try {
            if (articleService.del(id) == 1) {
                return CommonResponse.success("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.fail(ErrorCode.WRONGTOKEN, "del 失败");
    }
}
