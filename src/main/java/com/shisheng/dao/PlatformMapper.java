package com.shisheng.dao;

import com.shisheng.entity.Platform;

import java.util.List;

public interface PlatformMapper {

    int addPlatform(Platform platform);

    int updatePlatform(Platform platform);

    int deletePlatformById(String id);

    List<Platform> getPlatformList();

    Platform getPlatformDetail(String id);

    List<String> getImageAddrs();
}