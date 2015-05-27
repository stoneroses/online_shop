<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="homePageCarouselPanel" class="panel panel-default">
  <div class="col-lg-5 col-centered">
    <div id="homePageCarousel" style="margin: 20px auto 20px auto;">
      <c:forEach var="imageLocation" items="${homePageCarousel}" varStatus="row">
        <div class="col-centered">
          <a href="${ctx}${fileURIRoot}${imageLocation}" title="${imageLocation}"> <img
            src="${ctx}${fileURIRoot}${homePageCarouselResizeResolution}${imageLocation}" alt="${imageLocation}"
            style="width: 408px; height: 330px;">
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
      autoplaySpeed : 2000,
      pauseOnHover : false,
    });
  });
</script>
