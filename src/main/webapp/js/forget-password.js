var app = angular.module('app', []);
app.controller('forgetPasswordController', ($scope, $http) => {
	$scope.isLoading = false;
	const initFormState = {
		id: '',
		email: '',
	};
	$scope.errorMessage = '';
	$scope.successMessage = '';
	$scope.formState = { ...initFormState };
	$scope.submit = () => {
		$scope.errorMessage = '';
		$scope.successMessage = '';
		$scope.isLoading = true;
		$http.post('http://localhost:8080/asm/api/account/forget-password', $scope.formState).then((res) => {
			$scope.isLoading = false;
			if (res.data.status) {
				$scope.successMessage = res.data.message;
			} else {
				$scope.errorMessage = res.data.message;
			}
		}, (err) => {
			console.log(err)
			$scope.isLoading = false;
			$scope.errorMessage = "Something went wrong";
		});
	};
});
