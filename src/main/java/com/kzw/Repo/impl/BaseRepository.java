package com.kzw.Repo.impl;

import org.hibernate.SessionFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShadowSaint
 * @Date:19-4-4 上午3:50
 * @Description: TODO
 */
@Repository
public class BaseRepository<E extends Serializable>{
    @PersistenceContext
    private EntityManager em;
    private static SessionFactory sf;
    protected Class<E> entityClass;

    public BaseRepository(){
        if (getClass().getGenericSuperclass() instanceof ParameterizedType){
            if (!(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0] instanceof TypeVariable)){
                    entityClass=(Class<E>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            }
        }
    }
    public E find(Object id){
        return em.find(entityClass,id);
    }
    @Transactional
    public void flush(){
            em.flush();
    }
    @Transactional
    public void clear(){
        em.flush();
        em.clear();
    }
    @Transactional
    public void detach(E entity){
        em.detach(entity);
    }

    @Transactional
    public void save(E entity){
        if (!em.contains(entity)){
            em.merge(entity);
        }
    }
    @Transactional
    public void delete(E entity){
        em.remove(em.contains(entity)?entity:em.merge(entity));
    }
    @Transactional
    public void delete(Object id){
        delete(find(id));
    }
 public List<?> queryList(String hql,Map<String,Object> map){
     Query query = em.createQuery(hql, entityClass);
     if (query!=null&&map!=null&&map.keySet().size()>0){
            for (Map.Entry<String,Object> entry:
            map.entrySet()) {
                query.setParameter(entry.getKey(),entry.getValue());
            }
            return query.getResultList();
        }
        return null;
 }
    public Page<E> queryPageList(String hql,Map<String,Object> map,Pageable pageable){

        if (pageable.getOffset()>=0&&pageable.getPageSize()>0){
            String hql2 =hql;
            if (hql.toLowerCase().indexOf("order by")==-1){
                Sort sort =pageable.getSort();
                if (sort!=null){
                    hql2+="order by "+sort.toString().replace(":","");
                }
            }
            long count = this.findCount(hql, map);
            Query query = em.createQuery(hql2, entityClass);
            for (Map.Entry<String,Object> entry:
                    map.entrySet()) {
                query.setParameter(entry.getKey(),entry.getValue());
            }

            query.setFirstResult((int)pageable.getOffset()).setMaxResults(pageable.getPageSize());
            List list = query.getResultList();
            return new PageImpl<E>(list,pageable,count);
        }
        return null;
    }
    private long findCount(String hql,Map<String,Object> map){
        hql="select count(*) "+hql;
        Query query = em.createQuery(hql, Long.class);
        for (Map.Entry<String,Object> entry:
                map.entrySet()) {
            query.setParameter(entry.getKey(),entry.getValue());
        }
        Object obj = query.getSingleResult();
        if (obj!=null){
            return Long.valueOf(obj+"");
        }
        return 0L;
    }

}