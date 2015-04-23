<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Category List page</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Name</th>
      <th width="25px">Description</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="category" items="${categoryList}">
      <tr>
        <td><a href="${ctx}/categories/${category.id}">${category.name}</a></td>
        <td>${category.description}</td>
        <td><a href="${ctx}/categories/${category.id}/edit">Edit</a> <a
          href="${ctx}/categories/${category.id}/delete">Delete</a></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<a href="${ctx}/categories/create">create</a>
