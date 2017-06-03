package com.shisheng.service;

import com.shisheng.dao.CommodityMapper;
import com.shisheng.dao.HotWordsMapper;
import com.shisheng.dao.PlatformMapper;
import com.shisheng.entity.HotW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created by Magic on 2017/4/25.
 */
@Service
public class TimerTask {
    @Autowired
    HotWordsMapper hotWordsMapper;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    PlatformMapper platformMapper;

    //0 0 0 * * ? *  每天凌晨执行
    @Scheduled(cron ="0 0 0 * * ?")
    public void everyDay(){
        statisticalHotWords();//热词统计
        clearPic();//图片清理
    }



    public void clearPic(){
        List<String> imageAddr1 = commodityMapper.getImageAddrs();
        List<String> imageAddr2 = platformMapper.getImageAddrs();
        imageAddr1.addAll(imageAddr2);
        File imageDir = new File("E:\\CommodityPicture");
        File[] files = imageDir.listFiles();
        try {
            for (File file:files) {
                System.out.println(file.getName()+" " +imageAddr1.contains(file.getName()));
                if(!imageAddr1.contains(file.getName()) && file.isFile()){
                    System.out.println(file.getName()+"删除了");
                    file.delete();
                }
            }
        }catch(Exception e){

        }

    }


    public void statisticalHotWords(){
        List<HotW> news = hotWordsMapper.getNewWords();
        hotWordsMapper.updateOldWords();
        if(news!=null&& news.size()!=0){
            hotWordsMapper.addNewWords(news);
        }
        hotWordsMapper.deleteAll();
    }
}
