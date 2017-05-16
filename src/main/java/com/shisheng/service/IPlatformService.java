package com.shisheng.service;

import com.shisheng.entity.Platform;

import java.util.List;

/**
 * Created by Magic on 2017/4/25.
 */
public interface IPlatformService {

     int addPlatform(Platform platform);

     int updatePlatform(Platform platform);

     int deletePlatformById(String id);

     List<Platform> getPlatformList();

     Platform getPlatformDetail(String id);

}
