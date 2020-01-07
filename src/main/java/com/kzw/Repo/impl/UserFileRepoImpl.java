package com.kzw.Repo.impl;

import com.kzw.Repo.UserFileRepo;
import com.kzw.VO.BaseQueryVO;
import com.kzw.entity.UserFileEO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class UserFileRepoImpl extends BaseRepository<UserFileEO> implements UserFileRepo{
    @Override
    public Page<UserFileEO> getSeeSlts(BaseQueryVO queryVo, Pageable page) {
        String hql ="from UserFileEO where 1=1 ";
        Map<String,Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(queryVo.getIsSee())){
            hql +=" and isSee=:isSee ";
            map.put("isSee",queryVo.getIsSee());
        }
        if (!StringUtils.isEmpty(queryVo.getXiangCe())){
            hql +=" and xiangCe=:xiangCe ";
            map.put("xiangCe",queryVo.getXiangCe());
        }
        if (!StringUtils.isEmpty(queryVo.getUserCode())){
            hql +=" and userCode=:userCode ";

            map.put("userCode",queryVo.getUserCode());
        }
        return this.queryPageList(hql,map,page);
    }

    @Override
    public UserFileEO findOne(BaseQueryVO queryVo) {
        String hql ="from UserFileEO where id=:id and userCode=:userCode ";
        Map<String,Object> map = new HashMap<>();
        map.put("id",queryVo.getId());
        map.put("userCode",queryVo.getUserCode());
        List<UserFileEO> list =(List<UserFileEO>)this.queryList(hql,map);
        if (list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }
}
