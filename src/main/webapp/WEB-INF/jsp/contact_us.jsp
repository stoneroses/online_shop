<p>${contactUs}</p>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="formLegend" value="contact.us.message.page.title.new" />
<c:set var="formAction" value="${ctx}/contact_us" />

<%@ include file="contact_us_message/form.jsp"%>
