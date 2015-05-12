<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="category.page.title.view" arguments="${pageTitleArg}"/></h1>
<p>
  <spring:message code="category.parent" />: <a href="${ctx}/categories/${category.parent.id}">${category.parent.name}</a>
</p>
<p>${category.name}</p>
<table id="categoriesTable" class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="category" /> <spring:message code="category.name" /></th>
      <th><spring:message code="category.description" /></th>
      <th><spring:message code="admin.page.list.actions" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="categoryChild" items="${category.children}" varStatus="row">
      <tr>
        <td><a href="${ctx}/categories/${categoryChild.id}">${categoryChild.name}</a></td>
        <td>${categoryChild.description}</td>
        <td><shiro:hasPermission name="category_edit">
            <a href="${ctx}/categories/${categoryChild.id}/edit" class="btn btn-default"><spring:message
                code="admin.actions.edit" /></a>
          </shiro:hasPermission> <shiro:hasPermission name="category_delete">
            <a href="${ctx}/categories/${categoryChild.id}/delete" class="btn btn-default"><spring:message
                code="admin.actions.delete" /></a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<table id="productsTable" class="table table-striped table-hover ">
  <thead>
    <tr>
      <th><spring:message code="product" /> <spring:message code="product.name" /></th>
      <th><spring:message code="product.description" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="product" items="${category.products}" varStatus="row">
      <tr>
        <td><input type="hidden" name="products" value="${product.id}"><a href="${ctx}/products/${product.id}">${product.name}</a></td>
        <td>${product.description}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<p>${category.description}</p>
<a href="${ctx}/categories" class="btn btn-default"><spring:message code="category.list" /></a>
<shiro:hasPermission name="category_edit">
  <a href="${ctx}/categories/${category.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="category_delete">
  <a href="${ctx}/categories/${category.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
</shiro:hasPermission>
