<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="link.page.title.view" />
</h1>
<p>${link.name}</p>
<p>${link.url}</p>
<p>${link.newWindow}</p>
<a href="${ctx}/admin/links" class="btn btn-default"><spring:message code="link.list" /></a>
<shiro:hasPermission name="link_edit">
  <a href="${ctx}/admin/links/${link.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="link_delete">
  <a href="${ctx}/admin/links/${link.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
</shiro:hasPermission>
