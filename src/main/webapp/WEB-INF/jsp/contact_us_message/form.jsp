<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="contactUsMessage" action="${formAction}" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>${formLegend}</legend>
        <div class="form-group">
          <label for="subject" class="col-sm-2 control-label">Subject</label>
          <div class="col-sm-10">
            <form:input path="subject" class="form-control" />
            <form:errors path="subject" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="content" class="col-sm-2 control-label">Content</label>
          <div class="col-sm-10">
            <form:textarea path="content" class="form-control" />
            <form:errors path="content" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Save Contact Us Message</button>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>
</div>
<shiro:hasPermission name="contact_us_message_list">
<a href="${ctx}/contact_us_messages" class="btn btn-default">Contact Us Message List</a>
</shiro:hasPermission>