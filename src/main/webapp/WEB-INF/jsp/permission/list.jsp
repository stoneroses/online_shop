<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="permission.list" />
</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="permission.name" /></th>
      <th><spring:message code="permission.description" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="permission" items="${permissionList}">
      <tr>
        <td><a href="${ctx}/permissions/${permission.id}">${permission.name}</a></td>
        <td>${permission.description}</td>
        <td><shiro:hasPermission name="permission_edit">
            <a href="${ctx}/permissions/${permission.id}/edit" class="btn btn-default"><spring:message
                code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="permission_delete">
            <a href="${ctx}/permissions/${permission.id}/delete" class="btn btn-default"><spring:message
                code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="permission_create">
  <a href="${ctx}/permissions/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>