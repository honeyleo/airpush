<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.huizhi.dass.model.type.StateType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="pageContent">
    <form method="post" action="view/content/update" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="60">
	      <div class="unit">
	        <label>主  题：</label>
	        <input name="title" type="text" size="40" value="${requestScope.entity.title}" class="required"/>
	      </div>
	      
	      <div class="unit">
	        <label>内容：</label>
            <textarea name="content" cols="35" rows="4" class="textInput" >${requestScope.entity.content}</textarea>
	      </div>
	      <div class="unit">
	        <label>类型：</label>
  			<select name="type" class="required combox">
       			<option value="1" ${requestScope.entity.type == 1 ? "selected" : ""}>纯文本</option>
  				<option value="2" ${requestScope.entity.type == 2 ? "selected" : ""}>链接</option>
  				<option value="3" ${requestScope.entity.type == 3 ? "selected" : ""}>下载</option>
  				<option value="4" ${requestScope.entity.type == 4 ? "selected" : ""}>打开游戏</option>
  			</select>       
	      </div>
	      <div class="unit">
	        <label>推送类型：</label>
  			<select name="msgType" class="required combox">
       			<option value="0" ${requestScope.entity.msgType == 0 ? "selected" : ""}>游戏外</option>
  				<option value="1" ${requestScope.entity.msgType == 1 ? "selected" : ""}>游戏内</option>
  			</select>       
	      </div>
	      <div class="unit">
	        <label>URL：</label>
	        <input name="url" type="text" size="40" value="${requestScope.entity.url}"/>       
	      </div>
	      <div class="unit">
	        <label>状态：</label>
  			<select name="status" class="required combox">
       			<option value="0" ${requestScope.entity.status == 0 ? "selected" : ""}>未发布</option>
  				<option value="1" ${requestScope.entity.status == 1 ? "selected" : ""}>发布</option>
  				<option value="-1" ${requestScope.entity.status == -1 ? "selected" : ""}>删除</option>
  			</select>       
	      </div>
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