<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ include file="category_breadcrumb.jsp"%>

<h3>${category.name}</h3>
<p>${category.description}</p>
<c:forEach var="categoryChild" items="${category.children}" varStatus="row">
  <div class="row">
    <a href="${ctx}/categories/${categoryChild.id}">${categoryChild.name}</a> ${categoryChild.description}
  </div>
</c:forEach>
<div class="row">
  <c:forEach var="product" items="${category.products}" varStatus="row">
    <div class="col-md-3">
      <div class="thumbnail">
        <a href="${ctx}/products/${product.id}"> <img alt="${product.name}"
          src="${fileURIRoot}/${product.imageLocation}">
          <p>${product.name}</p>
        </a>
      </div>
    </div>
  </c:forEach>
</div>
