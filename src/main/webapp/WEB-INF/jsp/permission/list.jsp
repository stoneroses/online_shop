<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Permission List</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th>Name</th>
      <th>Description</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="permission" items="${permissionList}">
      <tr>
        <td><a href="${ctx}/permissions/${permission.id}">${permission.name}</a></td>
        <td>${permission.description}</td>
        <td><shiro:hasPermission name="permission_edit">
            <a href="${ctx}/permissions/${permission.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission> <shiro:hasPermission name="permission_delete">
            <a href="${ctx}/permissions/${permission.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="permission_create">
  <a href="${ctx}/permissions/create" class="btn btn-default">Create</a>
</shiro:hasPermission>