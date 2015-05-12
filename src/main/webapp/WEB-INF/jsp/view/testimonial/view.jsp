<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row-fluid home-post">
  <h3>${testimonial.title}</h3>
  <div>${testimonial.content}</div>
  <div>by ${testimonial.name}</div>
</div>
<a href="${ctx}/testimonials" class="btn btn-default"><spring:message code="actions.back" /></a>
