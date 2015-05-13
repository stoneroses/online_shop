<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="pigeon.sale.list" />
</h1>
<div class="row">
  <c:forEach var="pigeonSale" items="${pigeonSalePage.content}">
    <div class="col-md-3">
      <div class="thumbnail">
        <div class="wrapper">
          <div class="caption post-content">${pigeonSale.name}</div>
        </div>
        <a href="${ctx}/pigeon_sales/${pigeonSale.id}"> <img alt="${pigeonSale.description}"
          src="${fileURIRoot}/${pigeonSale.imageLocation}">
        </a>
      </div>
    </div>
  </c:forEach>
</div>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>
