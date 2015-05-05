<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
        aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
          class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${ctx}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${ctx}">Home</a></li>
        <li><a href="${ctx}/equipments">Equipments</a></li>
        <li><a href="${ctx}/pigeon_sales">Pigeon Sales</a></li>
        <li><a href="${ctx}/news">News</a></li>
        <li><a href="${ctx}/testimonial">Testimonial</a></li>
        <li><a href="${ctx}/about_us">About</a></li>
        <li><a href="${ctx}/contact_us">Contact</a></li>
        <li><a href="${ctx}/strayed_pigeons">Found Strayed Pigeons</a></li>
        <li><a href="${ctx}/"></a></li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
          aria-expanded="false">Links<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <c:forEach var="link" items="${linkList}">
              <li><a href="${link.url}" class="list-group-item" <c:if test="${link.newWindow}"> target="_blank" </c:if> >${link.name}</a></li>
            </c:forEach>
          </ul></li>
      </ul>
    </div>
    <!--/.container-fluid -->
  </div>
  <!--/.nav-collapse -->
</nav>