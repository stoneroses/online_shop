<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${not empty product.categories}">
  <c:set var="category" value="${product.categories[0]}" />
  <%@ include file="../category/category_breadcrumb.jsp"%>
</c:if>
<p>${product.name}</p>
<p>
  <spring:message code="unit.of.measure.money" />
  ${product.price}
</p>
<p>${product.discount}</p>
<p>
  <spring:message code="pigeon.sale.price.after.discount" />
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
            target="_blank" class="thumbnail"> <img src="${ctx}${fileURIRoot}${image.location}" alt="${image.name}">
          </a>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
