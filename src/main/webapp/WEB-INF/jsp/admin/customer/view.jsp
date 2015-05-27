<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
</h1>
<p>${customer.name}</p>
<p>${customer.description}</p>
<p>
  <c:forEach var="customerGroup" items="${customer.customerGroups}">
    <span class="label label-default">${customerGroup.name}</span>
  </c:forEach>
</p>
<shiro:hasPermission name="customer_list">
  <a href="${ctx}/admin/customers" class="btn btn-default"><spring:message code="customer.list" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="customer_edit">
  <a href="${ctx}/admin/customers/${customer.id}/edit" class="btn btn-default"><spring:message
      code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="customer_delete">
  <a href="${ctx}/admin/customers/${customer.id}/delete" class="btn btn-default"><spring:message
      code="admin.actions.delete" /></a>
</shiro:hasPermission>