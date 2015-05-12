<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<div class="row-fluid home-post">
  <h3>${news.title}</h3>
  <div>${news.content}</div>
  <div>Created at: <joda:format value="${news.createdDateTime}" pattern="dd MMM, YYYY -- HH:mm" /></div>
  <div>Updated at: <joda:format value="${news.updatedDateTime}" pattern="dd MMM, YYYY -- HH:mm" /></div>
</div>
<a href="${ctx}/news" class="btn btn-default"><spring:message code="actions.back" /></a>
