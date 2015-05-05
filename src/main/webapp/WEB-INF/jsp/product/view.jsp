<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>View Product</h1>
<p>${product.name}</p>
<p>${product.reference}</p>
<p>${product.weight}kg</p>
<p>$${product.price}</p>
<p>${product.discount}</p>
<p>Now: $${product.nowPrice}</p>
<p>${product.description}</p>
<div class="form-group">
  <label for="imagesThumbnail" class="col-sm-12">Images Thumbnail</label>
  <div class="col-sm-12">
    <div id="imagesThumbnail" class="row">

      <c:forEach var="image" items="${product.images}" varStatus="row">

        <div class="col-xs-6 col-md-3">
          <input type="hidden" name="images" value="${image.id}" /> <a href="${ctx}${fileURIRoot}${image.location}"
            target="_blank" class="thumbnail"> <img src="${ctx}${fileURIRoot}${image.location}" alt="${image.name}">
          </a>
        </div>

      </c:forEach>

    </div>
  </div>
</div>

<a href="${ctx}/products" class="btn btn-default">Product list</a>
<shiro:hasPermission name="product_edit">
  <a href="${ctx}/products/${product.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="product_delete">
  <a href="${ctx}/products/${product.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>
