<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${not empty product.categories}">
  <c:set var="category" value="${product.categories[0]}" />
  <%@ include file="../category/category_breadcrumb.jsp"%>
</c:if>

<div class="panel panel-default">
  <div class="panel-heading">${product.name}</div>
  <div class="panel-body">
    <div class="row">
      <div class="col-sm-12">
        <c:if test="${displayPrice}">
          <p>
            <spring:message code="unit.of.measure.money" />
            ${product.price}
          </p>
          <p>${product.discount}</p>
          <p>
            <spring:message code="pigeon.sale.price.after.discount" />
            :
            <spring:message code="unit.of.measure.money" />
            ${product.nowPrice}
          </p>
        </c:if>
        <c:if test="${not displayPrice}">
          <a href="${ctx}/contact_us?subject=${product.name}" class="btn btn-default">Contact Us</a>
        </c:if>
      </div>
    </div>

    <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
      <div class="slides"></div>
      <h3 class="title"></h3>
      <a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a> <a class="play-pause"></a>
      <ol class="indicator"></ol>
    </div>

    <div id="links" class="row">
      <c:forEach var="image" items="${product.images}" varStatus="row">
        <div class="thumbnail col-sm-2">
          <a href="${ctx}${fileURIRoot}${image.location}" title="${image.name}"> <img
            src="${ctx}${fileURIRoot}${image.location}" alt="${image.name}" style="height: auto;">
          </a>
        </div>
      </c:forEach>
    </div>

    <p>${product.description}</p>
    <a href="${ctx}/contact_us?subject=${product.name}" class="btn btn-default">Contact Us</a>

  </div>
</div>


<script src="${ctx}/common/javascript/blueimp-gallery.min.js"></script>
<script>
  document.getElementById('links').onclick = function(event) {
    event = event || window.event;
    var target = event.target || event.srcElement, link = target.src ? target.parentNode : target, options = {
      index : link,
      event : event
    }, links = this.getElementsByTagName('a');
    blueimp.Gallery(links, options);
  };
</script>