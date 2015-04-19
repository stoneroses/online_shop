<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" scope="page" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
  <table>
    <tr>
      <td colspan="2"><tiles:insertAttribute name="header" /></td>
    </tr>
    <tr>
      <td><tiles:insertAttribute name="menu" /></td>
      <td><tiles:insertAttribute name="body" /></td>
    </tr>
    <tr>
      <td colspan="2"><tiles:insertAttribute name="footer" /></td>
    </tr>
  </table>
</body>
</html>