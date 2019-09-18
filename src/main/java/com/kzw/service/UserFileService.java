package com.kzw.service;

import com.kzw.entity.UserFileEO;

import java.util.List;

public interface UserFileService {
    public void save(UserFileEO user);

    /**
     * 根据相册获取对外可见的图片缩略图地址
     * @param xiangce
     * @param isSee
     * @return
     */
    public List<UserFileEO> getSeeSlts(String xiangce,String isSee);
}
