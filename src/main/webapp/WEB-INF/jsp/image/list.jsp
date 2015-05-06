<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Image List</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th>Name</th>
      <th>Description</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="image" items="${imageList}">
      <tr>
        <td><a href="${ctx}/images/${image.id}">${image.name}</a></td>
        <td>${image.description}</td>
        <td><shiro:hasPermission name="image_edit">
            <a href="${ctx}/images/${image.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission> <shiro:hasPermission name="image_delete">
            <a href="${ctx}/images/${image.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="image_create">
  <a href="${ctx}/images/create" class="btn btn-default">Create</a>
</shiro:hasPermission>