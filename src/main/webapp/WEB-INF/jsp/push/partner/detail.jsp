<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.huizhi.dass.model.type.StateType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="pageContent">
    <form method="post" action="view/partner/update" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <p> 
                <label>商户名称：</label>
                <input name="name" type="text" size="30" value="${requestScope.entity.name}"/>
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