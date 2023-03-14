package com.junmooo.springbootdemo.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junmooo.springbootdemo.entity.dict.Dict;
import com.junmooo.springbootdemo.mapper.dict.DictMapper;
import com.junmooo.springbootdemo.service.dict.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public String getLatestVersion(String type) throws Exception {
        QueryWrapper<Dict> qw = new QueryWrapper<>();
        if (type.equals("mac")) {
            qw.eq("LABEL", "app_version_mac");
        } else {
            qw.eq("LABEL", "app_version_win");
        }
        Dict dict = dictMapper.selectOne(qw);
        return dict.getVal();
    }
}
