package com.shisheng.service;

import com.shisheng.dao.HotWordsMapper;
import com.shisheng.entity.HotW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Magic on 2017/4/25.
 */
@Service
public class TimerTask {
    @Autowired
    HotWordsMapper hotWordsMapper;
    //0 0 0 * * ? *
    @Scheduled(cron ="0 0 0 * * ?")
    public void hotWordsSummary(){
        /*List<HotW> news = hotWordsMapper.getNewWords();
        hotWordsMapper.updateOldWords();
        if(news!=null&& news.size()!=0){
            hotWordsMapper.addNewWords(news);
        }
        hotWordsMapper.deleteAll();*/
    }
}
