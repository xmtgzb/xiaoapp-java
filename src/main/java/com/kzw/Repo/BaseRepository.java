package com.kzw.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;


/**
 * @Author:ShadowSaint
 * @Date:19-4-4 上午3:45
 * @Description: 框架通用的基础repository接口
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
        extends
        JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>,
        PagingAndSortingRepository<T, ID> {


    <S extends T> Iterable<S> batchSave(Iterable<S> var1);

    <S extends T> Iterable<S> batchUpdate(Iterable<S> var1);

}

