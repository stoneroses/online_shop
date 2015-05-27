<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<p>${customerGroup.name}</p>
<p>${customerGroup.description}</p>
<table id="customerGroupsTable" class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="customer.title" /> <spring:message code="customer.name" /></th>
      <th><spring:message code="customer.name" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="customer" items="${customerGroup.customers}" varStatus="row">
      <tr>
        <td><a href="${ctx}/admin/customers/${customer.id}">${customer.name}</a></td>
        <td>${customer.email}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<a href="${ctx}/admin/customer_groups" class="btn btn-default"><spring:message code="customer.group.list" /></a>
<shiro:hasPermission name="customer_group_edit">
  <a href="${ctx}/admin/customer_groups/${customerGroup.id}/edit" class="btn btn-default"><spring:message
      code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="customer_group_delete">
  <a href="${ctx}/admin/customer_groups/${customerGroup.id}/delete" class="btn btn-default"><spring:message
      code="admin.actions.delete" /></a>
</shiro:hasPermission>
