<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <td><shiro:hasRole name="user">
            <a href="${ctx}/news/${news.id}/edit">Edit</a>
          </shiro:hasRole>
          <shiro:hasRole name="admin">
            <a href="${ctx}/news/${news.id}/delete">Delete</a>
          </shiro:hasRole></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasRole name="user">
  <a href="${ctx}/news/create">create</a>
</shiro:hasRole>