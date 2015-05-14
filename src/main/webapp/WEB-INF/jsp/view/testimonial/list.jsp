<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<c:forEach var="testimonial" items="${testimonialPage.content}">
  <div class="row-fluid home-post">
    ${testimonial.content} <strong> - <a href="${ctx}/testimonials/${testimonial.id}">${testimonial.name}</a></strong>
  </div>
</c:forEach>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>
