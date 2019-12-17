package com.kzw.Repo.impl;

import com.kzw.Repo.UserRepo;
import com.kzw.entity.UserEO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
@Repository
public class UserRepoImpl extends BaseRepository<UserEO> implements UserRepo{
    @Override
    public UserEO getByCode(String userCode) {
        String hql=" select * from UserEO where userCode=:userCode ";
        Map<String,Object> map= new HashMap<>();
        map.put("userCode",userCode);
        List<UserEO> list = (List<UserEO>)this.queryList(hql, map);
        if (list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }
}
