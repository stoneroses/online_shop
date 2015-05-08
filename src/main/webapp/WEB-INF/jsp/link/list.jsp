<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="link.list" /></h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="link.name" /></th>
      <th><spring:message code="link.url" /></th>
      <th><spring:message code="link.new.window" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="link" items="${linkPage.content}">
      <tr>
        <td><a href="${ctx}/links/${link.id}">${link.name}</a></td>
        <td>${link.url}</td>
        <td>${link.newWindow}</td>
        <td><shiro:hasPermission name="link_edit">
            <a href="${ctx}/links/${link.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="link_delete">
            <a href="${ctx}/links/${link.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<c:set var="pageObject" value="${linkPage}" />
<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="link_create">
  <a href="${ctx}/links/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>