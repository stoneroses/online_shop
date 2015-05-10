<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="pigeon.sale.list" /></h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="pigeon.sale.name" /></th>
      <th><spring:message code="pigeon.sale.description" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="pigeonSale" items="${pigeonSalePage.content}">
      <tr>
        <td><a href="${ctx}/pigeon_sales/${pigeonSale.id}">${pigeonSale.name}</a></td>
        <td>${pigeonSale.description}</td>
        <td><shiro:hasPermission name="pigeon_sale_edit">
            <a href="${ctx}/pigeon_sales/${pigeonSale.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="pigeon_sale_delete">
            <a href="${ctx}/pigeon_sales/${pigeonSale.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="pigeon_sale_create">
  <a href="${ctx}/pigeon_sales/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>