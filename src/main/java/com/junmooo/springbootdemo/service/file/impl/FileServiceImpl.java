package com.junmooo.springbootdemo.service.file.impl;

import com.alibaba.fastjson.JSONArray;

import com.junmooo.springbootdemo.entity.file.FileEntity;
import com.junmooo.springbootdemo.mapper.file.FileMapper;
import com.junmooo.springbootdemo.service.file.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${env.dir-path}")
    private String DIRPATH;
    @Value("${env.basket}")
    private String BASKET;

    private static final String[] SUFFIXES = {"jpg", "jpeg", "png", "gif"};

    @Autowired
    private FileMapper fileMapper;

    @Override
    @Transactional
    public FileEntity save(MultipartFile uploadFile) throws IOException {
        File dir = new File(DIRPATH);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            FileUtils.forceMkdir(dir);
        }
        final String id = UUID.randomUUID().toString();
        String originalFilename = uploadFile.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String suffix = split.length > 0 ? split[split.length - 1] : "";
        String isImage = Arrays.binarySearch(SUFFIXES, suffix) == -1 ? "00" : "01";

        String filename = id + "." + suffix;
        String url = BASKET + filename;
        //2，实现上传
        FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), new File(dir.getAbsolutePath() + "/" + filename));
        FileEntity fileEntity = FileEntity.builder().id(UUID.randomUUID().toString()).url(url).originalFileName(originalFilename).deleteFlag("00").fileName(filename).isImage(isImage).timeCreated(System.currentTimeMillis()).build();

        int i = fileMapper.insert(fileEntity);
        if (i == 1) {
            return fileEntity;
        }
        throw new RuntimeException("sql 写入失败 id:" + id + "fileName: " + originalFilename);
    }

    @Override
    @Transactional
    public JSONArray saveAll(MultipartFile[] files) throws IOException {
        JSONArray res = new JSONArray();
        for (int i = 0; i < files.length; i++) {
            //2，实现上传
            res.add(save(files[i]));
        }
        return res;
    }

}
