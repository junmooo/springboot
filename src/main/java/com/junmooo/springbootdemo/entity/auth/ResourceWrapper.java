package com.junmooo.springbootdemo.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceWrapper {
    public ResourceWrapper(Resource resource) {
        this.resourceId = resource.getResourceId();
        this.resourceName = resource.getResourceName();
        this.parentId = resource.getParentId();
        this.resourcePath = resource.getResourcePath();
        this.menuFlag = resource.getMenuFlag();
        this.hasChild = resource.getHasChild();
        this.sortNum = resource.getSortNum();
        this.resourceCode = resource.getResourceCode();
        this.resourceUrl = resource.getResourceUrl();
        this.remark = resource.getRemark();
        this.deleteFlag = resource.getDeleteFlag();
        this.timeCreated = resource.getTimeCreated();
        this.timeUpdated = resource.getTimeUpdated();
    }

    private String resourceId;
    private String resourceName;
    private String parentId;
    private String resourcePath;
    private String menuFlag;
    private String hasChild;
    private int sortNum;
    private String resourceCode;
    private String resourceUrl;
    private String remark;
    private String deleteFlag;
    private Long timeCreated;
    private Long timeUpdated;

    private ArrayList<ResourceWrapper> children;

    private void addChildren(ResourceWrapper wrapper) {
        this.children.add(wrapper);
    }
}
