<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Edit User page</h1>
<form:form method="POST" commandName="user" action="${ctx}/users/${user.id}/edit">
  <table>
    <tbody>
      <tr>
        <td>Name:</td>
        <td><form:input path="name" /></td>
        <td><form:errors path="name" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td>Name:</td>
        <td><form:input path="password" /></td>
        <td><form:errors path="password" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td>Description:</td>
        <td><form:input path="description" /></td>
        <td><form:errors path="description" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td><input type="submit" value="Update" /></td>
        <td></td>
        <td></td>
      </tr>
    </tbody>
  </table>
</form:form>
<a href="${ctx}/users">user list</a>