package com.w.push.web.view;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huizhi.dass.common.exception.AdminException;
import com.huizhi.dass.common.util.DwzJsonUtil;
import com.huizhi.dass.common.util.RequestUtil;
import com.w.core.exception.AppException;
import com.w.core.model.Criteria;
import com.w.core.model.PageInfo;
import com.w.core.web.JsonView;
import com.w.push.entity.Partner;
import com.w.push.service.PartnerService;

@Controller
@RequestMapping("/partner")
public class PartnerController {

	public static final int listPageSize = 20;
	
	public static final String LIST = "/push/partner/list";
	public static final String DETAIL = "/push/partner/detail";
	public static final String ADD = "/push/partner/add";
	
	@Resource private PartnerService partnerService;
	
	@RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) throws AppException {

        String name = RequestUtil.getString(request, "name");
        Integer pageNum = RequestUtil.getInteger(request, "pageNum");
        if (pageNum == null || pageNum <= 0) {// 判断页码是否为空
            pageNum = 1;
        }
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.put("nameLike", name);
        }
        PageInfo<Partner> result = partnerService.findListByCriteria(criteria, pageNum, listPageSize);
        request.setAttribute("result", result);
        return new ModelAndView(LIST);
    }
	
	@RequestMapping("/goadd")
    public ModelAndView goAdd(HttpServletRequest request) throws AppException {
        
        return new ModelAndView(ADD);
    }
	
	@RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws AppException {
		long id = RequestUtil.getLong(request, "id");
        String name = RequestUtil.getString(request, "name");

        Partner record = new Partner();
        record.setId(id);
        record.setName(name);
        record.setCreateTime(new Date());
        Map<String, String> resp = null;
        try {
			partnerService.save(record);
			resp = DwzJsonUtil.getOkStatusMsg("添加成功");
		} catch (Exception e) {
			resp = DwzJsonUtil.getErrorStatusMsg("添加失败");
		}
        
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, resp);
    }
	
	@RequestMapping("/del")
    public ModelAndView del(HttpServletRequest request) throws AdminException {
        Long id = RequestUtil.getLong(request, "id");
        partnerService.delete(id);
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT,
                DwzJsonUtil.getOkStatusMsg("删除成功"));
    }
	
	@RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request) throws AppException {
        Long id = RequestUtil.getLong(request, "id");
        Partner entity;
		try {
			entity = partnerService.getEntityById(id);
			request.setAttribute("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new ModelAndView(DETAIL);
    }
	
	@RequestMapping("/update")
    public ModelAndView update(HttpServletRequest request,
            HttpServletResponse response) throws AppException {
        Long id = RequestUtil.getLong(request, "id");
        String name = RequestUtil.getString(request, "name");
        
        Partner record = new Partner();
        record.setId(id);
        
        if (StringUtils.isNotBlank(name)) {
        	record.setName(name);
        }
        
        partnerService.update(record);
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, DwzJsonUtil.getOkStatusMsg("更新成功"));
    }
    
}
