<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="testimonial.page.title.view" /></h1>
<p>${testimonial.title}</p>
<p>${testimonial.content}</p>
<a href="${ctx}/testimonials" class="btn btn-default"><spring:message code="testimonial.list" /></a>
<shiro:hasPermission name="testimonial_edit">
  <a href="${ctx}/testimonials/${testimonial.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="testimonial_delete">
  <a href="${ctx}/testimonials/${testimonial.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
</shiro:hasPermission>