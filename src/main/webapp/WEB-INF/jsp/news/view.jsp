<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<h1>View News page</h1>
<p>${news.title}</p>
<p>${news.content}</p>
<a href="${ctx}/news">News list</a>
<shiro:hasRole name="user"><a href="${ctx}/news/${news.id}/edit">Edit</a></shiro:hasRole>
<shiro:hasRole name="admin"><a href="${ctx}/news/${news.id}/delete">Delete</a></shiro:hasRole>
