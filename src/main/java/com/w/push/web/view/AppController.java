package com.w.push.web.view;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.w.push.entity.App;
import com.w.push.entity.Partner;
import com.w.push.service.AppService;
import com.w.push.service.PartnerService;

@Controller
@RequestMapping("/app")
public class AppController {

	public static final int listPageSize = 20;
	
	public static final String LIST = "/push/app/list";
	public static final String DETAIL = "/push/app/detail";
	public static final String ADD = "/push/app/add";
	
	@Resource private AppService appService;
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
        PageInfo<App> result = appService.findListByCriteria(criteria, pageNum, listPageSize);
        request.setAttribute("result", result);
        return new ModelAndView(LIST);
    }
	
	@RequestMapping("/goadd")
    public ModelAndView goAdd(HttpServletRequest request) throws AppException {
        
        return new ModelAndView(ADD);
    }
	
	@RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws AppException {
		Long id = RequestUtil.getLong(request, "id");
        String name = RequestUtil.getString(request, "name");
        long partnerId = RequestUtil.getLong(request, "operator.id");
        
        App record = new App();
        record.setId(id);
        record.setName(name);
        record.setPartnerId(partnerId);
        
        record.setCreateTime(new Date());
        try {
        	appService.save(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, DwzJsonUtil.getOkStatusMsg("添加成功"));
    }
	
	@RequestMapping("/del")
    public ModelAndView del(HttpServletRequest request) throws AdminException {
        Long id = RequestUtil.getLong(request, "id");
        appService.delete(id);
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT,
                DwzJsonUtil.getOkStatusMsg("删除成功"));
    }
	
	@RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request) throws AppException {
        Long id = RequestUtil.getLong(request, "id");
        App entity;
		try {
			entity = appService.getEntityById(id);
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
        Long partnerId = RequestUtil.getLong(request, "operator.id");
        
        App record = new App();
        record.setId(id);
        
        if (StringUtils.isNotBlank(name)) {
        	record.setName(name);
        }
        if(partnerId > 0) {
        	record.setPartnerId(partnerId);
        }
        appService.update(record);
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, DwzJsonUtil.getOkStatusMsg("更新成功"));
    }
    
	@RequestMapping("/listLookup")
    public String listLookup(HttpServletRequest request, HttpSession session) throws AdminException {
        
        Integer pageNum = RequestUtil.getInteger(request, "pageNum");
        if (pageNum == null || pageNum <= 0) {// 判断页码是否为空
            pageNum = 1;
        }
        
        Criteria criteria = new Criteria();
        String keyword = request.getParameter("keyword");
        if(keyword != null && keyword.trim().length() > 0){
            criteria.put("name", keyword);
        }
        
        PageInfo<Partner> result = partnerService.findListByCriteria(criteria, pageNum, 10); // 检索到的人员列表
        request.setAttribute("result", result);
        
        return "/push/app/list_lookup";
    }
}
