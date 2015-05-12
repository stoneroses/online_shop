<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="setting.list" /></h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="setting.key" /></th>
      <th><spring:message code="setting.value" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="setting" items="${settingPage.content}">
      <tr>
        <td><a href="${ctx}/admin/settings/${setting.id}">${setting.key}</a></td>
        <td>${setting.value}</td>
        <td><shiro:hasPermission name="setting_edit">
            <a href="${ctx}/admin/settings/${setting.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="setting_delete">
            <a href="${ctx}/admin/settings/${setting.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="setting_create">
  <a href="${ctx}/admin/settings/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>