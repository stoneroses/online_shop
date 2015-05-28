<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="cf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="emailJob" action="${ctx}/admin/email_jobs/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>
          <spring:message code="${pageTitle}" arguments="${pageTitleArg}" />
        </legend>
        <div class="form-group">
          <label for="title" class="col-sm-2 control-label"><spring:message code="email.job.title" /></label>
          <div class="col-sm-10">
            <form:input path="title" class="form-control" />
            <form:errors path="title" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="activated" class="col-sm-2 control-label"><spring:message code="email.job.activated" /></label>
          <div class="col-sm-10">
            <form:checkbox path="activated" class="form-control" />
            <form:errors path="activated" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="executedDateTime" class="col-sm-2 control-label"><spring:message
              code="email.job.executed.date.time" /></label>
          <div class="col-sm-10">
            <form:input path="executedDateTime" class="form-control" />
            <form:errors path="executedDateTime" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="content" class="col-sm-2 control-label"><spring:message code="email.job.content" /></label>
          <div class="col-sm-10">
            <form:textarea path="content" class="form-control" />
            <form:errors path="content" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label"><spring:message code="email.job.customer.group" /></label>
          <div class="col-sm-10">
            <c:forEach var="customerGroup" items="${allCustomerGroups}" varStatus="row">
              <div class="checkbox">
                <label> <input name="customerGroups" type="checkbox" value="${customerGroup.id}"
                  <c:if test="${ cf:contains(emailJob.customerGroups, customerGroup)}">checked</c:if> />
                  ${customerGroup.name}
                </label>
              </div>
            </c:forEach>
            <form:errors path="customerGroups" cssClass="text-danger" />
          </div>
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
<a href="${ctx}/admin/email_jobs" class="btn btn-default"><spring:message code="email.job.list" /></a>

<script>
  $(function() {
    $("#executedDateTime").datetimepicker({
      changeMonth : true,
      changeYear : true,
      dateFormat : "dd/mm/yy",
      timeFormat: 'HH:mm',
    });
  });
  CKEDITOR.replace('content');
</script>
