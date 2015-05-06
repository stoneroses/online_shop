<script>
  $(function() {

    $("span[id*=removeButton]").click(function() {
      $(this).parents("tr").remove();
    });

    $("#productsTable > tbody").sortable();

    $("#productsTable > tbody").disableSelection();

    $("#productsInput").autocomplete({
      source : "${ctx}/products/search",
      paramName : "productName",
      minLength : 2,
      select : function(event, ui) {
        $("#productsTable > tbody").append(createProductTr(ui.item))
      }
    }).autocomplete("instance")._renderItem = function(ul, item) {
      return $("<li>").append("<a>" + item.name + "<br>" + item.description + "</a>").appendTo(ul);
    };

    function createProductTr(item) {
      tr = document.createElement('tr');
      $(tr)
          .append("<td><input type='hidden' name='products' value='" + item.id + "'>" + item.name + "</td>")
          .append("<td>" + item.description + "</td>")
          .append(
              "<td><span id='removeButton-" + item.id +"' class='glyphicon glyphicon-remove-circle' aria-hidden='true'></span></td>");
      return tr;
    }

    $("#categoriesTable > tbody").sortable();

    $("#categoriesTable > tbody").disableSelection();

    $("#categoriesInput").autocomplete({
      source : "${ctx}/categories/search",
      paramName : "categoryName",
      minLength : 2,
      select : function(event, ui) {
        $("#categoriesTable > tbody").append(createCategoryTr(ui.item))
      }
    }).autocomplete("instance")._renderItem = function(ul, item) {
      return $("<li>").append("<a>" + item.name + "<br>" + item.description + "</a>").appendTo(ul);
    };

    function createCategoryTr(item) {
      tr = document.createElement('tr');
      $(tr)
          .append("<td><input type='hidden' name='children' value='" + item.id + "'>" + item.name + "</td>")
          .append("<td>" + item.description + "</td>")
          .append(
              "<td><span id='removeButton-" + item.id +"' class='glyphicon glyphicon-remove-circle' aria-hidden='true'></span></td>");
      return tr;
    }
  });
</script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="category" action="${ctx}/categories/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>${formLegend}</legend>
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
            <form:input path="description" class="form-control" />
            <form:errors path="description" cssClass="text-danger" />
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
                    <th><spring:message code="page.list.table.head.actions" /></th>
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
          <label for="products" class="col-sm-2 control-label">Products Selector</label>
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
                    <th><spring:message code="page.list.table.head.actions" /></th>
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
              <spring:message code="actions.save" />
            </button>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>
</div>
<a href="${ctx}/categories" class="btn btn-default"><spring:message code="category.list" /></a>
