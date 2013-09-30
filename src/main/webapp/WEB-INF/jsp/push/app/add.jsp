<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="pageContent">
    <form method="post" action="view/app/add" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
        	<p>
                <label>应用编号：</label>
                <input name="id" type="text" size="30" class="required"/>
            </p>
            <p>
                <label>应用名称：</label>
                <input name="name" type="text" size="30" class="required"/>
            </p>
            <p>
                <label>关联商户：</label>
                <input type="hidden" name="operator.id" />
            	<input class="textInput" style="float: left;" type="text" name="operator.name"/>
            	<a class="btnLook"  lookupgroup="operator" href="view/app/listLookup">查找带回</a>
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