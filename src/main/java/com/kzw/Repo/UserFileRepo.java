package com.kzw.Repo;

import com.kzw.VO.BaseQueryVO;
import com.kzw.entity.UserFileEO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserFileRepo {

     Page<UserFileEO> getSeeSlts(BaseQueryVO queryVo, Pageable page);

    void save(UserFileEO userFileEO);
}
