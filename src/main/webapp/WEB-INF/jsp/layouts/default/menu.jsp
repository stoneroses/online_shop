<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-green panel-default small">
  <div class="panel-heading ">Categories</div>
  <div class="list-group">
    <c:forEach var="category" items="${categoryList}">
      <a href="${ctx}/categories/${category.id}" class="list-group-item"><span class="badge">${category.products.size()}</span>${category.name}</a>
    </c:forEach>
  </div>
</div>


