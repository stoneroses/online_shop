<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<h1>View News</h1>
<p>${news.title}</p>
<p>${news.content}</p>
<a href="${ctx}/news" class="btn btn-default">News list</a>
<shiro:hasPermission name="news_edit">
  <a href="${ctx}/news/${news.id}/edit" class="btn btn-default">Edit</a>
</shiro:hasPermission>
<shiro:hasPermission name="news_delete">
  <a href="${ctx}/news/${news.id}/delete" class="btn btn-default">Delete</a>
</shiro:hasPermission>
