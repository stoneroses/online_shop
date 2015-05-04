<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>View Role</h1>
<p>${role.name}</p>
<p>${role.description}</p>
<c:forEach var="permission" items="${role.permissions}">
  <span class="label label-default">${permission.name}</span>
</c:forEach>
<shiro:hasPermission name="role_list">
<a href="${ctx}/roles" class="btn btn-default">Role List</a>
</shiro:hasPermission>
<shiro:hasPermission name="role_edit">
  <a href="${ctx}/roles/${role.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="role_delete">
  <a href="${ctx}/roles/${role.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>