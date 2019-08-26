package com.kzw.Repo;

import com.kzw.entity.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends BaseRepository<UserEO,Long>{

    @Query(value = "SELECT u FROM UserEO u WHERE u.userCode=:userCode")
    public UserEO getByCode(@Param("userCode")String userCode);

}
