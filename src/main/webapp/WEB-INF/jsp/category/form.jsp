<script>
  $(function() {

    $("#productsTable > tbody").sortable();

    $("#productsTable > tbody").disableSelection();

    $("span[id*=removeProductButton]").click(function() {
      $(this).parents("tr").remove();
    });

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
              "<td><span id='removeProductButton-" + item.id +"' class='glyphicon glyphicon-remove-circle' aria-hidden='true'></span></td>");
      return tr;
    }
  });
</script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="category" action="${ctx}/categories/save" class="form-horizontal">
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
          <label for="description" class="col-sm-2 control-label">Description</label>
          <div class="col-sm-10">
            <form:input path="description" class="form-control" />
            <form:errors path="description" cssClass="text-danger" />
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
                    <th>Name</th>
                    <th>Description</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="product" items="${category.products}" varStatus="row">
                    <tr>
                      <td><input type="hidden" name="products" value="${product.id}">${product.name}</td>
                      <td>${product.description}</td>
                      <td><span id="removeProductButton-${product.id}" class="glyphicon glyphicon-remove-circle"
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
            <button type="submit" class="btn btn-default">Save Category</button>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>
</div>
<a href="${ctx}/categories" class="btn btn-default">Category List</a>
