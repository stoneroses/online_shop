<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<p>${emailJob.title}</p>
<p>${emailJob.content}</p>
<p>${emailJob.activated}</p>
<p>${emailJob.success}</p>
<p>${emailJob.failure}</p>
<p>
  Created at:
  <joda:format value="${emailJob.createdDateTime}" pattern="dd MMM, YYYY -- HH:mm" />
</p>
<p>
  executed at:
  <joda:format value="${emailJob.executedDateTime}" pattern="dd MMM, YYYY -- HH:mm" />
</p>
<p>
  <c:forEach var="customerGroup" items="${emailJob.customerGroups}">
    <span class="label label-default">${customerGroup.name}</span>
  </c:forEach>
</p>
<shiro:hasPermission name="email_job_list">
  <a href="${ctx}/admin/email_jobs" class="btn btn-default"><spring:message code="email.job.list" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="email_job_edit">
  <a href="${ctx}/admin/email_jobs/${emailJob.id}/edit" class="btn btn-default"><spring:message
      code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="email_job_delete">
  <a href="${ctx}/admin/email_jobs/${emailJob.id}/delete" class="btn btn-default"><spring:message
      code="admin.actions.delete" /></a>
</shiro:hasPermission>