<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="panel panel-default" style="background-color: #eee;">
  <div class="col-lg-7 col-centered">
    <div id="homePageCarousel">
      <c:forEach var="imageLocation" items="${homePageCarousel}" varStatus="row">
        <div class="col-centered">
          <a href="${ctx}${fileURIRoot}${imageLocation}" title="${imageLocation}"> <img
            src="${ctx}${fileURIRoot}${homePageCarouselResizeResolution}${imageLocation}" alt="${imageLocation}"
            style="height: auto;">
          </a>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
<script>
  $(document).ready(function() {
    $('#homePageCarousel').slick({
      slidesToShow : 1,
      slidesToScroll : 1,
      autoplay : true,
      autoplaySpeed : 1500,
      pauseOnHover: false
    });
  });
</script>