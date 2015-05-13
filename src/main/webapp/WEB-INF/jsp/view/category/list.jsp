<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:forEach var="category" items="${categoryPage.content}">
  <div class="row">
    <a href="${ctx}/categories/${category.id}">${category.name}</a> ${category.description}
  </div>
</c:forEach>
