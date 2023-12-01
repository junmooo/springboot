package com.junmooo.springbootdemo.service.article.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junmooo.springbootdemo.entity.atical.Article;
import com.junmooo.springbootdemo.entity.atical.ArticleTree;
import com.junmooo.springbootdemo.entity.token.UserToken;
import com.junmooo.springbootdemo.mapper.article.ArticleMapper;
import com.junmooo.springbootdemo.mapper.article.ArticleTreeMapper;
import com.junmooo.springbootdemo.mapper.user.UserMapper;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleTreeMapper treeMapper;

    @Override
    public ArticleTree saveTree(ArticleTree tree, UserToken userToken) throws Exception {
        if (StringUtils.isNotEmpty(tree.getId())) {
            tree.setTimeUpdated(System.currentTimeMillis());
            if (treeMapper.updateById(tree) == 1) {
                return tree;
            }
        } else {
            tree.setId(UUID.randomUUID().toString());
            //软删除标志：00-已删除，01-未删除
            tree.setDeleteFlag("01");
            if (StringUtils.isEmpty(tree.getOwnerId())) tree.setOwnerId(userToken.getId());
            tree.setTimeCreated(System.currentTimeMillis());
            if (treeMapper.insert(tree) == 1) {
                return tree;
            }
        }
        throw new RuntimeException("article 写入失败");
    }


    @Override
    public String save(Article article, UserToken author) throws Exception {

        if (StringUtils.isNotEmpty(article.getId())) {
            article.setTimeUpdated(System.currentTimeMillis());
            if (articleMapper.updateById(article) == 1) {
                return article.getId();
            }
        } else {
            article.setId(UUID.randomUUID().toString());
            if (StringUtils.isEmpty(article.getAuthorId())) article.setAuthorId(author.getId());
            if (StringUtils.isEmpty(article.getAuthorName())) article.setAuthorName(author.getName());
            if (StringUtils.isEmpty(article.getAuthorAvatar())) article.setAuthorAvatar(author.getAvatar());
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

    @Override
    public Article getArticleById(String id) throws Exception {
        return articleMapper.selectById(id);
    }

    @Override
    public ArticleTree getTreeByUid(String uid) throws Exception {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("OWNER_ID",uid);
        return treeMapper.selectOne(qw);
    }

    @Override
    public IPage<Article> search(String type, String regex,int curr,int size) throws Exception {
        IPage<Article> page = new Page<>(curr,size);
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.eq("TYPE","01");
        qw.eq("DELETE_FLAG","01");
        if (type.equals("title")){
            qw.like("TITLE",regex);
        }else {
            qw.like("ARTICLE",regex);
        }
        qw.orderByDesc("TIME_CREATED");
        return articleMapper.selectPage(page,qw);
    }

}
