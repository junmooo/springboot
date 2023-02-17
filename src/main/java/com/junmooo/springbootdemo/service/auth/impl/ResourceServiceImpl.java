package com.junmooo.springbootdemo.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junmooo.springbootdemo.entity.auth.Resource;
import com.junmooo.springbootdemo.entity.auth.ResourceWrapper;
import com.junmooo.springbootdemo.mapper.auth.ResourceMapper;
import com.junmooo.springbootdemo.service.auth.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> getResourceList(Resource resource) {
        if (resource != null) {
            QueryWrapper wrapper = new QueryWrapper<>(resource);
            return resourceMapper.selectList(wrapper);
        }
        return resourceMapper.selectList(null);
    }

    @Override
    public ArrayList<ResourceWrapper> getAllResources() {
        ArrayList<Resource> resources = (ArrayList<Resource>) resourceMapper.selectList(null);
        final ArrayList<ResourceWrapper> out = new ArrayList<>();
        final ArrayList<Resource> in = new ArrayList<>();

        resources.forEach(i -> {
            if (i.getParentId().equals("0")) {
                out.add(new ResourceWrapper(i));
            } else {
                in.add(i);
            }
        });

        getTreeNode(in, out);
        System.out.println(out);
        return out;
    }

    private void getTreeNode(ArrayList<Resource> in, List<ResourceWrapper> out) {
        if (in.size() == 0) {
            return;
        }
        out.forEach(item -> {
            ArrayList<ResourceWrapper> newOut = new ArrayList<>();
            Iterator<Resource> iterator = in.iterator();
            while (iterator.hasNext()) {
                Resource i = iterator.next();
                if (i.getParentId().equals(item.getResourceId())) {
                    newOut.add(new ResourceWrapper(i));
                    iterator.remove();
                }
            }
            if (newOut.size() > 0) {
                item.setChildren(newOut);
                getTreeNode(in, newOut);
            }
        });
    }

    @Override
    public int addResource(Resource resource) {
        return resourceMapper.insert(resource);
    }

    @Override
    public int updateResource(Resource resource) {
        return resourceMapper.updateById(resource);
    }

    @Override
    public int deleteWithRecursiveById(String resourceId) {
        return resourceMapper.deleteWithRecursiveById(resourceId);
    }


}
