package com.kzw.Repo;

import com.kzw.entity.UserFileEO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFileRepo extends BaseRepository<UserFileEO,Long>{

    @Query(value = "SELECT u FROM UserFileEO u WHERE u.isSee=:isSee and u.xiangCe=:xiangCe")
    public List<UserFileEO> getSeeSlts(@Param(value="xiangCe")String xiangce,@Param(value="isSee")String isSee);

}
