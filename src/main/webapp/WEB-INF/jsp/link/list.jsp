<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Link List</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Name</th>
      <th width="25px">URL</th>
      <th width="25px">Open In New Window</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="link" items="${linkList}">
      <tr>
        <td><a href="${ctx}/links/${link.id}">${link.name}</a></td>
        <td>${link.url}</td>
        <td>${link.newWindow}</td>
        <td><shiro:hasPermission name="link_edit">
            <a href="${ctx}/links/${link.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission> <shiro:hasPermission name="link_delete">
            <a href="${ctx}/links/${link.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="link_create">
  <a href="${ctx}/links/create" class="btn btn-default">Create</a>
</shiro:hasPermission>