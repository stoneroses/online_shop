<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Edit News page</h1>
<form:form method="POST" commandName="news" action="${ctx}/news/${news.id}/edit">
  <table>
    <tbody>
      <tr>
        <td>Title:</td>
        <td><form:input path="title" /></td>
        <td><form:errors path="title" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td>Content:</td>
        <td><form:input path="content" /></td>
        <td><form:errors path="content" cssStyle="color: red;" /></td>
      </tr>
      <tr>
        <td><input type="submit" value="Update" /></td>
        <td></td>
        <td></td>
      </tr>
    </tbody>
  </table>
</form:form>
<a href="${ctx}/news">news list</a>
