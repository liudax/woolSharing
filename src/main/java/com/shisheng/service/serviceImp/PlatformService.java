package com.shisheng.service.serviceImp;

import com.shisheng.dao.PlatformMapper;
import com.shisheng.entity.Platform;
import com.shisheng.service.IPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Magic on 2017/4/25.
 */
@Service
public class PlatformService implements IPlatformService {

    @Autowired
    PlatformMapper dao;

    public List<Platform> getPlatformList() {

        return  dao.getPlatformList();
    }

    public Platform getPlatformDetail(String id) {
        return dao.getPlatformDetail(id);
    }
}
