package com.junmooo.springbootdemo.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junmooo.springbootdemo.entity.auth.Role;
import com.junmooo.springbootdemo.mapper.auth.RoleMapper;
import com.junmooo.springbootdemo.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public int addRole(Role role) throws Exception {
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(Role role) throws Exception {
        return roleMapper.updateById(role);
    }

    @Override
    public List<Role> getRoles(Role role) throws Exception {
        QueryWrapper wrapper = new QueryWrapper<>(role);
        return roleMapper.selectList(wrapper);
    }
}
