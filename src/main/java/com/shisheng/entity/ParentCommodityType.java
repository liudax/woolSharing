package com.shisheng.entity;

import java.util.List;

public class ParentCommodityType {
    private String id;

    private String typeName;

    public List<ChildCommodityType> getChildren() {
        return children;
    }

    public void setChildren(List<ChildCommodityType> children) {
        this.children = children;
    }

    private List<ChildCommodityType> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

}