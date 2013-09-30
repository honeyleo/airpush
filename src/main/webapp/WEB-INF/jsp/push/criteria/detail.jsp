<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.huizhi.dass.model.type.StateType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<div class="pageContent">
    <form method="post" action="view/criteria/update" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="60">
	      <div class="unit">
	        <label>渠道：</label>
	        <input name="channelId" value="${requestScope.entity.channelId}" type="text" size="40" class=""/>
	      </div>
	      
	      <div class="unit">
	        <label>商户：</label>
            <input name="partnerId" value="${requestScope.entity.partnerId}" type="text" size="40" class=""/>
	      </div>
	      <div class="unit">
	        <label>应用：</label>
  			<input name="appId" value="${requestScope.entity.appId}" type="text" size="40" class=""/>
	      </div>
	      <div class="unit">
	        <label>开始时间：</label>
            <input name="startTime" type="text" size="20" value="${funcs:formatDateTime(entity.startTime,'yyyy-MM-dd hh:mm:ss')}" class="date textInput readonly" readonly="true" format="yyyy-MM-dd HH:mm:ss"/>
            <a class="inputDateButton" href="javascript:;">选择</a>
            <span class="info">yyyy-MM-dd HH:mm:ss</span>
	      </div>
	      <div class="unit">
	        <label>结束时间：</label>
  			<input name="endTime" type="text" size="20" value="${funcs:formatDateTime(entity.endTime,'yyyy-MM-dd hh:mm:ss')}" class="date textInput readonly" readonly="true" format="yyyy-MM-dd HH:mm:ss"/>
            <a class="inputDateButton" href="javascript:;">选择</a>
            <span class="info">yyyy-MM-dd HH:mm:ss</span>
	      </div>
	      <div class="unit">
	        <label>状态：</label>
  			<select name="status" class="required combox">
       			<option value="0">未发布</option>
  				<option value="1">发布</option>
  			</select>       
	      </div>
	      <p>
	      	<label>关联内容：</label>
            <input type="hidden" name="operator.id" value="${requestScope.entity.contentId }"/>
        	<input class="textInput" style="float: left;" value="${funcs:getPushContentTitle(requestScope.entity.contentId) }" type="text" name="operator.name"/>
        	<a class="btnLook"  lookupgroup="operator" href="view/criteria/contentLookup">查找带回</a>
	      </p>
	    </div>
        <input name="id" type="hidden" value="${requestScope.entity.id}"/>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
                </li>
            </ul>
        </div>
    </form>
</div>