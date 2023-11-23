var app = angular.module('app', []);
const baseUrl = 'http://localhost:8080/asm/api';
app.controller('signinController', ($scope, $http) => {
	$scope.isLoading = false;
	$scope.formState = {
		id: '',
		password: '',
	};
	$scope.signin = () => {
		$scope.errorMessage = '';
		$scope.isLoading = true;
		$http.post(`${baseUrl}/account/sign-in`, $scope.formState).then((res) => {
			$scope.isLoading = false;
			if (!res.data.status) {
				$scope.errorMessage = res.data.message;
			} else {
				const paths = ["watch", "profile", "favorite"]
				const previousPage = document.referrer
				let isBackToHome = true
				paths.forEach(path => {
					if (previousPage.includes(path)) {
						isBackToHome = false
					}
				})
				window.location = isBackToHome ? 'http://localhost:8080/asm/home' : previousPage;
			}
		}), (err) => {
			console.log(err)
			$scope.errorMessage = "Something went wrong";
			$scope.isLoading = false;
		};
	};
	$scope.errorMessage = '';
});
