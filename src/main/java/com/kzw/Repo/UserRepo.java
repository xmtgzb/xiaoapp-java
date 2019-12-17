package com.kzw.Repo;

import com.kzw.Repo.impl.BaseRepository;
import com.kzw.entity.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepo {

    public UserEO getByCode(String userCode);
    void save(UserEO userEO);
}
