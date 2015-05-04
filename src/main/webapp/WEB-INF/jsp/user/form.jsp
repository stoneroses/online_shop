<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="cf"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="user" action="${ctx}/users/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>${formLegend}</legend>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label">Name</label>
          <div class="col-sm-10">
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="email" class="col-sm-2 control-label">Email</label>
          <div class="col-sm-10">
            <input id="email" name="email" class="form-control" type="text" value="${user.email}" <c:if test="${not empty user.id}">readonly</c:if>>
            <form:errors path="email" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="description" class="col-sm-2 control-label">Description</label>
          <div class="col-sm-10">
            <form:textarea path="description" class="form-control" />
            <form:errors path="description" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-2 control-label"></div>
          <div class="col-sm-10">
            <c:forEach var="permission" items="${allPermissions}" varStatus="row">
              <div class="checkbox">
                <label> <input name="permissions" type="checkbox" value="${permission.id}"
                  <c:if test="${ cf:contains(user.permissions, permission)}">checked</c:if> /> ${permission.name}
                </label>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Save User</button>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>
</div>
<a href="${ctx}/users" class="btn btn-default">User List</a>