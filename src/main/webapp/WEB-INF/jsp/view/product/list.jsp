<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
  <c:forEach var="product" items="${productPage.content}">
    <div class="col-md-3">
      <div class="thumbnail">
        <div class="wrapper">
          <div class="caption post-content">${product.name}</div>
        </div>
        <a href="${ctx}/pigeon_sales/${product.id}"> <img alt="${product.description}"
          src="${fileURIRoot}/${product.imageLocation}">
        </a>
      </div>
    </div>
  </c:forEach>
</div>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>
