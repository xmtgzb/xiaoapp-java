package com.kzw.service.impl;

import com.kzw.Repo.UserFileRepo;
import com.kzw.VO.BaseQueryVO;
import com.kzw.entity.UserFileEO;
import com.kzw.service.UserFileService;
import com.kzw.web.FileController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
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
    private static final Logger log = LoggerFactory.getLogger(UserFileServiceImpl.class);
    @Autowired
    private UserFileRepo userFileRepo;
    @Override
    public void save(UserFileEO userFileEO) {
        userFileRepo.save(userFileEO);
    }

    @Override
    public void delete(BaseQueryVO queryVo) {
        //根据userCode和Id查询
        UserFileEO eo=userFileRepo.findOne(queryVo);

        if (eo==null){
            log.error("根据userCode["+queryVo.getUserCode()+"]和ID["+queryVo.getId()+"]查询不到对应的数据");
        }
        //删除硬盘的数据
        String realFile=eo.getRealPath()+""+eo.getFileName();
        String sltFile=eo.getSltPath()+""+eo.getSltName();
        //删除缩略图
        if(!new File(sltFile).delete()){
            log.error("删除文件["+sltFile+"]失败");
        }
        //删除原图
        if(!new File(realFile).delete()){
            log.error("删除文件["+realFile+"]失败");
        }
        //删除数据库的数据
        userFileRepo.delete(eo);
    }

    @Override
    public Page<UserFileEO> getSeeSlts(BaseQueryVO queryVo, Pageable page) {
        return userFileRepo.getSeeSlts(queryVo,page);
    }


}
