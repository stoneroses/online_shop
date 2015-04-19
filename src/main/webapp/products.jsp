<!doctype html>
<html ng-app="productApp">
<head>
<title>Spring MVC + AngularJS Demo</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
<script>
  var productApp = angular.module('productApp', []);

  productApp.controller('productController', function($scope, $http) {
    $http.get("products.json").success(function(data) {
      $scope.products = data;
    });
  });
</script>
</head>
<body>
  <div ng-controller="productController">
    <table>
      <thead>
        <th>ID</th>
        <th>Name</th>
      </thead>
      <tbody>
        <tr ng-repeat="product in products">
          <td>{{ product.id }}</td>
          <td>{{ product.productName }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <a href="${ctx}">home</a>
</body>
</html>