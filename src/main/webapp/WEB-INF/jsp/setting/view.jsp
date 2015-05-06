<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>View Setting</h1>
<p>${setting.key}</p>
<p>${setting.value}</p>
<a href="${ctx}/settings" class="btn btn-default">Setting list</a>
<shiro:hasPermission name="setting_edit">
  <a href="${ctx}/settings/${setting.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="setting_delete">
  <a href="${ctx}/settings/${setting.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>
