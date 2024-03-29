<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
  $(function() {

    $(document).on('click','span[id*=removeButton]', function() {
      $(this).parents("tr").remove();
    });

    $("#productsTable > tbody").sortable();

    $("#productsTable > tbody").disableSelection();

    $("#productsInput").autocomplete({
      source : "${ctx}/admin/products/search",
      paramName : "productName",
      minLength : 2,
      select : function(event, ui) {
        $("#productsTable > tbody").append(createProductTr(ui.item))
      }
    }).autocomplete("instance")._renderItem = function(ul, item) {
      if (item.imageLocation) {
        return $("<li>").append(
            "<a><img width='102' height='58' src='${ctx}${fileURIRoot}${resizeResolution}" + item.imageLocation + "'></img>"
                + item.name + "</a>").appendTo(ul);
      } else {
        return $("<li>").append("<a>" + item.name + "</a>").appendTo(ul);
      }
    };

    function createProductTr(item) {
      tr = document.createElement('tr');
      $(tr)
          .append("<td><input type='hidden' name='products' value='" + item.id + "'>" + item.name + "</td>")
          .append("<td>" + item.description + "</td>")
          .append(
              "<td><span id='removeButton-product-" + item.id +"' class='glyphicon glyphicon-remove-circle' aria-hidden='true'></span></td>");
      return tr;
    }

    $("#categoriesTable > tbody").sortable();

    $("#categoriesTable > tbody").disableSelection();

    $("#categoriesInput").autocomplete({
      source : "${ctx}/admin/categories/search",
      paramName : "categoryName",
      minLength : 2,
      select : function(event, ui) {
        $("#categoriesTable > tbody").append(createCategoryTr(ui.item))
      }
    }).autocomplete("instance")._renderItem = function(ul, item) {
      return $("<li>").append("<a>" + item.name + "</a>").appendTo(ul);
    };

    function createCategoryTr(item) {
      tr = document.createElement('tr');
      $(tr)
          .append("<td><input type='hidden' name='children' value='" + item.id + "'>" + item.name + "</td>")
          .append("<td>" + item.description + "</td>")
          .append(
              "<td><span id='removeButton-category-" + item.id +"' class='glyphicon glyphicon-remove-circle' aria-hidden='true'></span></td>");
      return tr;
    }
  });
</script>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="category" action="${ctx}/admin/categories/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>
          <spring:message code="${pageTitle}" arguments="${pageTitleArg}" />
        </legend>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label"><spring:message code="category.name" /></label>
          <div class="col-sm-10">
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="description" class="col-sm-2 control-label"><spring:message code="category.description" /></label>
          <div class="col-sm-10">
            <form:textarea path="description" class="form-control" />
            <form:errors path="description" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="sortOrder" class="col-sm-2 control-label"><spring:message code="category.sort.order" /></label>
          <div class="col-sm-10">
            <form:input path="sortOrder" class="form-control" />
            <form:errors path="sortOrder" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="categories" class="col-sm-2 control-label"><spring:message code="category.selector" /></label>
          <div class="col-sm-10">
            <input id="categoriesInput" name="categoriesInput" class="form-control" placeholder="Enter category name" />
            <form:errors path="children" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-12">
            <div class="row">
              <table id="categoriesTable" class="table table-striped table-hover ">
                <thead>
                  <tr>
                    <th><spring:message code="category.name" /></th>
                    <th><spring:message code="category.description" /></th>
                    <th><spring:message code="admin.page.list.actions" /></th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="category" items="${category.children}" varStatus="row">
                    <tr>
                      <td><input type="hidden" name="children" value="${category.id}">${category.name}</td>
                      <td>${category.description}</td>
                      <td><span id="removeButton-category-${category.id}" class="glyphicon glyphicon-remove-circle"
                        aria-hidden="true"></span></td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label for="products" class="col-sm-2 control-label"><spring:message code="product.selector" /></label>
          <div class="col-sm-10">
            <input id="productsInput" name="productsInput" class="form-control" placeholder="Enter product name" />
            <form:errors path="products" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-12">
            <div class="row">
              <table id="productsTable" class="table table-striped table-hover ">
                <thead>
                  <tr>
                    <th><spring:message code="product.name" /></th>
                    <th><spring:message code="product.description" /></th>
                    <th><spring:message code="admin.page.list.actions" /></th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="product" items="${category.products}" varStatus="row">
                    <tr>
                      <td><input type="hidden" name="products" value="${product.id}">${product.name}</td>
                      <td>${product.description}</td>
                      <td><span id="removeButton-product-${product.id}" class="glyphicon glyphicon-remove-circle"
                        aria-hidden="true"></span></td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
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
<a href="${ctx}/admin/categories" class="btn btn-default"><spring:message code="category.list" /></a>

<script>
  CKEDITOR.replace('description');
</script>
