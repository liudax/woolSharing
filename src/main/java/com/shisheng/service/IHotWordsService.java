package com.shisheng.service;

import com.shisheng.entity.HotW;

import java.util.List;

/**
 * Created by Magic on 2017/4/27.
 */
public interface IHotWordsService {
    List<HotW> getAll();

    int addNewWords(String content);
}
