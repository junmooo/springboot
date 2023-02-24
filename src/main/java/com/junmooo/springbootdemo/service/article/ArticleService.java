package com.junmooo.springbootdemo.service.article;

import com.junmooo.springbootdemo.entity.atical.Article;
import com.junmooo.springbootdemo.entity.token.OperToken;

import java.util.List;

public interface ArticleService {
    String save(Article article, OperToken author) throws Exception;

    List<Article> all() throws Exception;
}
