package com.w.push.web.view;

import java.util.Date;

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
import com.w.push.entity.PushContent;
import com.w.push.service.PushContentService;

@Controller
@RequestMapping("/content")
public class PushContentController {

	public static final int listPageSize = 20;
	
	public static final String LIST = "/push/content/list";
	public static final String DETAIL = "/push/content/detail";
	public static final String ADD = "/push/content/add";
	
	@Resource private PushContentService pushContentService;
	
	@RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) throws AppException {

        String title = RequestUtil.getString(request, "title");
        Integer pageNum = RequestUtil.getInteger(request, "pageNum");
        if (pageNum == null || pageNum <= 0) {// 判断页码是否为空
            pageNum = 1;
        }
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(title)) {
            criteria.put("titleLike", title);
        }
        String strStatus = request.getParameter("status");
        if(StringUtils.isNotBlank(strStatus)) {
        	int status = Integer.parseInt(strStatus);
        	criteria.put("status", status);
        }
        criteria.putOrder("id", true);
        PageInfo<PushContent> result = pushContentService.findListByCriteria(criteria, pageNum, listPageSize);
        request.setAttribute("result", result);
        return new ModelAndView(LIST);
    }
	
	@RequestMapping("/goadd")
    public ModelAndView goAdd(HttpServletRequest request) throws AppException {
        
        return new ModelAndView(ADD);
    }
	
	@RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws AppException {
        
        PushContent record = new PushContent();
        setEntity(record, request);
        record.setCreateTime(new Date());
        
        try {
        	pushContentService.save(record);
		} catch (Exception e) {
			return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, DwzJsonUtil.getErrorStatusMsg("添加失败"));
		}
        
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, DwzJsonUtil.getOkStatusMsg("添加成功"));
    }
	
	@RequestMapping("/del")
    public ModelAndView del(HttpServletRequest request) throws AdminException {
        Long id = RequestUtil.getLong(request, "id");
        pushContentService.delete(id);
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT,
                DwzJsonUtil.getOkStatusMsg("删除成功"));
    }
	
	@RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request) throws AppException {
        Long id = RequestUtil.getLong(request, "id");
        PushContent entity;
		try {
			entity = pushContentService.getEntityById(id);
			request.setAttribute("entity", entity);
		} catch (Exception e) {
			throw new AppException("100","查看详情出错");
		}
        return new ModelAndView(DETAIL);
    }
	
	@RequestMapping("/update")
    public ModelAndView update(HttpServletRequest request,
            HttpServletResponse response) throws AppException {
        Long id = RequestUtil.getLong(request, "id");
        
        PushContent record = new PushContent();
        record.setId(id);
        setEntity(record, request);
        
        pushContentService.update(record);
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, DwzJsonUtil.getOkStatusMsg("更新成功"));
    }
	
	protected void setEntity(PushContent entity, HttpServletRequest request) throws AppException {
		
		String title = RequestUtil.getString(request, "title");
        String content = RequestUtil.getString(request, "content");
        Integer type = RequestUtil.getInteger(request, "type");
        Integer msgType = RequestUtil.getInteger(request, "msgType");
        String url = RequestUtil.getString(request, "url");
        Integer status = RequestUtil.getInteger(request, "status");
        
        entity.setTitle(title);
        entity.setContent(content);
        entity.setType(type);
        entity.setMsgType(msgType);
        entity.setUrl(url);
        entity.setStatus(status);
	}
    
}
