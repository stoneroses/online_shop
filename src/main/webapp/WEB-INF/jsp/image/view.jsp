<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
  <spring:message code="image.page.title.view" />
</h1>
<p>${image.name}</p>
<p>${image.description}</p>
<a href="${ctx}${fileURIRoot}${image.location}" target="_blank"> <img src="${ctx}${fileURIRoot}${image.location}"
  class="img-thumbnail" />
</a>
<a href="${ctx}/images" class="btn btn-default"><spring:message code="image.list" /></a>
<shiro:hasPermission name="image_edit">
  <a href="${ctx}/images/${image.id}/edit" class="btn btn-default"><spring:message code="admin.actions.edit" /></a>
</shiro:hasPermission>
<shiro:hasPermission name="image_delete">
  <a href="${ctx}/images/${image.id}/delete" class="btn btn-default"><spring:message code="admin.actions.delete" /></a>
</shiro:hasPermission>
