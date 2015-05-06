<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<h1>Setting List</h1>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th width="150px">Key</th>
      <th width="25px">Value</th>
      <th width="50px">Actions</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="setting" items="${settingList}">
      <tr>
        <td><a href="${ctx}/settings/${setting.id}">${setting.key}</a></td>
        <td>${setting.value}</td>
        <td><shiro:hasPermission name="setting_edit">
            <a href="${ctx}/settings/${setting.id}/edit" class="btn btn-default">Edit</a>
          </shiro:hasPermission> <shiro:hasPermission name="setting_delete">
            <a href="${ctx}/settings/${setting.id}/delete" class="btn btn-default">Delete</a>
          </shiro:hasPermission></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<shiro:hasPermission name="setting_create">
  <a href="${ctx}/settings/create" class="btn btn-default">Create</a>
</shiro:hasPermission>