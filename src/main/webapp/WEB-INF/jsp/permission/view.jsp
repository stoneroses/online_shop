<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<h1>View Permission</h1>
<p>${permission.name}</p>
<p>${permission.description}</p>
<a href="${ctx}/permissions" class="btn btn-default">Permission list</a>
<shiro:hasPermission name="permission_edit">
  <a href="${ctx}/permissions/${permission.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="permission_delete">
  <a href="${ctx}/permissions/${permission.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>