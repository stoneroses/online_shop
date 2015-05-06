<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="setting" action="${ctx}/settings/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id"/>
        <legend>${formLegend}</legend>
        <div class="form-group">
          <label for="key" class="col-sm-2 control-label">Name</label>
          <div class="col-sm-10">
            <form:input path="key" class="form-control" />
            <form:errors path="key" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="value" class="col-sm-2 control-label">Value</label>
          <div class="col-sm-10">
            <form:input path="value" class="form-control" />
            <form:errors path="value" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Save Setting</button>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>
</div>
<a href="${ctx}/settings" class="btn btn-default">Setting List</a>
