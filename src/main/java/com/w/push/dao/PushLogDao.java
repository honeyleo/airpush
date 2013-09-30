package com.w.push.dao;

import com.w.core.dao.GenericDao;
import com.w.push.entity.PushLog;

/**
 * 
 * @author Leo.liao
 * @since 2013-09-25
 */
public interface PushLogDao extends GenericDao<PushLog> {
     
	void hit(PushLog entity);
}
