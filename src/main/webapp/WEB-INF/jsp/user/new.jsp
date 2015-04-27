<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>New User page</h1>
<form:form method="POST" commandName="user" action="${ctx}/users/create">
  <table>
    <tbody>
      <tr>
        <td>Name:</td>
        <td><form:input path="name" /></td>
        <td><form:errors path="name" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><form:input path="email" /></td>
        <td><form:errors path="email" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><form:input path="password" /></td>
        <td><form:errors path="password" cssStyle="color: red;" /></td>
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
<a href="${ctx}/users">user list</a>
