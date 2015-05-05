<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>View Product</h1>
<p>${product.name}</p>
<p>${product.reference}</p>
<p>${product.weight}kg</p>
<p>$${product.price}</p>
<p>${product.discount}</p>
<p>Now: $${product.nowPrice}</p>
<p>${product.description}</p>
<a href="${ctx}/products" class="btn btn-default">Product list</a>
<shiro:hasPermission name="product_edit">
  <a href="${ctx}/products/${product.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="product_delete">
  <a href="${ctx}/products/${product.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>
