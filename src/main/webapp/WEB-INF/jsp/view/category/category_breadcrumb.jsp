<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ol class="breadcrumb">
  <li><a href="${ctx}/">Home</a></li>
  <c:forEach var="cate" items="${category.parents}">
    <li><a href="${ctx}/categories/${cate.id}">${cate.name}</a></li>
  </c:forEach>
</ol>