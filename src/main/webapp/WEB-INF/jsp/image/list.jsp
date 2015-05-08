<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="image.list" /></h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="image.name" /></th>
      <th><spring:message code="image.description" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="image" items="${imagePage.content}">
      <tr>
        <td><a href="${ctx}/images/${image.id}">${image.name}</a></td>
        <td>${image.description}</td>
        <td><shiro:hasPermission name="image_edit">
            <a href="${ctx}/images/${image.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="image_delete">
            <a href="${ctx}/images/${image.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<c:set var="pageObject" value="${imagePage}" />
<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="image_create">
  <a href="${ctx}/images/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>