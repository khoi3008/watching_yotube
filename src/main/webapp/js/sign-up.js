var app = angular.module('app', []);
const baseUrl = 'http://localhost:8080/asm/api';
app.controller('signupController', ($scope, $http) => {
	$scope.isLoading = false;
	$scope.formState = {
		id: '',
		password: '',
		confirmPassword: '',
	};
	$scope.isValid = () => {
		return $scope.formState.password === $scope.formState.confirmPassword;
	};

	$scope.signUp = () => {
		if (!$scope.isValid()) {
			$scope.errorMessage = 'Password is not same confirm password!';
		} else {
			$scope.errorMessage = '';
			$scope.isLoading = true;
			$http
				.post(`${baseUrl}/account/sign-up`, { id: $scope.formState.id, password: $scope.formState.password })
				.then((res) => {
					$scope.isLoading = false;
					if (res.data.status) {
						$scope.successMessage = res.data.message;
					} else {
						$scope.errorMessage = res.data.message;
					}
				}, (err) => {
					console.log(err)
					$scope.errorMessage = "Something went wrong";
					$scope.isLoading = false;
				});
		}
	};
	$scope.successMessage = '';
	$scope.errorMessage = '';
});
