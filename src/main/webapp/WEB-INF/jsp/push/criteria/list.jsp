<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<form id="pagerForm" method="post" action="view/criteria/list">
    <input type="hidden" name="pageNum" value="${requestScope.result.nowPage}" />
    <input type="hidden" name="numPerPage" value="${requestScope.reslut.pageSize}" />
    <input type="hidden" name="contentId" value="${param.contentId}" />
    <input type="hidden" name="channelId" value="${param.channelId}" />
    <input type="hidden" name="partnerId" value="${param.partnerId}" />
    <input type="hidden" name="appId" value="${param.appId}" />
</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="view/criteria/list" method="post" class="pageForm required-validate">
      <div class="searchBar">
      <table class="searchContent">
        <tr>
            <td>
            <p>
	      	<label>关联内容：</label>
            <input type="hidden" name="contentId" value="${param.contentId }"/>
        	<input class="textInput" style="float: left;" value="${funcs:getPushContentTitle(param.contentId) }" type="text" name="contentTitle"/>
        	<a class="btnLook"  lookupgroup="" href="view/criteria/contentLookup">查找带回</a>
	      	</p>
            </td>
            <td>
            <p>
	      	<label>关联渠道：</label>
            <input type="hidden" name="channelId" value="${param.channelId }"/>
        	<input class="textInput" style="float: left;" type="text" name="channelName" value="${funcs:getChannelName(param.channelId) }"/>
        	<a class="btnLook"  lookupgroup="" href="view/criteria/channelLookup">查找带回</a>
	      	</p>
            </td>
            <td>
            <p>
	      	<label>关联商户：</label>
            <input type="hidden" name="partnerId" value="${param.partnerId }"/>
        	<input class="textInput" style="float: left;" type="text" name="partnerName" value="${funcs:getPartnerName(param.partnerId) }"/>
        	<a class="btnLook"  lookupgroup="" href="view/criteria/partnerLookup">查找带回</a>
	      	</p>
            </td>
            <td>
            <p>
	      	<label>关联应用：</label>
            <input type="hidden" name="appId" value="${param.appId }"/>
        	<input class="textInput" style="float: left;" type="text" name="appName" value="${funcs:getAppName(param.appId) }"/>
        	<a class="btnLook"  lookupgroup="" href="view/criteria/appLookup">查找带回</a>
	      	</p>
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
                <li><a class="add" href="view/criteria/goadd?contentId=${contentId}" rel="lookup2organization" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>添加</span></a></li>
                <li><a class="delete" href="view/criteria/del?id={sid}" target="ajaxTodo" title="确定删除该内容吗?"><span>删除</span></a></li>
                <li><a class="edit" href="view/criteria/detail?id={sid}" rel="lookup2organization" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>修改</span></a></li>         
            </ul>
        </div>
        
        <table class="table" width="100%" layoutH="138">
            <thead>
            <tr>
                <th width="80">ID</th>
                <th width="120">内容主题</th>
                <th width="120">渠道</th>
                <th width="120">商户</th>
                <th width="120">应用</th>
                <th width="120">开始时间</th>
                <th width="120">结束时间</th>
                <th width="120">状态</th>
                <th width="120">创建时间</th>
            </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${requestScope.result.data}" var="entity" varStatus="var">
            <tr target="sid" rel="${entity.id}">
                <td>${entity.id}</td>
                <td>${funcs:getPushContentTitle(entity.contentId) }</td>
                <td>${funcs:getChannelName(entity.channelId) }</td>
                <td>${funcs:getPartnerName(entity.partnerId) }</td>
                <td>${funcs:getAppName(entity.appId) }</td>
                <td>${funcs:formatDateTime(entity.startTime,'yyyy-MM-dd hh:mm:ss')}</td>
                <td>${funcs:formatDateTime(entity.endTime,'yyyy-MM-dd hh:mm:ss')}</td>
                <td>${entity.status}</td>
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

