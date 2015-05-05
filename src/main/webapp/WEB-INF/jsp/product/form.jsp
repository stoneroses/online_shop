<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="product" action="${ctx}/products/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id"/>
        <legend>${formLegend}</legend>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label">Name</label>
          <div class="col-sm-10">
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="reference" class="col-sm-2 control-label">Reference</label>
          <div class="col-sm-10">
            <form:input path="reference" class="form-control" />
            <form:errors path="reference" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="weight" class="col-sm-2 control-label">Weight</label>
          <div class="col-sm-10">
            <form:input path="weight" class="form-control" />
            <form:errors path="weight" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="stock" class="col-sm-2 control-label">Stock</label>
          <div class="col-sm-10">
            <form:input path="stock" class="form-control" />
            <form:errors path="stock" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="price" class="col-sm-2 control-label">Price</label>
          <div class="col-sm-10">
            <form:input path="price" class="form-control" />
            <form:errors path="price" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="discount" class="col-sm-2 control-label">Discount</label>
          <div class="col-sm-10">
            <form:input path="discount" class="form-control" />
            <form:errors path="discount" cssClass="text-danger" />
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
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Save Product</button>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>
</div>
<a href="${ctx}/products" class="btn btn-default">Product List</a>
