
<script>
  $(function() {

    $("#header_logo_productsInput").autocomplete({
      source : "${ctx}/admin/products/search",
      paramName : "productName",
      minLength : 2,
      select : function(event, ui) {
        window.location.href = "${ctx}/products/" + ui.item.id;
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

    $("#searchclear").click(function() {
      $("#header_logo_productsInput").val('');
    });
  });
</script>

<div class="container">
  <div class="row">
    <div class="col-sm-6">
      <a href="${ctx}/"> <img alt="${pageTitlePrefix}" src="${ctx}/common/images/logo.png" height="130" width="500">
      </a>
    </div>
    <div class="col-sm-6">
      <input id="header_logo_productsInput" name="header_logo_productsInput" class="form-control"
        placeholder="Enter product name" /> <span id="searchclear" class="glyphicon glyphicon-remove-circle"></span>
    </div>
    <div class="col-sm-6">
      <span id="newsPanel"></span>
    </div>
  </div>
</div>
