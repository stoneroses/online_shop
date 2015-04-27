<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Role List page</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Name</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="role" items="${roleList}">
      <tr>
        <td><a href="${ctx}/roles/${role.id}">${role.name}</a></td>
        <td><shiro:hasPermission name="role:edit">
            <a href="${ctx}/roles/${role.id}/edit">Edit</a>
          </shiro:hasPermission>
          <shiro:hasPermission name="role:delete">
            <a href="${ctx}/roles/${role.id}/delete">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="role:create">
  <a href="${ctx}/roles/create">create</a>
</shiro:hasPermission>
