package com.junmooo.springbootdemo.service.file;

import com.alibaba.fastjson.JSONArray;
import com.junmooo.springbootdemo.entity.file.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileEntity save(MultipartFile uploadFile, String operId) throws IOException;

    JSONArray saveAll(MultipartFile[] files, String token) throws IOException;
}
