<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>User List page</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Name</th>
      <th width="25px">Description</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="user" items="${userList}">
      <tr>
        <td><a href="${ctx}/users/${user.id}">${user.name}</a></td>
        <td>${user.description}</td>
        <td><a href="${ctx}/users/${user.id}/edit">Edit</a> <a
          href="${ctx}/users/${user.id}/delete">Delete</a></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<a href="${ctx}/users/create">create</a>
