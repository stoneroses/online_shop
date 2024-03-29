<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<p>${pigeonSale.name}</p>
<p>${pigeonSale.reference}</p>
<p>${pigeonSale.father}</p>
<p>${pigeonSale.mother}</p>
<p>
  <spring:message code="unit.of.measure.money" />
  ${pigeonSale.price}
</p>
<p>${pigeonSale.discount}</p>
<p>
  <spring:message code="pigeon.sale.price.after.discount" />
  :
  <spring:message code="unit.of.measure.money" />
  ${pigeonSale.nowPrice}
</p>
<a href="${ctx}/contact_us?subject=${pigeonSale.name}" class="btn btn-default">Contact Us</a>
<p>${pigeonSale.description}</p>
<div class="form-group">
  <label for="imagesThumbnail" class="col-sm-12"><spring:message code="image.thumbnail" /></label>
  <div class="col-sm-12">
    <div id="imagesThumbnail" class="row">
      <c:forEach var="image" items="${pigeonSale.images}" varStatus="row">
        <div class="col-xs-6 col-md-3">
          <input type="hidden" name="images" value="${image.id}" /> <a href="${ctx}${fileURIRoot}${image.location}"
            target="_blank" class="thumbnail"> <img src="${ctx}${fileURIRoot}${resizeResolution}${image.location}" alt="${image.name}">
          </a>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
