package com.shisheng.entity;

import java.util.Date;

/**
 * Created by Magic on 2017/5/14.
 */
public class CollectionList {

    private String commodityId;

    private String UserId;

    private Date collectTime;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}
