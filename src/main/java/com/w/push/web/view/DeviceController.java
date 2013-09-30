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
import com.w.push.entity.Device;
import com.w.push.service.DeviceService;

@Controller
@RequestMapping("/device")
public class DeviceController {

	public static final int listPageSize = 20;
	
	public static final String LIST = "/push/device/list";
	
	@Resource private DeviceService deviceService;
	
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
        PageInfo<Device> result = deviceService.findListByCriteria(criteria, pageNum, listPageSize);
        request.setAttribute("contentId", contentId);
        request.setAttribute("result", result);
        return new ModelAndView(LIST);
    }
	
}
