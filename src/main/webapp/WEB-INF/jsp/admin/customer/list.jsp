<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="customer.title" /></th>
      <th><spring:message code="customer.name" /></th>
      <th><spring:message code="customer.email" /></th>
      <th><spring:message code="customer.subscribe" /></th>
      <th><spring:message code="customer.group" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="customer" items="${customerPage.content}">
      <tr>
        <td>${customer.title}</td>
        <td><a href="${ctx}/admin/customers/${customer.id}">${customer.name}</a></td>
        <td>${customer.email}</td>
        <td>${customer.subscribe}</td>
        <td><c:forEach var="customerGroup" items="${customer.customerGroups}">
            <span class="label label-default">${customerGroup.name}</span>
          </c:forEach></td>
        <td><shiro:hasPermission name="customer_edit">
            <a href="${ctx}/admin/customers/${customer.id}/edit" class="btn btn-default"><spring:message
                code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="customer_delete">
            <a href="${ctx}/admin/customers/${customer.id}/delete" class="btn btn-default"><spring:message
                code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="customer_create">
  <a href="${ctx}/admin/customers/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>
