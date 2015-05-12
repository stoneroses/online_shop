<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="pigeon.sale.page.title.view" /></h1>
<p>${pigeonSale.name}</p>
<p>${pigeonSale.reference}</p>
<p>${pigeonSale.father}</p>
<p>${pigeonSale.mother}</p>
<p><spring:message code="unit.of.measure.money" /> ${pigeonSale.price}</p>
<p>${pigeonSale.discount}</p>
<p><spring:message code="pigeon.sale.price.after.discount" />: <spring:message code="unit.of.measure.money" /> ${pigeonSale.nowPrice}</p>
<p>${pigeonSale.description}</p>
<div class="form-group">
  <label for="imagesThumbnail" class="col-sm-12"><spring:message code="image.thumbnail" /></label>
  <div class="col-sm-12">
    <div id="imagesThumbnail" class="row">

      <c:forEach var="image" items="${pigeonSale.images}" varStatus="row">

        <div class="col-xs-6 col-md-3">
          <input type="hidden" name="images" value="${image.id}" /> <a href="${ctx}${fileURIRoot}${image.location}"
            target="_blank" class="thumbnail"> <img src="${ctx}${fileURIRoot}${image.location}" alt="${image.name}">
          </a>
        </div>

      </c:forEach>

    </div>
  </div>
</div>

<a href="${ctx}/admin/pigeon_sales" class="btn btn-default"><spring:message code="pigeon.sale.list" /></a>
<shiro:hasPermission name="pigeon_sale_edit">
  <a href="${ctx}/admin/pigeon_sales/${pigeonSale.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="pigeon_sale_delete">
  <a href="${ctx}/admin/pigeon_sales/${pigeonSale.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
</shiro:hasPermission>