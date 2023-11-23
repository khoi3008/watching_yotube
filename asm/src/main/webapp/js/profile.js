var app = angular.module('app', []);
const baseUrl = 'http://localhost:8080/asm/api';
var myModalEl = document.getElementById('staticBackdrop');
var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
app.controller('profileController', ($scope, $http) => {
	$scope.errorMessage = '';
	$scope.successMessage = '';
	$scope.activeTab = 0;
	$scope.justWatchVideos = []
	$scope.loaded = false;
	$scope.isUpdatingInfo = false;
	$scope.isChangingPassword = false;
	$scope.changeTab = (tabIndex) => {
		if (tabIndex === 2) {
			let videoids = JSON.parse(localStorage.getItem("justwatched")) || []
			if (videoids.length > 0) {
				videoids = [...new Set(videoids)]
				videoids = videoids.filter((item, index) => index < 6)
				$http.post(`${baseUrl}/justwatch`, videoids).then(res => {
					if (res.data) {
						$scope.justWatchVideos = res.data
					}
				})
			}

		}

		$scope.activeTab = tabIndex;
		$scope.errorMessage = '';
		$scope.successMessage = '';
	};

	$scope.information = {};
	$scope.passwordData = {
		password: "",
		newPassword: '',
		confirmPassword: '',
	};

	$http.get(`${baseUrl}/user-data`).then((res) => {
		if (res.data) {
			const { id, fullname, email } = res.data;
			$scope.loaded = true;
			$scope.fullname = !!fullname ? fullname : id;
			$scope.information = {
				id,
				fullname,
				email,
			};
		} else {
			window.location = 'http://localhost:8080/asm/sign-in';
		}
	});

	$scope.updateInfomation = () => {
		$scope.isUpdatingInfo = true;
		$http.post(`${baseUrl}/update-user`, $scope.information).then((res) => {
			$scope.isUpdatingInfo = false;
			if (res.data.status) {
				$scope.fullname = $scope.information.fullname
				$scope.successMessage = res.data.message;
				$scope.errorMessage = '';

			} else {
				$scope.errorMessage = res.data.message;
				$scope.successMessage = '';
			}
		}, (err) => {
			console.log(err)
			$scope.errorMessage = "Something went wrong";
			$scope.isUpdatingInfo = false;
		});
	};

	$scope.changePassword = () => {
		if ($scope.passwordData.newPassword !== $scope.passwordData.confirmPassword) {
			$scope.errorMessage = 'Confirm password is not same new password';
		} else {
			$scope.isChangingPassword = true;
			const { newPassword, password } = $scope.passwordData;
			$http.post(`${baseUrl}/account/change-password`, { newPassword, password }).then((res) => {
				$scope.isChangingPassword = false;
				if (res.data.status) {
					$scope.successMessage = res.data.message;
					$scope.errorMessage = '';
					modal.show();
				} else {
					$scope.errorMessage = res.data.message;
					$scope.successMessage = '';
				}
			}, (err) => {
				console.log(err);
				$scope.errorMessage = "Something went wrong";
				$scope.isChangingPassword = false;
			});
		}
	};
});
