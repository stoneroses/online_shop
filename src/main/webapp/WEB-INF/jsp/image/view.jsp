<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>View Image</h1>
<p>${image.name}</p>
<p>${image.description}</p>
<img src="${ctx}${fileURIRoot}${image.location}"/>
<a href="${ctx}/images" class="btn btn-default">Image list</a>
<shiro:hasPermission name="image_edit">
  <a href="${ctx}/images/${image.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="image_delete">
  <a href="${ctx}/images/${image.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>
