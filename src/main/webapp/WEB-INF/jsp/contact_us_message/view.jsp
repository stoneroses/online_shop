<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>View Contact Us Message</h1>
<p>${contactUsMessage.subject}</p>
<p>${contactUsMessage.content}</p>
<a href="${ctx}/contact_us_messages" class="btn btn-default">Contact Us Message list</a>
<shiro:hasPermission name="contact_us_message_edit">
  <a href="${ctx}/contact_us_messages/${contactUsMessage.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="contact_us_message_delete">
  <a href="${ctx}/contact_us_messages/${contactUsMessage.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>
