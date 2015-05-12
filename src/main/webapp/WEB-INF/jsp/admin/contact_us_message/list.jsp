<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="contact.us.message.list" />
</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="contact.us.message.name" /></th>
      <th><spring:message code="contact.us.message.phone" /></th>
      <th><spring:message code="contact.us.message.subject" /></th>
      <th><spring:message code="contact.us.message.content" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="contactUsMessage" items="${contactUsMessagePage.content}">
      <tr>
        <td>${contactUsMessage.firstName} ${contactUsMessage.lastName}</td>
        <td>${contactUsMessage.phone}</td>
        <td><a href="${ctx}/admin/contact_us_messages/${contactUsMessage.id}">${contactUsMessage.subject}</a></td>
        <td>${contactUsMessage.content}</td>
        <td><shiro:hasPermission name="contact_us_message_edit">
            <a href="${ctx}/admin/contact_us_messages/${contactUsMessage.id}/edit" class="btn btn-default"><spring:message
                code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="contact_us_message_delete">
            <a href="${ctx}/admin/contact_us_messages/${contactUsMessage.id}/delete" class="btn btn-default"><spring:message
                code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="contact_us_message_create">
  <a href="${ctx}/admin/contact_us_messages/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>