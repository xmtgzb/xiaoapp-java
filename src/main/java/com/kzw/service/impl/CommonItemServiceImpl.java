package com.kzw.service.impl;


import com.kzw.Repo.CommonItemRepo;
import com.kzw.entity.CommonItemEO;
import com.kzw.service.CommonItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author panaidong
 * @version V1.0
 * @Title: UserServiceImpl
 * @Description:
 * @date
 */
@Service("commonItemService")
public class CommonItemServiceImpl implements CommonItemService {
    @Autowired
    private CommonItemRepo itemRepo;

    @Override
    public CommonItemEO getByCode(String parentCode) {
        return itemRepo.getByCode(parentCode);
    }
}
