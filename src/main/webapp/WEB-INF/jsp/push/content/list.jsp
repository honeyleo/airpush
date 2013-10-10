<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<form id="pagerForm" method="post" action="view/content/list">
    <input type="hidden" name="pageNum" value="${requestScope.result.nowPage}" />
    <input type="hidden" name="numPerPage" value="${requestScope.reslut.pageSize}" />
    <input type="hidden" name="name" value="${param.title}" />
</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="view/content/list" method="post" class="pageForm required-validate">
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
        <div class="panelBar">
            <ul class="toolBar">
                <li><a class="add" href="view/content/goadd" rel="lookup2organization" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>添加</span></a></li>
                <li><a class="delete" href="view/content/del?id={sid}" target="ajaxTodo" title="确定删除该内容吗?"><span>删除</span></a></li>
                <li><a class="edit" href="view/content/detail?id={sid}" rel="lookup2organization" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>修改</span></a></li>         
            </ul>
        </div>
        
        <table class="table" width="100%" layoutH="138">
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
                <th width="120"></th>
            </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${requestScope.result.data}" var="entity" varStatus="var">
            <tr target="sid" rel="${entity.id}">
                <td>${entity.id}</td>
                <td>${entity.title}</td>
                <td>${entity.content}</td>
                <td>${funcs:getCmd(entity.type)}</td>
                <td>${funcs:getMsgType(entity.msgType)}</td>
                <td>${entity.url}</td>
                <td>${funcs:getStatus(entity.status)}</td>
                <td>${funcs:formatDateTime(entity.createTime,'yyyy-MM-dd hh:mm:ss')}</td>
                <td><a href="view/content/detail?id=${entity.id}" target="dialog" mask="true" target="dialog" minable="false" width="500" height="600"><span style="color:blue">查看</span></a></td>
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

