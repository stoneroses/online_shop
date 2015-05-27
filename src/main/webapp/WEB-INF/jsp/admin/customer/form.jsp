<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="cf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="customer" action="${ctx}/admin/customers/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>
          <spring:message code="${pageTitle}" arguments="${pageTitleArg}" />
        </legend>
        <div class="form-group">
          <label for="title" class="col-sm-2 control-label"><spring:message code="customer.title" /></label>
          <div class="col-sm-10">
            <form:input path="title" class="form-control" />
            <form:errors path="title" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label"><spring:message code="customer.name" /></label>
          <div class="col-sm-10">
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="email" class="col-sm-2 control-label"><spring:message code="customer.email" /></label>
          <div class="col-sm-10">
            <input id="email" name="email" class="form-control" type="text" value="${customer.email}">
            <form:errors path="email" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="subscribe" class="col-sm-2 control-label"><spring:message code="customer.subscribe" /></label>
          <div class="col-sm-10">
            <form:checkbox path="subscribe" class="form-control" />
            <form:errors path="subscribe" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="description" class="col-sm-2 control-label"><spring:message code="customer.description" /></label>
          <div class="col-sm-10">
            <form:textarea path="description" class="form-control" />
            <form:errors path="description" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label"><spring:message code="customer.group" /></label>
          <div class="col-sm-10">
            <c:forEach var="customerGroup" items="${allCustomerGroups}" varStatus="row">
              <div class="checkbox">
                <label> <input name="customerGroups" type="checkbox" value="${customerGroup.id}"
                  <c:if test="${ cf:contains(customer.customerGroups, customerGroup)}">checked</c:if> /> ${customerGroup.name}
                </label>
              </div>
            </c:forEach>
            <form:errors path="customerGroups" cssClass="text-danger" />
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
<a href="${ctx}/admin/customers" class="btn btn-default"><spring:message code="customer.list" /></a>
