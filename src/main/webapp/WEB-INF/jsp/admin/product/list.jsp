<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="product.list" /></h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="product.name" /></th>
      <th><spring:message code="product.description" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="product" items="${productPage.content}">
      <tr>
        <td><a href="${ctx}/admin/products/${product.id}">${product.name}</a></td>
        <td>${product.description}</td>
        <td><shiro:hasPermission name="product_edit">
            <a href="${ctx}/admin/products/${product.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="product_delete">
            <a href="${ctx}/admin/products/${product.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>

<shiro:hasPermission name="product_create">
  <a href="${ctx}/admin/products/create" class="btn btn-default"><spring:message code="admin.actions.create" /></a>
</shiro:hasPermission>