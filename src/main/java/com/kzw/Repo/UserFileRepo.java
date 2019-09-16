package com.kzw.Repo;

import com.kzw.entity.UserFileEO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFileRepo extends BaseRepository<UserFileEO,Long>{

    @Query(value = "SELECT u FROM UserFileEO u WHERE u.isSee='1'")
    public List<UserFileEO> getDuiWai();

}
