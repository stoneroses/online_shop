<p class="bg-info">Something wrong here!</p>
<p id="errorMessage" class="bg-danger">${errorMessage}</p>
<p id="detailErrorMessage" class="bg-warning" style="display: none;">${detailErrorMessage}</p>

<script type="text/javascript">
  $("#errorMessage").click(function() {
    $("#detailErrorMessage").toggle();
  });
</script>