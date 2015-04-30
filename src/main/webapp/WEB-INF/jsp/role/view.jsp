<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>View Role page</h1>
<p>${role.name}</p>
<p>${role.description}</p>
<c:forEach var="permission" items="${role.permissions}">
  <span class="label label-default">${permission.name}</span>
</c:forEach>
<a href="${ctx}/roles">Role list</a>
<a href="${ctx}/roles/${role.id}/edit">Edit</a>
<a href="${ctx}/roles/${role.id}/delete">Delete</a>
