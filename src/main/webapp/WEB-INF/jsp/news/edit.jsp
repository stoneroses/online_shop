<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="formLegend" value="Edit News Page" />
<c:set var="formAction" value="${ctx}/news/${news.id}/edit" />

<%@ include file="form.jsp"%>
