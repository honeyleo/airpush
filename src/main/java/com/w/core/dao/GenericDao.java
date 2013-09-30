package com.w.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.w.core.model.Criteria;

public interface GenericDao<T> {

	int count(T entity);
	
	List<T> getEntity(T entity);
	
	T getEntityById(long id);
	
	int save(T entity);
	
	int update(T entity);
	
	int delete(@Param("ids") List<String> ids);
	
	int delete(@Param("id") long id);
	
	int countByCriteria(Criteria criteria);
	
	List<T> findListByCriteria(Criteria criteria);
}
