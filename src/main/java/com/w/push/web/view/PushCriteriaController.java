package com.w.push.web.view;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.w.push.entity.Channel;
import com.w.push.entity.Partner;
import com.w.push.entity.PushContent;
import com.w.push.entity.PushCriteria;
import com.w.push.service.AppService;
import com.w.push.service.ChannelService;
import com.w.push.service.PartnerService;
import com.w.push.service.PushContentService;
import com.w.push.service.PushCriteriaService;

@Controller
@RequestMapping("/criteria")
public class PushCriteriaController {

	public static final int listPageSize = 20;
	
	public static final String LIST = "/push/criteria/list";
	public static final String DETAIL = "/push/criteria/detail";
	public static final String ADD = "/push/criteria/add";
	
	@Resource private PushCriteriaService pushCriteriaService;
	@Resource private PushContentService pushContentService;
	@Resource private ChannelService channelService;
	@Resource private PartnerService partnerService;
	@Resource private AppService appService;
	
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
        PageInfo<PushCriteria> result = pushCriteriaService.findListByCriteria(criteria, pageNum, listPageSize);
        request.setAttribute("contentId", contentId);
        request.setAttribute("result", result);
        return new ModelAndView(LIST);
    }
	
	@RequestMapping("/goadd")
    public ModelAndView goAdd(HttpServletRequest request) throws AppException {
		Long contentId = RequestUtil.getLong(request, "contentId");
		request.setAttribute("contentId", contentId);
        return new ModelAndView(ADD);
    }
	
	@RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws AppException {
        Long contentId = RequestUtil.getLong(request, "contentId");
        if(contentId == 0) {
        	contentId = RequestUtil.getLong(request, "operator.contentId");
        }
        PushCriteria record = new PushCriteria();
        record.setContentId(contentId);
        setEntity(record, request);
        record.setCreateTime(new Date());
        
        try {
        	pushCriteriaService.save(record);
		} catch (Exception e) {
			return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, DwzJsonUtil.getErrorStatusMsg("添加失败"));
		}
        
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, DwzJsonUtil.getOkStatusMsg("添加成功"));
    }
	
	@RequestMapping("/del")
    public ModelAndView del(HttpServletRequest request) throws AdminException {
        Long id = RequestUtil.getLong(request, "id");
        pushCriteriaService.delete(id);
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT,
                DwzJsonUtil.getOkStatusMsg("删除成功"));
    }
	
	@RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request) throws AppException {
        Long id = RequestUtil.getLong(request, "id");
        PushCriteria entity;
		try {
			entity = pushCriteriaService.getEntityById(id);
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
        
        Long contentId = RequestUtil.getLong(request, "operator.contentId");
        PushCriteria record = new PushCriteria();
        record.setId(id);
        record.setContentId(contentId);
        setEntity(record, request);
        
        pushCriteriaService.update(record);
        return new ModelAndView(JsonView.JSON_VIEW, JsonView.JSON_ROOT, DwzJsonUtil.getOkStatusMsg("更新成功"));
    }
	
	@RequestMapping("/contentLookup")
    public String contentLookup(HttpServletRequest request, HttpSession session) throws AdminException {
        
        Integer pageNum = RequestUtil.getInteger(request, "pageNum");
        if (pageNum == null || pageNum <= 0) {// 判断页码是否为空
            pageNum = 1;
        }
        
        Criteria criteria = new Criteria();
        String keyword = request.getParameter("keyword");
        if(keyword != null && keyword.trim().length() > 0){
            criteria.put("title", keyword);
        }
        
        PageInfo<PushContent> result = pushContentService.findListByCriteria(criteria, pageNum, 10); // 检索到的人员列表
        request.setAttribute("result", result);
        
        return "/push/criteria/content_lookup";
    }
	
	@RequestMapping("/channelLookup")
    public String channelLookup(HttpServletRequest request, HttpSession session) throws AdminException {
        
        Integer pageNum = RequestUtil.getInteger(request, "pageNum");
        if (pageNum == null || pageNum <= 0) {// 判断页码是否为空
            pageNum = 1;
        }
        
        Criteria criteria = new Criteria();
        String keyword = request.getParameter("keyword");
        if(keyword != null && keyword.trim().length() > 0){
            criteria.put("title", keyword);
        }
        
        PageInfo<Channel> result = channelService.findListByCriteria(criteria, pageNum, 10); // 检索到的人员列表
        request.setAttribute("result", result);
        
        return "/push/criteria/channel_lookup";
    }
	
	@RequestMapping("/partnerLookup")
    public String partnerLookup(HttpServletRequest request, HttpSession session) throws AdminException {
        
        Integer pageNum = RequestUtil.getInteger(request, "pageNum");
        if (pageNum == null || pageNum <= 0) {// 判断页码是否为空
            pageNum = 1;
        }
        
        Criteria criteria = new Criteria();
        String keyword = request.getParameter("keyword");
        if(keyword != null && keyword.trim().length() > 0){
            criteria.put("title", keyword);
        }
        
        PageInfo<Partner> result = partnerService.findListByCriteria(criteria, pageNum, 10); // 检索到的人员列表
        request.setAttribute("result", result);
        
        return "/push/criteria/partner_lookup";
    }
	
	@RequestMapping("/appLookup")
    public String appLookup(HttpServletRequest request, HttpSession session) throws AdminException {
        
        Integer pageNum = RequestUtil.getInteger(request, "pageNum");
        if (pageNum == null || pageNum <= 0) {// 判断页码是否为空
            pageNum = 1;
        }
        
        Criteria criteria = new Criteria();
        String keyword = request.getParameter("keyword");
        if(keyword != null && keyword.trim().length() > 0){
            criteria.put("title", keyword);
        }
        
        PageInfo<App> result = appService.findListByCriteria(criteria, pageNum, 10); // 检索到的人员列表
        request.setAttribute("result", result);
        
        return "/push/criteria/app_lookup";
    }
	
	
	private void setEntity(PushCriteria record, HttpServletRequest request) throws AppException {
		
        Long channelId = RequestUtil.getLong(request, "operator.channelId");
        Long partnerId = RequestUtil.getLong(request, "operator.partnerId");
        Long appId = RequestUtil.getLong(request, "operator.appId");
        Date startTime = RequestUtil.getDate(request, "startTime", "yyyy-MM-dd HH:mm:ss");
        Date endTime = RequestUtil.getDate(request, "endTime", "yyyy-MM-dd HH:mm:ss");
        Integer status = RequestUtil.getInteger(request, "status");
        
        record.setChannelId(channelId);
        record.setPartnerId(partnerId);
        record.setAppId(appId);
        record.setStartTime(startTime);
        record.setEndTime(endTime);
        record.setStatus(status);
        record.setCreateTime(new Date());
	}
    
}
