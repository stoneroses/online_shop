<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>New News page</h1>
<form:form method="POST" commandName="news" action="${ctx}/news/create">
  <table>
    <tbody>
      <tr>
        <td>Title:</td>
        <td><form:input path="title" /></td>
        <td><form:errors path="title" cssClass="text-danger" /></td>
      </tr>
      <tr>
        <td>Content:</td>
        <td><form:input path="content" /></td>
        <td><form:errors path="content" cssClass="text-danger" /></td>
      </tr>
      <tr>
        <td><input type="submit" value="Create" /></td>
        <td></td>
        <td></td>
      </tr>
    </tbody>
  </table>
</form:form>
<a href="${ctx}/news">news list</a>
