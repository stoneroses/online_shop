<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div id="blueimp-gallery-carousel" class="blueimp-gallery blueimp-gallery-carousel">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>

<div id="links" class="row" hidden="true">
  <c:forEach var="imageLocation" items="${homePageCarousel}" varStatus="row">
    <div class="thumbnail col-sm-2">
      <a href="${ctx}${fileURIRoot}${imageLocation}" title="${imageLocation}"> <img
        src="${ctx}${fileURIRoot}${resizeResolution}${imageLocation}" alt="${imageLocation}" style="height: auto;">
      </a>
    </div>
  </c:forEach>
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
  
  blueimp.Gallery(
      document.getElementById('links').getElementsByTagName('a'),
      {
          container: '#blueimp-gallery-carousel',
          carousel: true
      }
  );
</script>