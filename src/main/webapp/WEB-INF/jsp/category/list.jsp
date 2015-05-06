<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Category List</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th>Name</th>
      <th>Description</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="category" items="${categoryList}">
      <tr>
        <td><a href="${ctx}/categories/${category.id}">${category.name}</a></td>
        <td>${category.description}</td>
        <td><shiro:hasPermission name="category_edit">
            <a href="${ctx}/categories/${category.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission> <shiro:hasPermission name="category_delete">
            <a href="${ctx}/categories/${category.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="category_create">
  <a href="${ctx}/categories/create" class="btn btn-default">Create</a>
</shiro:hasPermission>