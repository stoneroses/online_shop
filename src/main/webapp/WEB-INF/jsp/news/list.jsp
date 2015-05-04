<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>News List page</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Title</th>
      <th width="25px">Content</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="news" items="${newsList}">
      <tr>
        <td><a href="${ctx}/news/${news.id}">${news.title}</a></td>
        <td>${news.content}</td>
        <td><shiro:hasPermission name="news_edit">
            <a href="${ctx}/news/${news.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission>
          <shiro:hasPermission name="news_delete">
            <a href="${ctx}/news/${news.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="news_create">
  <a href="${ctx}/news/create" class="btn btn-default">Create</a>
</shiro:hasPermission>