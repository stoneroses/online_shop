<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" scope="session" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Online Shop - <tiles:insertAttribute name="title" ignore="true" /></title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="${ctx}/common/css/main.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
  <div class="container">
    <div class="container">
      <tiles:insertAttribute name="header" />
    </div>
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-3">
          <tiles:insertAttribute name="menu" ignore="true" />
        </div>
        <div class="col-lg-8 col-lg-offset-1">
          <div class="row">
            <tiles:insertAttribute name="message" />
          </div>
          <div class=row>
            <tiles:insertAttribute name="body" />
          </div>
        </div>
      </div>
    </div>
    <div class="container-fluid pull-right">
      <tiles:insertAttribute name="footer" />
    </div>
  </div>
  <script src="${ctx}/common/javascript/main.js"></script>
</body>
</html>
