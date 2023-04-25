package com.junmooo.springbootdemo.service.photo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.junmooo.springbootdemo.entity.photo.Album;
import com.junmooo.springbootdemo.entity.photo.AlbumVO;

import java.util.List;

public interface AlbumService {
    int save(Album album) throws Exception;

    IPage<AlbumVO> queryByPage(String keyword, int curr, int size) throws Exception;

    int hide(int id) throws Exception;

    int activate(int id) throws Exception;;

    int update(Album album)throws Exception;

    List<AlbumVO> queryAll() throws Exception;;
}
