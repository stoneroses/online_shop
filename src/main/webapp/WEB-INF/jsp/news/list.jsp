<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>News List page</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="25px">id</th>
      <th width="150px">title</th>
      <th width="25px">content</th>
      <th width="50px">actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="news" items="${newsList}">
      <tr>
        <td>${news.id}</td>
        <td>${news.title}</td>
        <td>${news.content}</td>
        <td><a href="${ctx}/news/${news.id}/edit">Edit</a><br /> <a
          href="${ctx}/news/${news.id}/delete">Delete</a><br /></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<a href="${ctx}/news/create">create</a>
