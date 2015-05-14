<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="news.title" /></th>
      <th><spring:message code="news.content" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="news" items="${newsPage.content}">
      <tr>
        <td><a href="${ctx}/admin/news/${news.id}">${news.title}</a></td>
        <td>${news.content}</td>
        <td><shiro:hasPermission name="news_edit">
            <a href="${ctx}/admin/news/${news.id}/edit" class="btn btn-default"><spring:message
                code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="news_delete">
            <a href="${ctx}/admin/news/${news.id}/delete" class="btn btn-default"><spring:message
                code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="news_create">
  <a href="${ctx}/admin/news/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>