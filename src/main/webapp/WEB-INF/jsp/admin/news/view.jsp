<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="news.page.title.view" /></h1>
<p>${news.title}</p>
<p>${news.content}</p>
<a href="${ctx}/admin/news" class="btn btn-default"><spring:message code="news.list" /></a>
<shiro:hasPermission name="news_edit">
  <a href="${ctx}/admin/news/${news.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="news_delete">
  <a href="${ctx}/admin/news/${news.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
</shiro:hasPermission>
