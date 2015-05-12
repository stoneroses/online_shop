<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="link" action="${ctx}/admin/links/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>
          <spring:message code="${formLegend}" />
        </legend>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label"><spring:message code="link.name" /></label>
          <div class="col-sm-10">
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="url" class="col-sm-2 control-label"><spring:message code="link.url" /></label>
          <div class="col-sm-10">
            <form:input path="url" class="form-control" />
            <form:errors path="url" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="newWindow" class="col-sm-2 control-label"><spring:message code="link.new.window" /></label>
          <div class="col-sm-10">
            <form:checkbox path="newWindow" class="form-control" />
            <form:errors path="newWindow" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">
              <spring:message code="admin.actions.save" />
            </button>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>
</div>
<a href="${ctx}/admin/links" class="btn btn-default"><spring:message code="link.list" /></a>
