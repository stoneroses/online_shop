<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="formLegend" value="Edit Category Page" />
<c:set var="formAction" value="${ctx}/categories/${category.id}/edit" />

<%@ include file="form.jsp"%>
