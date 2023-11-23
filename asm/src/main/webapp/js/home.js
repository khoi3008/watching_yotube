var app = angular.module('app', []);

app.controller('homeController', ($scope) => {
  const root = document.getElementById('page-root');
  $scope.pagesize = 8;
  $scope.baseUrl = 'http://localhost:8080/asm/home';
  $scope.totalPage = parseInt(root.dataset.totalPage);
  $scope.currentPage = parseInt(root.dataset.currentPage);
  $scope.point = 6;
  const getPage = () => {
    let result = [];
    for (let i = 0; i < $scope.totalPage; i++) {
      result.push({
        href: `${$scope.baseUrl}?page=${i}&pagesize=${$scope.pagesize}`,
        content: i + 1,
      });
    }
    return result;
  };
  $scope.pages = getPage();
});
