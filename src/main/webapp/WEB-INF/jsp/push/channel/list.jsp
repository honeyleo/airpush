<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<form id="pagerForm" method="post" action="view/channel/list">
    <input type="hidden" name="pageNum" value="${requestScope.result.nowPage}" />
    <input type="hidden" name="numPerPage" value="${requestScope.reslut.pageSize}" />
    <input type="hidden" name="name" value="${param.name}" />
</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="view/channel/list" method="post" class="pageForm required-validate">
      <div class="searchBar">
      <table class="searchContent">
        <tr>
            <td>
                        渠道名称：<input type="text" name=name value="${param.name}"/> 
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
        <div class="panelBar">
            <ul class="toolBar">
                <li><a class="add" href="view/channel/goadd" rel="lookup2organization" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>添加</span></a></li>
                <li><a class="delete" href="view/channel/del?id={sid}" target="ajaxTodo" title="确定删除该渠道吗?"><span>删除</span></a></li>
                <li><a class="edit" href="view/channel/detail?id={sid}" rel="lookup2organization" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>修改</span></a></li>         
            </ul>
        </div>
        
        <table class="table" width="100%" layoutH="138">
            <thead>
            <tr>
                <th width="80">渠道编号</th>
                <th width="120">渠道名称</th>
                <th width="120">创建时间</th>
            </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${requestScope.result.data}" var="entity" varStatus="var">
            <tr target="sid" rel="${entity.id}">
                <td>${entity.id}</td>
                <td>${entity.name}</td>
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

