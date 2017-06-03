package com.shisheng.util;

/**
 * Created by Magic on 2017/4/26.
 */
public class QueryPojo {
    private int[] states;
    private String search;
    private String shareType;
    private String typeId;
    private String patformId;
    private int offset;
    private int rows;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    private String order;

    public QueryPojo() {
        int[] a = {1,2};
        this.states= a;
        this.offset = 0;
        this.rows = 5;
        this.order = "a.share_time desc";
    }

    public String getPatformId() {
        return patformId;
    }

    public void setPatformId(String patformId) {
        this.patformId = patformId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int[] getStates() {
        return states;
    }

    public void setStates(int[] states) {
        this.states = states;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
