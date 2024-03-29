<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="cf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="role" action="${ctx}/admin/roles/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>
          <spring:message code="${pageTitle}" arguments="${pageTitleArg}" />
        </legend>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label"><spring:message code="role.name" /></label>
          <div class="col-sm-10">
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="description" class="col-sm-2 control-label"><spring:message code="role.description" /></label>
          <div class="col-sm-10">
            <form:input path="description" class="form-control" />
            <form:errors path="description" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label"><spring:message code="permission" /></label>
          <div class="col-sm-10">
            <c:forEach var="permission" items="${allPermissions}" varStatus="row">
              <div class="checkbox">
                <label> <input name="permissions" type="checkbox" value="${permission.id}"
                  <c:if test="${ cf:contains(role.permissions, permission)}">checked</c:if> /> ${permission.name}
                </label>
              </div>
            </c:forEach>
          </div>
          <form:errors path="permissions" cssClass="text-danger" />
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
<a href="${ctx}/admin/roles" class="btn btn-default"><spring:message code="role.list" /></a>
