<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Edit Role page</h1>
<form:form method="POST" commandName="role" action="${ctx}/roles/${role.id}/edit">
  <table>
    <tbody>
      <tr>
        <td>Name:</td>
        <td><form:input path="name" /></td>
        <td><form:errors path="name" cssStyle="color: red;" /></td>
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
<a href="${ctx}/roles">role list</a>
