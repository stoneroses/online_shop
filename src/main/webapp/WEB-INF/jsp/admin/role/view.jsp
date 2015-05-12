<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="role.page.title.view" />
</h1>
<p>${role.name}</p>
<p>${role.description}</p>
<c:forEach var="permission" items="${role.permissions}">
  <span class="label label-default">${permission.name}</span>
</c:forEach>
<shiro:hasPermission name="role_list">
  <a href="${ctx}/admin/roles" class="btn btn-default"><spring:message code="role.list" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="role_edit">
  <a href="${ctx}/admin/roles/${role.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="role_delete">
  <a href="${ctx}/admin/roles/${role.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
</shiro:hasPermission>