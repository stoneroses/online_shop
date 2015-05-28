<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="email.job.title" /></th>
      <th><spring:message code="email.job.customer.group" /></th>
      <th><spring:message code="email.job.activated" /></th>
      <th><spring:message code="email.job.success" /></th>
      <th><spring:message code="email.job.failure" /></th>
      <th><spring:message code="email.job.created.date.time" /></th>
      <th><spring:message code="email.job.executed.date.time" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="emailJob" items="${emailJobPage.content}">
      <tr>
        <td><a href="${ctx}/admin/email_jobs/${emailJob.id}">${emailJob.title}</a></td>
        <td><c:forEach var="customerGroup" items="${emailJob.customerGroups}">
            <span class="label label-default">${customerGroup.name}</span>
          </c:forEach></td>
        <td>${emailJob.activated}</td>
        <td>${emailJob.success}</td>
        <td>${emailJob.failure}</td>
        <td>Created at: <joda:format value="${emailJob.createdDateTime}" pattern="dd MMM, YYYY -- HH:mm" /></td>
        <td>executed at: <joda:format value="${emailJob.executedDateTime}" pattern="dd MMM, YYYY -- HH:mm" /></td>
        <td><shiro:hasPermission name="email_job_edit">
            <a href="${ctx}/admin/email_jobs/${emailJob.id}/edit" class="btn btn-default"><spring:message
                code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="email_job_delete">
            <a href="${ctx}/admin/email_jobs/${emailJob.id}/delete" class="btn btn-default"><spring:message
                code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="email_job_create">
  <a href="${ctx}/admin/email_jobs/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>
