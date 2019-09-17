package com.kzw.service.impl;


import com.kzw.Repo.UserRepo;
import com.kzw.entity.UserEO;
import com.kzw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author panaidong
 * @version V1.0
 * @Title: UserServiceImpl
 * @Description:
 * @date
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public void save(UserEO user) {
        String userCode = user.getUserCode();
        if (!StringUtils.isEmpty(userCode)){
            UserEO oldUser= userRepo.getByCode(userCode);
            if (oldUser==null){
                user.setUniqueFlag(UUID.randomUUID().toString());
                userRepo.save(user);
            }
        }

    }
}
