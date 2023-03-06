package com.junmooo.springbootdemo.service.article;

import com.junmooo.springbootdemo.entity.atical.Article;
import com.junmooo.springbootdemo.entity.atical.ArticleTree;
import com.junmooo.springbootdemo.entity.token.UserToken;

import java.util.List;

public interface ArticleService {
    String save(Article article, UserToken author) throws Exception;

    List<Article> all() throws Exception;

    int del(String id) throws Exception;

    ArticleTree saveTree(ArticleTree folder, UserToken userToken) throws Exception;

    Article getArticleById(String id) throws Exception;

    ArticleTree getTreeByUid(String uid) throws Exception;
}
