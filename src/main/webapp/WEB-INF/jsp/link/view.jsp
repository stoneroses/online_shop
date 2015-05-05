<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>View Link</h1>
<p>${link.name}</p>
<p>${link.url}</p>
<p>${link.newWindow}</p>
<a href="${ctx}/links" class="btn btn-default">Link list</a>
<shiro:hasPermission name="link_edit">
  <a href="${ctx}/links/${link.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="link_delete">
  <a href="${ctx}/links/${link.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>
