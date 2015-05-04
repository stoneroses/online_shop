<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>View Category page</h1>
<p>${category.name}</p>
<p>${category.description}</p>
<a href="${ctx}/categories" class="btn btn-default">Category list</a>
<shiro:hasPermission name="category_edit">
  <a href="${ctx}/categories/${category.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="category_delete">
  <a href="${ctx}/categories/${category.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>
