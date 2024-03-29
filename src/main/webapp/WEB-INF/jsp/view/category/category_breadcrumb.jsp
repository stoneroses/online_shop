<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ol class="breadcrumb">
  <li><a href="${ctx}/">Home</a></li>
  <c:forEach var="cate" items="${category.parents}">
    <c:if test="${category ne cate}">
      <li><a href="${ctx}/categories/${cate.id}">${cate.name}</a></li>
    </c:if>
  </c:forEach>
  <c:if test="${not empty product}">
    <li><a href="${ctx}/categories/${category.id}">${category.name}</a></li>
    <li class="active">${product.name}</li>
  </c:if>
  <c:if test="${empty product}">
    <li class="active">${category.name}</li>
  </c:if>
</ol>