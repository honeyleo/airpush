<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="pageContent">
    <form method="post" action="view/content/add" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	    <div class="pageFormContent" layoutH="60">
	      <div class="unit">
	        <label>主  题：</label>
	        <input name="title" type="text" size="40" class="required"/>
	      </div>
	      
	      <div class="unit">
	        <label>内容：</label>
            <textarea name="content" cols="35" rows="4" class="textInput"></textarea>
	      </div>
	      <div class="unit">
	        <label>类型：</label>
  			<select name="type" class="required combox">
       			<option value="1">纯文本</option>
  				<option value="2">链接</option>
  				<option value="3">下载</option>
  				<option value="4">打开游戏</option>
  			</select>       
	      </div>
	      <div class="unit">
	        <label>推送类型：</label>
  			<select name="msgType" class="required combox">
       			<option value="0">游戏外</option>
  				<option value="1">游戏内</option>
  			</select>       
	      </div>
	      <div class="unit">
	        <label>URL：</label>
	        <input name="url" type="text" size="40"/>       
	      </div>
	      <div class="unit">
	        <label>状态：</label>
  			<select name="status" class="required combox">
       			<option value="0">未发布</option>
  				<option value="1">发布</option>
  				<option value="-1">删除</option>
  			</select>       
	      </div>
	    </div>
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