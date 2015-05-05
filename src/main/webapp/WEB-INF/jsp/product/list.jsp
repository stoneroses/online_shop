<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Product List</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Name</th>
      <th width="25px">Description</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="product" items="${productList}">
      <tr>
        <td><a href="${ctx}/products/${product.id}">${product.name}</a></td>
        <td>${product.description}</td>
        <td><shiro:hasPermission name="product_edit">
            <a href="${ctx}/products/${product.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission> <shiro:hasPermission name="product_delete">
            <a href="${ctx}/products/${product.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="product_create">
  <a href="${ctx}/products/create" class="btn btn-default">Create</a>
</shiro:hasPermission>