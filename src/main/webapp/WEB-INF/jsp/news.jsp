<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Getting Started: Serving Web Content</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
  <c:forEach var="news" items="${newsList}">
    <div>
      <h3>${news.createdDateTime}</h3>
      <h3>${news.updatedDateTime}</h3>
      <p>${news.title}</p>
      <p>${news.content}</p>
    </div>
  </c:forEach>
</body>
</html>
