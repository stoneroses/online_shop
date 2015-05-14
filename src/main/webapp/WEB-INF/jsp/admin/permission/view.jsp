<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<p>${permission.name}</p>
<p>${permission.description}</p>
<a href="${ctx}/admin/permissions" class="btn btn-default"><spring:message code="permission.list" /></a>
<shiro:hasPermission name="permission_edit">
  <a href="${ctx}/admin/permissions/${permission.id}/edit" class="btn btn-default"><spring:message
      code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="permission_delete">
  <a href="${ctx}/admin/permissions/${permission.id}/delete" class="btn btn-default"><spring:message
      code="admin.actions.delete" /></a>
</shiro:hasPermission>