<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="500" />
<tiles:insertDefinition name="error/500" />

<script type="text/javascript">
  <c:forEach items="${pageContext.exception.stackTrace}" var="element">
  $("#detailErrorMessage").append("<c:out value="${element}" /><br />");
  </c:forEach>
  $("#errorMessage").append("${pageContext.exception}");
</script>