<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="formLegend" value="Edit Role Page" />
<c:set var="formAction" value="${ctx}/roles/${role.id}/edit" />

<%@ include file="form.jsp"%>
