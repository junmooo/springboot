package com.junmooo.springbootdemo.service.article.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junmooo.springbootdemo.entity.atical.Article;
import com.junmooo.springbootdemo.entity.token.UserToken;
import com.junmooo.springbootdemo.mapper.article.ArticleMapper;
import com.junmooo.springbootdemo.service.article.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public String save(Article article, UserToken author) throws Exception {
        if (StringUtils.isNotEmpty(article.getId())) {
            article.setTimeUpdated(System.currentTimeMillis());
            if (articleMapper.updateById(article) == 1) {
                return article.getId();
            }
        } else {
            article.setId(UUID.randomUUID().toString());
            //软删除标志：00-已删除，01-未删除
            article.setDeleteFlag("01");
//            article.setAuthorId(author.getId());
//            article.setAuthorName(author.getName());
            article.setTimeCreated(System.currentTimeMillis());
            article.setType("00");
            if (articleMapper.insert(article) == 1) {
                return article.getId();
            }
        }
        throw new RuntimeException("article 写入失败");
    }

    @Override
    public List<Article> all() throws Exception {
        Article article = Article.builder().deleteFlag("01").build();
        QueryWrapper qw = new QueryWrapper(article);
        qw.orderByDesc("TIME_CREATED");
        return articleMapper.selectList(qw);
    }

    @Override
    public int del(String id) throws Exception {
        Article article = Article.builder().id(id).deleteFlag("00").build();
        return articleMapper.updateById(article);
    }
}
