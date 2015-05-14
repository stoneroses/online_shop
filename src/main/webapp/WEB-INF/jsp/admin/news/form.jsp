<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="news" action="${ctx}/admin/news/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>
          <spring:message code="${pageTitle}" arguments="${pageTitleArg}" />
        </legend>
        <div class="form-group">
          <label for="title" class="col-sm-2 control-label"><spring:message code="news.title" /></label>
          <div class="col-sm-10">
            <form:input path="title" class="form-control" />
            <form:errors path="title" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="content" class="col-sm-2 control-label"><spring:message code="news.content" /></label>
          <div class="col-sm-10">
            <form:textarea path="content" class="form-control" />
            <form:errors path="content" cssClass="text-danger" />
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
<a href="${ctx}/admin/news" class="btn btn-default"><spring:message code="news.list" /></a>

<script>
  // Replace the <textarea id="editor1"> with a CKEditor
  // instance, using default configuration.
  CKEDITOR.replace('content');
</script>
