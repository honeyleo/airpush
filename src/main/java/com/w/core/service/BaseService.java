package com.w.core.service;

import java.util.List;

import com.w.core.model.Criteria;
import com.w.core.model.PageInfo;


public interface BaseService<T> {

	int count(T entity);
	
	List<T> getEntity(T entity);
	
	T getEntityById(Long id) throws Exception;
	
	int save(T entity) throws Exception;
	
	int update(T entity);
	
	int delete(List<String> ids);
	
	int delete(long id);
	
	int countByCriteria(Criteria criteria);
	
	List<T> findListByCriteria(Criteria criteria);
	
	PageInfo<T> findListByCriteria(Criteria criteria, int pageNo,
			int pageSize);
}
