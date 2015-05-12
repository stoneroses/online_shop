<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="contact.us.message.page.title.view" />
</h1>
<p>${contactUsMessage.title} ${contactUsMessage.firstName} ${contactUsMessage.phone}</p>
<p>${contactUsMessage.phone}</p>
<p>${contactUsMessage.email}</p>
<p>${contactUsMessage.subject}</p>
<p>${contactUsMessage.content}</p>
<a href="${ctx}/contact_us_messages" class="btn btn-default"><spring:message code="contact.us.message.list" /></a>
<shiro:hasPermission name="contact_us_message_edit">
  <a href="${ctx}/contact_us_messages/${contactUsMessage.id}/edit" class="btn btn-default"><spring:message
      code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="contact_us_message_delete">
  <a href="${ctx}/contact_us_messages/${contactUsMessage.id}/delete" class="btn btn-default"><spring:message
      code="admin.actions.delete" /></a>
</shiro:hasPermission>
