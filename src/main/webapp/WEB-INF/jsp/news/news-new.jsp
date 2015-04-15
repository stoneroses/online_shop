<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>New News page</title>
</head>
<body>
<h1>New News page</h1>
<form:form method="POST" commandName="news" action="${pageContext.request.contextPath}/news/create" >
<table>
<tbody>
<tr>
<td>Title:</td>
<td><form:input path="title" /></td>
<td><form:errors path="title" cssStyle="color: red;"/></td>
</tr>
<tr>
<td>Content:</td>
<td><form:input path="content" /></td>
<td><form:errors path="content" cssStyle="color: red;"/></td>
</tr>
<tr>
<td><input type="submit" value="Create" /></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>
</form:form>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>