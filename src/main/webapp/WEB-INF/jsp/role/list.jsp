<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Role List page</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Name</th>
      <th width="150px">Permission</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="role" items="${roleList}">
      <tr>
        <td><a href="${ctx}/roles/${role.id}">${role.name}</a></td>
        <td><c:forEach var="permission" items="${role.permissions}">
            <span class="label label-default">${permission.name}</span>
          </c:forEach></td>
        <td><shiro:hasPermission name="role_edit">
            <a href="${ctx}/roles/${role.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission> <shiro:hasPermission name="role_delete">
            <a href="${ctx}/roles/${role.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="role_create">
  <a href="${ctx}/roles/create" class="btn btn-default">Create</a>
</shiro:hasPermission>
