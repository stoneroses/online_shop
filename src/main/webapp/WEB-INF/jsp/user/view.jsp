<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<h1>View User</h1>
<p>${user.name}</p>
<p>${user.description}</p>
<shiro:hasPermission name="user_list">
<a href="${ctx}/users" class="btn btn-default">User list</a>
</shiro:hasPermission>
<shiro:hasPermission name="user_edit">
  <a href="${ctx}/users/${user.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="user_delete">
  <a href="${ctx}/users/${user.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>