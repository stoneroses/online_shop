<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Contact Us Message List</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th>Subject</th>
      <th>Content</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="contactUsMessage" items="${contactUsMessageList}">
      <tr>
        <td><a href="${ctx}/contact_us_messages/${contactUsMessage.id}">${contactUsMessage.subject}</a></td>
        <td>${contactUsMessage.content}</td>
        <td><shiro:hasPermission name="contact_us_message_edit">
            <a href="${ctx}/contact_us_messages/${contactUsMessage.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission> <shiro:hasPermission name="contact_us_message_delete">
            <a href="${ctx}/contact_us_messages/${contactUsMessage.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="contact_us_message_create">
  <a href="${ctx}/contact_us_messages/create" class="btn btn-default">Create</a>
</shiro:hasPermission>