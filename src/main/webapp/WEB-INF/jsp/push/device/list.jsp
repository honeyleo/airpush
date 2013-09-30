<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<form id="pagerForm" method="post" action="view/device/list">
    <input type="hidden" name="pageNum" value="${requestScope.result.nowPage}" />
    <input type="hidden" name="numPerPage" value="${requestScope.reslut.pageSize}" />
    <input type="hidden" name="name" value="${param.title}" />
</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="view/device/list" method="post" class="pageForm required-validate">
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
                <th width="120">uui</th>
                <th width="120">token</th>
                <th width="120">imei</th>
                <th width="120">type</th>
                <th width="120">region</th>
                <th width="120">operator</th>
                <th width="120">创建时间</th>
            </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${requestScope.result.data}" var="entity" varStatus="var">
            <tr target="sid" rel="${entity.id}">
                <td>${entity.id}</td>
                <td>${entity.uui}</td>
                <td>${entity.token}</td>
                <td>${entity.imei}</td>
                <td>${entity.type}</td>
                <td>${entity.region}</td>
                <td>${entity.operator}</td>
                <td>${funcs:formatDateTime(entity.createTime,'yyyy-MM-dd hh:mm:ss')}</td>
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

