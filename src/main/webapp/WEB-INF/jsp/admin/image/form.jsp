<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="image" action="${ctx}/admin/images/save" class="form-horizontal"
      enctype="multipart/form-data">
      <fieldset>
        <form:hidden path="id" />
        <legend>
          <spring:message code="${pageTitle}" arguments="${pageTitleArg}" text="" />
        </legend>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label"><spring:message code="image.name" /></label>
          <div class="col-sm-10">
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="description" class="col-sm-2 control-label"><spring:message code="image.description" /></label>
          <div class="col-sm-10">
            <form:input path="description" class="form-control" />
            <form:errors path="description" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="file" class="col-sm-2 control-label"><spring:message code="image.file.to.upload" /></label>
          <div class="col-sm-10">
            <input type="file" name="file" <c:if test="${not empty image.location}">disabled</c:if> />
            <form:errors path="location" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">
              <spring:message code="admin.actions.save" />
            </button>
          </div>
        </div>
        <c:if test="${not empty image.location}">
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <a href="${ctx}${fileURIRoot}${image.location}" target="_blank"> <img
                src="${ctx}${fileURIRoot}${resizeResolution}${image.location}" class="img-thumbnail" />
              </a>
              <form:hidden path="location" />
            </div>
          </div>
        </c:if>
      </fieldset>
    </form:form>
  </div>
</div>
<a href="${ctx}/admin/images" class="btn btn-default"><spring:message code="image.list" /></a>
