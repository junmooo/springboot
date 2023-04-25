package com.junmooo.springbootdemo.service.photo.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junmooo.springbootdemo.entity.photo.Photo;
import com.junmooo.springbootdemo.mapper.photo.PhotoMapper;
import com.junmooo.springbootdemo.service.photo.PhotoService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Value("${env.photo-dir-path}")
    private String DIRPATH;

    @Value("${env.photo-basket}")
    private String BASKET;
    @Autowired
    private PhotoMapper photoMapper;
    @Override
    public List<Photo> queryByAlbum(int albumId) throws Exception {
        QueryWrapper<Photo> qw = new QueryWrapper<>();
        qw.eq("ALBUM",albumId);
        qw.orderByDesc("SORT_ORDER");
        qw.orderByDesc("TIME_CREATED");

        return photoMapper.selectList(qw);
    }

    @Override
    public int delete(String photoId, String filename) throws Exception {
        QueryWrapper<Photo> qw = new QueryWrapper<>();
        File dir = new File(DIRPATH);
        int i = photoMapper.deleteById(photoId);
        Files.deleteIfExists(Paths.get(dir.getAbsolutePath() + "/" + filename));
        return i;
    }

    @Override
    public int save(Photo photo, MultipartFile uploadFile) throws Exception {

        File dir = new File(DIRPATH);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            FileUtils.forceMkdir(dir);
        }
        final String id = UUID.randomUUID().toString();
        String originalFilename = uploadFile.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String suffix = split.length > 0 ? split[split.length - 1] : "";
        String filename = id + "." + suffix;
        String preFilename = id + "-pre." + suffix;
        String url = BASKET + filename;
        File file = new File(dir.getAbsolutePath() + "/" + filename);
        //2，实现上传
//        FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), file);
        Thumbnails.of(uploadFile.getInputStream()).size(1920,1080).toFile(file);
        File preFile = new File(dir.getAbsolutePath() + "/" + preFilename);
        Thumbnails.of(uploadFile.getInputStream()).width(160).toFile(preFile);
        photo.setName(filename);
        photo.setId(id);
        photo.setUrl(url);
        photo.setOriginalName(originalFilename);
        photo.setTimeCreated(System.currentTimeMillis());
        return photoMapper.insert(photo);
    }
    @Override
    public int update(Photo photo) throws Exception {
        return photoMapper.updateById(photo);
    }
    @Override
    public int saveAll(Photo photo, MultipartFile[] files) throws Exception {
        return 0;
    }


}
