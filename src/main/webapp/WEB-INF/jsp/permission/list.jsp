<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Permission List page</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Name</th>
      <th width="25px">Permission</th>
      <th width="25px">Description</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="permission" items="${permissionList}">
      <tr>
        <td><a href="${ctx}/permissions/${permission.id}">${permission.name}</a></td>
        <td>${permission.permission}</td>
        <td>${permission.description}</td>
        <td><shiro:hasPermission name="permission:edit">
            <a href="${ctx}/permissions/${permission.id}/edit">Edit</a>
          </shiro:hasPermission>
          <shiro:hasPermission name="permission:delete">
            <a href="${ctx}/permissions/${permission.id}/delete">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<a href="${ctx}/permissions/create">create</a>
