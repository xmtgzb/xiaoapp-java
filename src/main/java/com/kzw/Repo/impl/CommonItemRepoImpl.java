package com.kzw.Repo.impl;

import com.kzw.Repo.CommonItemRepo;
import com.kzw.entity.CommonItemEO;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
public class CommonItemRepoImpl extends BaseRepository<CommonItemEO> implements CommonItemRepo{
    @Override
    public CommonItemEO getByCode(String parentCode) {
        String hql="from CommonItemEO where 1=1 ";
        Map<String,Object> map =new HashMap<>();
        if (!StringUtils.isEmpty(parentCode)){
            hql+=" and parentCode =:parentCode ";
            map.put("parentCode",parentCode);
        }
        List<CommonItemEO> list = (List<CommonItemEO>)this.queryList(hql,map);
        if (list==null||list.size()==0){
           return  null;
        }
        return list.get(0);
    }
}
