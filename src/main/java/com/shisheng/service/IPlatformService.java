package com.shisheng.service;

import com.shisheng.entity.Platform;

import java.util.List;

/**
 * Created by Magic on 2017/4/25.
 */
public interface IPlatformService {

     List<Platform> getPlatformList();

     Platform getPlatformDetail(String id);

}
