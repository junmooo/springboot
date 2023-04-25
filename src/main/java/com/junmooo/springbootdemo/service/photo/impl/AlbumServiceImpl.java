package com.junmooo.springbootdemo.service.photo.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junmooo.springbootdemo.entity.photo.Album;
import com.junmooo.springbootdemo.entity.photo.AlbumVO;
import com.junmooo.springbootdemo.entity.photo.Photo;
import com.junmooo.springbootdemo.mapper.photo.AlbumMapper;
import com.junmooo.springbootdemo.mapper.photo.PhotoMapper;
import com.junmooo.springbootdemo.service.photo.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public int save(Album album) throws Exception {
        album.setTimeCreated(System.currentTimeMillis());
        return albumMapper.insert(album);
    }

    @Override
    public IPage<AlbumVO> queryByPage(String keyword, int curr, int size) throws Exception {
        IPage<Album> page = new Page<>(curr,size);
        QueryWrapper<Album> aqw = new QueryWrapper<>();
        aqw.orderByDesc("SORT_ORDER","TIME_CREATED");
        IPage<Album> albumPage = albumMapper.selectPage(page, aqw);
        List<Album> albums = albumPage.getRecords();
        List<AlbumVO> albumVOs = new ArrayList<AlbumVO>();
        for (int i = 0; i < albums.size(); i++) {
            int albumId = albums.get(i).getId();
            QueryWrapper<Photo> pqw = new QueryWrapper<>();
            pqw.eq("ALBUM",albumId);
            pqw.orderByDesc("SORT_ORDER","TIME_CREATED");
            List<Photo> photos = photoMapper.selectList(pqw);
            albumVOs.add(AlbumVO.builder().album(albums.get(i)).photos(photos).build());
        }
        return new Page<AlbumVO>(albumPage.getCurrent(),albumPage.getSize(),albumPage.getTotal()).setRecords(albumVOs);
    }

    @Override
    public List<AlbumVO> queryAll() throws Exception {
        QueryWrapper<Album> aqw = new QueryWrapper<>();
        aqw.eq("HIDE_FLAG","01");
        aqw.eq("DELETE_FLAG","01");
        aqw.orderByDesc("SORT_ORDER","TIME_CREATED");
        List<Album> albums = albumMapper.selectList(aqw);

        List<AlbumVO> albumVOs = new ArrayList<AlbumVO>();
        for (int i = 0; i < albums.size(); i++) {
            int albumId = albums.get(i).getId();
            QueryWrapper<Photo> pqw = new QueryWrapper<>();
            pqw.eq("ALBUM",albumId);
            pqw.eq("HIDE_FLAG","01");
            pqw.eq("DELETE_FLAG","01");
            pqw.orderByDesc("SORT_ORDER","TIME_CREATED");
            List<Photo> photos = photoMapper.selectList(pqw);
            albumVOs.add(AlbumVO.builder().album(albums.get(i)).photos(photos).build());
        }
        return albumVOs;
    }
    @Override
    public int hide(int id) throws Exception {
        Album album = Album.builder().id(id).hideFlag("00").build();
        return albumMapper.updateById(album);
    }

    @Override
    public int activate(int id) throws Exception {
        Album album = Album.builder().id(id).hideFlag("01").build();
        return albumMapper.updateById(album);
    }

    @Override
    public int update(Album album) throws Exception {
        return albumMapper.updateById(album);
    }

}
