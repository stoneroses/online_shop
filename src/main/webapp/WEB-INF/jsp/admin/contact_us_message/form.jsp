<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="contactUsMessage" action="${formAction}" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>
          <spring:message code="${formLegend}" />
        </legend>
        <div class="form-group">
          <label for="title" class="col-sm-2 control-label"><spring:message code="contact.us.message.title" /></label>
          <div class="col-sm-10">
            <form:input path="title" class="form-control" />
            <form:errors path="title" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="firstName" class="col-sm-2 control-label"><spring:message
              code="contact.us.message.first.name" /></label>
          <div class="col-sm-10">
            <form:input path="firstName" class="form-control" />
            <form:errors path="firstName" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="lastName" class="col-sm-2 control-label"><spring:message
              code="contact.us.message.last.name" /></label>
          <div class="col-sm-10">
            <form:input path="lastName" class="form-control" />
            <form:errors path="lastName" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="email" class="col-sm-2 control-label"><spring:message code="contact.us.message.email" /></label>
          <div class="col-sm-10">
            <form:input path="email" class="form-control" />
            <form:errors path="email" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="phone" class="col-sm-2 control-label"><spring:message code="contact.us.message.phone" /></label>
          <div class="col-sm-10">
            <form:input path="phone" class="form-control" />
            <form:errors path="phone" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="subject" class="col-sm-2 control-label"><spring:message code="contact.us.message.subject" /></label>
          <div class="col-sm-10">
            <form:input path="subject" class="form-control" />
            <form:errors path="subject" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="content" class="col-sm-2 control-label"><spring:message code="contact.us.message.content" /></label>
          <div class="col-sm-10">
            <form:textarea path="content" class="form-control" />
            <form:errors path="content" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-10 col-sm-offset-2">${captchaScript}</div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">
              <spring:message code="admin.actions.save" />
            </button>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>
</div>
<shiro:hasPermission name="contact_us_message_list">
  <a href="${ctx}/admin/contact_us_messages" class="btn btn-default"><spring:message code="contact.us.message.list" /></a>
</shiro:hasPermission>