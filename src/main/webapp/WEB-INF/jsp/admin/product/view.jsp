<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<p>${product.name}</p>
<p>${product.sortOrder}</p>
<p>${product.reference}</p>
<p>${product.weight}
  <spring:message code="unit.of.measure.weight" />
</p>
<p>
  <spring:message code="unit.of.measure.money" />
  ${product.price}
</p>
<p>${product.discount}</p>
<p>
  <spring:message code="product.price.after.discount" />
  :
  <spring:message code="unit.of.measure.money" />
  ${product.nowPrice}
</p>
<p>${product.description}</p>
<div class="form-group">
  <label for="imagesThumbnail" class="col-sm-12"><spring:message code="image.thumbnail" /></label>
  <div class="col-sm-12">
    <div id="imagesThumbnail" class="row">

      <c:forEach var="image" items="${product.images}" varStatus="row">

        <div class="col-xs-6 col-md-3">
          <input type="hidden" name="images" value="${image.id}" /> <a href="${ctx}${fileURIRoot}${image.location}"
            target="_blank" class="thumbnail"> <img src="${ctx}${fileURIRoot}${resizeResolution}${image.location}" alt="${image.name}">
          </a>
        </div>

      </c:forEach>

    </div>
  </div>
</div>

<a href="${ctx}/admin/products" class="btn btn-default"><spring:message code="product.list" /></a>
<shiro:hasPermission name="product_edit">
  <a href="${ctx}/admin/products/${product.id}/edit" class="btn btn-default"><spring:message
      code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="product_delete">
  <a href="${ctx}/admin/products/${product.id}/delete" class="btn btn-default"><spring:message
      code="admin.actions.delete" /></a>
</shiro:hasPermission>