<!doctype html>
<html ng-app="productApp">
<head>
<title>Spring MVC + AngularJS Demo</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
<script>
  var productApp = angular.module('productApp', []);

  productApp.controller('productController', function($scope, $http) {
    $http.get('products/1.json').success(function(data) {
      $scope.product = data;
    });
  });
</script>
</head>
<body>
  <div ng-controller="productController">
    <h2>Spring MVC + AngularJS Demo</h2>
    <p>Id : {{product.id}}</p>
    <p>Product Name : {{product.productName}}</p>
  </div>
  <a href="index.jsp">home</a>

</body>
</html>