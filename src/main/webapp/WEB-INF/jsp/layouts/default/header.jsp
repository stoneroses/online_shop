<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="${ctx}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${ctx}"><spring:message code="page.nav.home" /></a></li>
        <li><a href="${ctx}/equipments"><spring:message code="page.nav.equipments" /></a></li>
        <li><a href="${ctx}/pigeon_sales"><spring:message code="page.nav.pigeon.sales" /></a></li>
        <li><a href="${ctx}/news"><spring:message code="page.nav.news" /></a></li>
        <li><a href="${ctx}/testimonial"><spring:message code="page.nav.testimonial" /></a></li>
        <li><a href="${ctx}/about_us"><spring:message code="page.nav.about" /></a></li>
        <li><a href="${ctx}/contact_us"><spring:message code="page.nav.contact" /></a></li>
        <li><a href="${ctx}/strayed_pigeons"><spring:message code="page.nav.found.strayed.pigeons" /></a></li>
        <li><a href="${ctx}/"></a></li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
          aria-expanded="false"><spring:message code="page.nav.links" /><span class="caret"></span></a>
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