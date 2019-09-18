package com.kzw.service.impl;

import com.kzw.Repo.UserFileRepo;
import com.kzw.entity.UserFileEO;
import com.kzw.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
@Service
public class UserFileServiceImpl implements UserFileService{
    @Autowired
    private UserFileRepo userFileRepo;
    @Override
    public void save(UserFileEO userFileEO) {
        userFileRepo.save(userFileEO);
    }

    @Override
    public List<UserFileEO> getSeeSlts(String xiangce, String isSee) {
        List<UserFileEO> files = userFileRepo.getSeeSlts(xiangce, isSee);
        return files;
    }
}
