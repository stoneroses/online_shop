<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>View Category</h1>
<p>${category.name}</p>
<table id="productsTable" class="table table-striped table-hover ">
  <thead>
    <tr>
      <th>Name</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="product" items="${category.products}" varStatus="row">
      <tr>
        <td><input type="hidden" name="products" value="${product.id}">${product.name}</td>
        <td>${product.description}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<p>${category.description}</p>
<a href="${ctx}/categories" class="btn btn-default">Category list</a>
<shiro:hasPermission name="category_edit">
  <a href="${ctx}/categories/${category.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="category_delete">
  <a href="${ctx}/categories/${category.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>
