package com.shisheng.service.serviceImp;
import com.shisheng.dao.HotWordsMapper;
import com.shisheng.entity.HotW;
import com.shisheng.entity.HotWD;
import com.shisheng.service.IHotWordsService;
import com.shisheng.util.EntityIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Magic on 2017/4/27.
 */
@Service
public class HotWordsServiceService implements IHotWordsService {

    @Autowired
    HotWordsMapper dao;

    public List<HotW> getAll() {

        return dao.getAll();
    }


    public int addNewWords(String content) {
        HotWD words = new HotWD(EntityIDFactory.createId(),content);
        return dao.insert(words);
    }


}
