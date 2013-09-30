<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<form id="pagerForm" method="post" action="view/pushlog/list">
    <input type="hidden" name="pageNum" value="${requestScope.result.nowPage}" />
    <input type="hidden" name="numPerPage" value="${requestScope.reslut.pageSize}" />
    <input type="hidden" name="name" value="${param.title}" />
</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="view/pushlog/list" method="post" class="pageForm required-validate">
      <div class="searchBar">
      <table class="searchContent">
        <tr>
            <td>
                        主题：<input type="text" name=title value="${param.title}"/> 
            </td>
        </tr>
      </table>
      <div class="subBar">
          <ul>
              <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
          </ul>
      </div>
    </div>
    </form>
</div>

<div class="pageContent">
        <table class="table" width="100%" layoutH="138">
            <thead>
            <tr>
                <th width="80">ID</th>
                <th width="120">内容</th>
                <th width="120">uui</th>
                <th width="120">渠道</th>
                <th width="120">商户</th>
                <th width="120">应用</th>
                <th width="120">状态</th>
                <th width="120">推送时间</th>
                <th width="120">回馈时间</th>
            </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${requestScope.result.data}" var="entity" varStatus="var">
            <tr target="sid" rel="${entity.id}">
                <td>${entity.id}</td>
                <td>${entity.contentId}</td>
                <td>${entity.uui}</td>
                <td>${entity.channelId}</td>
                <td>${entity.partnerId}</td>
                <td>${entity.appId}</td>
                <td>${entity.status}</td>
                <td>${funcs:formatDateTime(entity.pushTime,'yyyy-MM-dd hh:mm:ss')}</td>
                <td>${funcs:formatDateTime(entity.hitTime,'yyyy-MM-dd hh:mm:ss')}</td>
            </tr>
        </c:forEach>
        </tbody>
        </table>
        <div class="panelBar">
          <div class="pages">
            <span>共${requestScope.result.total}条</span>
          </div>
        <div class="pagination" targetType="navTab" totalCount="${requestScope.result.total}" numPerPage="${requestScope.result.pageSize}" currentPage="${requestScope.result.nowPage}"></div>

    </div>
</div>

