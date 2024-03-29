<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="role.name" /></th>
      <th><spring:message code="permission" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="role" items="${rolePage.content}">
      <tr>
        <td><a href="${ctx}/admin/roles/${role.id}">${role.name}</a></td>
        <td><c:forEach var="permission" items="${role.permissions}">
            <span class="label label-default">${permission.name}</span>
          </c:forEach></td>
        <td><shiro:hasPermission name="role_edit">
            <a href="${ctx}/admin/roles/${role.id}/edit" class="btn btn-default"><spring:message
                code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="role_delete">
            <a href="${ctx}/admin/roles/${role.id}/delete" class="btn btn-default"><spring:message
                code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="role_create">
  <a href="${ctx}/admin/roles/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>
