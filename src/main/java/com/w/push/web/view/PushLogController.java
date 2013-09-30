package com.w.push.web.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huizhi.dass.common.util.RequestUtil;
import com.w.core.exception.AppException;
import com.w.core.model.Criteria;
import com.w.core.model.PageInfo;
import com.w.push.entity.PushLog;
import com.w.push.service.PushLogService;

@Controller
@RequestMapping("/pushlog")
public class PushLogController {

	public static final int listPageSize = 20;
	
	public static final String LIST = "/push/pushlog/list";
	
	@Resource private PushLogService pushLogService;
	
	@RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) throws AppException {

        Long contentId = RequestUtil.getLong(request, "contentId");
        Integer pageNum = RequestUtil.getInteger(request, "pageNum");
        if (pageNum == null || pageNum <= 0) {// 判断页码是否为空
            pageNum = 1;
        }
        Criteria criteria = new Criteria();
        if(contentId > 0) {
        	criteria.put("contentId", contentId);
        }
        PageInfo<PushLog> result = pushLogService.findListByCriteria(criteria, pageNum, listPageSize);
        request.setAttribute("contentId", contentId);
        request.setAttribute("result", result);
        return new ModelAndView(LIST);
    }
	
}
