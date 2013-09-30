package com.w.push.service;

import com.w.core.service.BaseService;
import com.w.push.entity.PushLog;

public interface PushLogService extends BaseService<PushLog> {

	void hit(PushLog entity);
}
