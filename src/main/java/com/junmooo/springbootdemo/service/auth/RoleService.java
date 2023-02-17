package com.junmooo.springbootdemo.service.auth;

import com.junmooo.springbootdemo.entity.auth.Role;

import java.util.List;

public interface RoleService {
    int addRole(Role role) throws Exception;

    int updateRole(Role role) throws Exception;

    List<Role> getRoles(Role role) throws Exception;
}
