<p class="bg-info message">Something wrong here!</p>
<p id="errorMessage" class="bg-danger message">${errorMessage}</p>
<p id="detailErrorMessage" class="bg-warning message" style="display: none;">${detailErrorMessage}</p>

<script type="text/javascript">
  $("#errorMessage").click(function() {
    $("#detailErrorMessage").toggle();
  });
</script>