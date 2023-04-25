package com.junmooo.springbootdemo.service.photo;

import com.junmooo.springbootdemo.entity.photo.Album;
import com.junmooo.springbootdemo.entity.photo.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    List<Photo> queryByAlbum(int albumId) throws Exception;

    int delete(String photoId, String filename) throws Exception;

    int save(Photo photo, MultipartFile uploadFile)throws Exception;

    int saveAll(Photo photo, MultipartFile[] files)throws Exception;

    int update(Photo photo) throws Exception;
}
