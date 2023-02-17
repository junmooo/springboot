package com.junmooo.springbootdemo.service.auth;

import com.junmooo.springbootdemo.entity.auth.Resource;
import com.junmooo.springbootdemo.entity.auth.ResourceWrapper;

import java.util.List;

public interface ResourceService {
    List<Resource> getResourceList(Resource resource) throws Exception;

    List<ResourceWrapper> getAllResources() throws Exception;

    int addResource(Resource resource) throws Exception;

    int updateResource(Resource resource) throws Exception;

    int deleteWithRecursiveById(String resourceId) throws Exception;
}
