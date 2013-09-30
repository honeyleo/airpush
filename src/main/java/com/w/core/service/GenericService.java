package com.w.core.service;

import java.util.List;

import com.w.core.dao.GenericDao;
import com.w.core.model.Criteria;
import com.w.core.model.PageInfo;


public abstract class GenericService<T> implements BaseService<T>{

	@Override
	public int count(T entity) {
		return getGenericDao().count(entity);
	}

	@Override
	public List<T> getEntity(T entity) {
		return getGenericDao().getEntity(entity);
	}

	@Override
	public T getEntityById(Long id) throws Exception {
		return getGenericDao().getEntityById(id);
	}

	@Override
	public int save(T entity) throws Exception {
		return getGenericDao().save(entity);
	}

	@Override
	public int update(T entity) {
		return getGenericDao().update(entity);
	}

	@Override
	public int delete(List<String> ids) {
		return getGenericDao().delete(ids);
	}

	@Override
	public int delete(long id) {
		return getGenericDao().delete(id);
	}
	
	public GenericDao<T> getGenericDao() {
		return null;
	}

	@Override
	public int countByCriteria(Criteria criteria) {
		return getGenericDao().countByCriteria(criteria);
	}

	@Override
	public List<T> findListByCriteria(Criteria criteria) {
		return getGenericDao().findListByCriteria(criteria);
	}
	
	@Override
	public PageInfo<T> findListByCriteria(Criteria criteria, int pageNo,
			int pageSize) {
		PageInfo<T> res = new PageInfo<T>(pageNo, pageSize);
        int total=this.countByCriteria(criteria);
        res.setTotal(total);
        
        criteria.setOffset(res.getOffset());
        criteria.setRows(pageSize);
        List<T> list = this.findListByCriteria(criteria);
        res.setData(list);
        return res;
	}
}
