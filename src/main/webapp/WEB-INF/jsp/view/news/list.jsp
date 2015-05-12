<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<c:forEach var="news" items="${newsPage.content}">
  <div class="row-fluid home-post">
    <h3>
      <a href="${ctx}/news/${news.id}"><joda:format value="${news.createdDateTime}" pattern="dd MMM, YYYY -- HH:mm" /></a>
    </h3>
    ${news.content}
  </div>
</c:forEach>

<%@ include file="/WEB-INF/jsp/layouts/default/pagination.jsp"%>
