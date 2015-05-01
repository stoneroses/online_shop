<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty message}">
  <p class="bg-success message">${message}</p>
</c:if>
<c:if test="${not empty errorMessage}">
  <p class="bg-danger message">${errorMessage}</p>
</c:if>
