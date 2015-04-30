<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="cf"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="role" action="${ctx}/roles/create" class="form-horizontal">
      <fieldset>
        <legend>New Role page</legend>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label">Name</label>
          <div class="col-sm-10">
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssStyle="color: red;" />
          </div>
        </div>
        <div class="form-group">
          <label for="description" class="col-sm-2 control-label">Description</label>
          <div class="col-sm-10">
            <form:input path="description" class="form-control" />
            <form:errors path="description" cssStyle="color: red;" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-2 control-label"></div>
          <div class="col-sm-10">
            <c:forEach var="permission" items="${allPermissions}" varStatus="row">
              <div class="checkbox">
                <label> <input name="permissions"
                  type="checkbox" value="${permission.id}" /> ${permission.name}
                </label>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Update Role</button>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>
</div>
<a href="${ctx}/roles">role list</a>
