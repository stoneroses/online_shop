<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Category List page</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="25px">id</th>
      <th width="150px">name</th>
      <th width="25px">description</th>
      <th width="50px">actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="category" items="${categoryList}">
      <tr>
        <td>${category.id}</td>
        <td>${category.name}</td>
        <td>${category.description}</td>
        <td><a href="${ctx}/categories/${category.id}/edit">Edit</a><br /> <a
          href="${ctx}/categories/${category.id}/delete">Delete</a><br /></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<a href="${ctx}/categories/create">create</a>
