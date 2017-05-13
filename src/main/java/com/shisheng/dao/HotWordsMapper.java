package com.shisheng.dao;

import com.shisheng.entity.HotW;
import com.shisheng.entity.HotWD;

import java.util.List;

public interface HotWordsMapper {

    int insert(HotWD hotWD);

    List<HotW> getAll();

    List<HotW> getNewWords();

    int addNewWords(List<HotW> news);

    int updateOldWords();

    int deleteAll();

}