package com.junmooo.springbootdemo.service.article;

import com.junmooo.springbootdemo.entity.atical.Article;
import com.junmooo.springbootdemo.entity.token.UserToken;

import java.util.List;

public interface ArticleService {
    String save(Article article, UserToken author) throws Exception;

    List<Article> all() throws Exception;

    int del(String id) throws Exception;
}
