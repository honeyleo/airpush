<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="pageContent">
    <form method="post" action="view/criteria/add" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
    	<input name="contentId" type="text" value="${contentId}" hidden="true" class=""/>
	    <div class="pageFormContent" layoutH="60">
	      <div class="unit">
	        <label>渠道：</label>
	        <input name="channelId" type="text" size="40" class=""/>
	      </div>
	      
	      <div class="unit">
	        <label>商户：</label>
            <input name="partnerId" type="text" size="40" class=""/>
	      </div>
	      <div class="unit">
	        <label>应用：</label>
  			<input name="appId" type="text" size="40" class=""/>
	      </div>
	      <div class="unit">
	        <label>开始时间：</label>
            <input name="startTime" type="text" size="20" value="" class="date textInput readonly" readonly="true" format="yyyy-MM-dd HH:mm:ss"/>
            <a class="inputDateButton" href="javascript:;">选择</a>
            <span class="info">yyyy-MM-dd HH:mm:ss</span>
	      </div>
	      <div class="unit">
	        <label>结束时间：</label>
  			<input name="endTime" type="text" size="20" value="" class="date textInput readonly" readonly="true" format="yyyy-MM-dd HH:mm:ss"/>
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
            <input type="hidden" name="operator.id" />
        	<input class="textInput" style="float: left;" type="text" name="operator.name"/>
        	<a class="btnLook"  lookupgroup="operator" href="view/criteria/contentLookup">查找带回</a>
	      </p>
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