<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>User List page</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Name</th>
      <th width="25px">Email</th>
      <th width="25px">Description</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="user" items="${userList}">
      <tr>
        <td><a href="${ctx}/users/${user.id}">${user.name}</a></td>
        <td>${user.email}</td>
        <td>${user.description}</td>
        <td><shiro:hasPermission name="user_edit">
            <a href="${ctx}/users/${user.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission>
          <shiro:hasPermission name="user_delete">
            <a href="${ctx}/users/${user.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="user_create">
  <a href="${ctx}/users/create" class="btn btn-default">Create</a>
</shiro:hasPermission>
