<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>New Permission page</h1>
<form:form method="POST" commandName="permission" action="${ctx}/permissions/create">
  <table>
    <tbody>
      <tr>
        <td>Name:</td>
        <td><form:input path="name" /></td>
        <td><form:errors path="name" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td>Permission:</td>
        <td><form:input path="permission" /></td>
        <td><form:errors path="permission" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td>Description:</td>
        <td><form:input path="description" /></td>
        <td><form:errors path="description" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td><input type="submit" value="Create" /></td>
        <td></td>
        <td></td>
      </tr>
    </tbody>
  </table>
</form:form>
<a href="${ctx}/permissions">permission list</a>
