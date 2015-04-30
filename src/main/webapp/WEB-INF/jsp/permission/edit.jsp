<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="formLegend" value="Edit Permission Page" />
<c:set var="formAction" value="${ctx}/permissions/${permission.id}/edit" />

<%@ include file="form.jsp"%>
