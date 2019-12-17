package com.kzw.service;

import com.kzw.VO.BaseQueryVO;
import com.kzw.entity.UserFileEO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserFileService {
    public void save(UserFileEO user);

    /**
     *
     * @param queryVo
     * @param page
     * @return
     */
    public Page<UserFileEO> getSeeSlts(BaseQueryVO queryVo, Pageable page);
}
