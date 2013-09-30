<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>

<script type="text/javascript">
$(function(){
    $('#opListLookup').treeTable();
});
</script>

<form id="pagerForm" method="post" action="view/content/list">
    <input type="hidden" name="pageNum" value="${requestScope.result.nowPage}" />
    <input type="hidden" name="numPerPage" value="${requestScope.reslut.pageSize}" />
    <input type="hidden" name="keyword" value="${param.keyword}" />
</form>

<div class="pageHeader">
  <form rel="pagerForm" method="post" action="view/criteria/contentLookup" onsubmit="return dwzSearch(this, 'dialog');">
    <div class="searchBar">
      <table class="searchContent">
        <tr>
          <td>&nbsp;</td>
          <td>内容状态:</td>
          <td>
            <input name=keyword value="${param.keyword }" type="text"/>
          </td>
        </tr>
      </table>
      <div class="subBar">
        <ul>
          <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
          <li><a class="button" href="javascript:$.bringBack({id:0,name:'取消'})" title="查找带回"><span>取消选择</span></a></li>
        </ul>
      </div>
    </div>
  </form>
</div>

<div class="pageContent">
        <table id="opListLookup" class="list" width="100%" layoutH="105">
            <thead>
            <tr>
                <th width="80">ID</th>
                <th width="120">主题</th>
                <th width="120">内容</th>
                <th width="120">类型</th>
                <th width="120">推送类型</th>
                <th width="120">URL</th>
                <th width="120">状态</th>
                <th width="120">创建时间</th>
            </tr>
        </thead>
        <tbody>
          <c:forEach items="${requestScope.result.data}" var="op" varStatus="var">
            <tr id="${op.id }" hasChild="true" target="sid_op" rel="${op.id}">
                <td><span controller="true">${op.id}</span></td>
                <td>${op.title}</td>
                <td>${op.content}</td>
                <td>${op.type}</td>
                <td>${op.msgType}</td>
                <td>${op.url}</td>
                <td>${op.status}</td>
                <td>
                    <a class="btnSelect" href="javascript:$.bringBack({id:${op.id}, name:'${op.title}'})" title="查找带回">选择</a>
                </td>
            </tr>
          </c:forEach>
        </tbody>
        </table>
        <div class="panelBar">
          <div class="pages">
            <span>共${requestScope.result.total}条</span>
          </div>
        <div class="pagination" targetType="dialog" totalCount="${requestScope.result.total}" numPerPage="${requestScope.result.pageSize}" currentPage="${requestScope.result.nowPage}"></div>
    </div>
</div>

