<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<p>${setting.key}</p>
<p>${setting.value}</p>
<a href="${ctx}/admin/settings" class="btn btn-default"><spring:message code="setting.list" /></a>
<shiro:hasPermission name="setting_edit">
  <a href="${ctx}/admin/settings/${setting.id}/edit" class="btn btn-default"><spring:message
      code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="setting_delete">
  <a href="${ctx}/admin/settings/${setting.id}/delete" class="btn btn-default"><spring:message
      code="admin.actions.delete" /></a>
</shiro:hasPermission>
