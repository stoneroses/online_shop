<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
  $(function() {

    $("#imagesThumbnail").sortable();

    $("#imagesThumbnail").disableSelection();

    $(document).on('click','span[id*=removeImageButton]', function() {
      $(this).parent().remove();
    });

    $("#imagesInput").autocomplete({
      source : "${ctx}/admin/images/search",
      paramName : "imageName",
      minLength : 2,
      select : function(event, ui) {
        $("#imagesThumbnail").append(createImageThumbnailDiv(ui.item))
      }
    }).autocomplete("instance")._renderItem = function(ul, item) {
      if (item.location) {
        return $("<li>").append(
            "<a><img width='102' height='58' src='${ctx}${fileURIRoot}${resizeResolution}" + item.location + "'></img>"
                + item.name + "</a>").appendTo(ul);
      } else {
        return $("<li>").append("<a>" + item.name + "<br>" + item.location + "</a>").appendTo(ul);
      }
    };

    function createImageThumbnailDiv(item) {
      d = document.createElement('div');
      $(d).addClass("col-xs-6 col-md-3");
      s1 = document.createElement('span');
      $(s1).append(item.name).appendTo($(d));
      s2 = document.createElement('span');
      $(s2).prop("id", "removeImageButton-" + item.id).addClass("glyphicon glyphicon-remove-circle").prop(
          "aria-hidden", "true").appendTo($(d));
      i = document.createElement('input');
      $(i).prop("type", "hidden").prop("name", "images").prop("value", item.id).appendTo($(d));
      a = document.createElement('a');
      $(a).prop("href", "${ctx}${fileURIRoot}" + item.location).prop("target", "_blank").addClass("thumbnail")
          .appendTo($(d));
      image = document.createElement('img');
      $(image).prop("src", "${ctx}${fileURIRoot}" + item.location).prop("alt", item.name).appendTo($(a));
      return d;
    }
  });
</script>

<div class="panel panel-default">
  <div class="panel-body">
    <form:form method="POST" modelAttribute="product" action="${ctx}/admin/products/save" class="form-horizontal">
      <fieldset>
        <form:hidden path="id" />
        <legend>
          <spring:message code="${pageTitle}" arguments="${pageTitleArg}" />
        </legend>
        <div class="form-group">
          <label for="name" class="col-sm-2 control-label"><spring:message code="product.name" /></label>
          <div class="col-sm-10">
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="reference" class="col-sm-2 control-label"><spring:message code="product.reference" /></label>
          <div class="col-sm-10">
            <form:input path="reference" class="form-control" />
            <form:errors path="reference" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="weight" class="col-sm-2 control-label"><spring:message code="product.weight" /></label>
          <div class="col-sm-10">
            <form:input path="weight" class="form-control" />
            <form:errors path="weight" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="stock" class="col-sm-2 control-label"><spring:message code="product.stock" /></label>
          <div class="col-sm-10">
            <form:input path="stock" class="form-control" />
            <form:errors path="stock" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="price" class="col-sm-2 control-label"><spring:message code="product.price" /></label>
          <div class="col-sm-10">
            <form:input path="price" class="form-control" />
            <form:errors path="price" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="discount" class="col-sm-2 control-label"><spring:message code="product.discount" /></label>
          <div class="col-sm-10">
            <form:input path="discount" class="form-control" />
            <form:errors path="discount" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="displayPrice" class="col-sm-2 control-label"><spring:message code="product.display.price" /></label>
          <div class="col-sm-10">
            <form:checkbox path="displayPrice" class="form-control" />
            <form:errors path="displayPrice" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="sortOrder" class="col-sm-2 control-label"><spring:message code="product.sort.order" /></label>
          <div class="col-sm-10">
            <form:input path="sortOrder" class="form-control" />
            <form:errors path="sortOrder" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="description" class="col-sm-2 control-label"><spring:message code="product.description" /></label>
          <div class="col-sm-10">
            <form:textarea path="description" class="form-control" />
            <form:errors path="description" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="images" class="col-sm-2 control-label"><spring:message code="image.selector" /></label>
          <div class="col-sm-10">
            <input id="imagesInput" name="imagesInput" class="form-control" placeholder="Enter image name" />
            <form:errors path="images" cssClass="text-danger" />
          </div>
        </div>
        <div class="form-group">
          <label for="imagesThumbnail" class="col-sm-12"><spring:message code="image.thumbnail" /></label>
          <div class="col-sm-12">
            <div id="imagesThumbnail" class="row">

              <c:forEach var="image" items="${product.images}" varStatus="row">

                <div class="col-xs-6 col-md-3">
                  <span>${image.name}</span><span id="removeImageButton-${image.id}"
                    class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span> <input type="hidden"
                    name="images" value="${image.id}" /> <a href="${ctx}${fileURIRoot}${image.location}"
                    target="_blank" class="thumbnail"> <img
                    src="${ctx}${fileURIRoot}${resizeResolution}${image.location}" alt="${image.name}">
                  </a>
                </div>

              </c:forEach>

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
<a href="${ctx}/admin/products" class="btn btn-default"><spring:message code="product.list" /></a>

<script>
  CKEDITOR.replace('description');
</script>
