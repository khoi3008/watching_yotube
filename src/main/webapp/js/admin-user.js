//Hello A
var app = angular.module('app', []);
const baseUrl = 'http://localhost:8080/asm/api';
app.controller('userController', ($scope, $http) => {
	$scope.isLoading = false
	const initFormState = {
		id: '',
		password: '',
		fullname: '',
		email: '',
	};
	$scope.errorMessage = '';
	$scope.successMessage = '';
	$scope.changeTab = (selector) => {
		const tab = document.querySelector(selector);
		bootstrap.Tab.getOrCreateInstance(tab).show();
	};

	$scope.formState = { ...initFormState };

	$scope.setForm = (id) => {
		$http.get(`${baseUrl}/admin/user?id=${id}`).then((res) => {
			if (res) {
				const { id, fullname, password, email } = res.data;
				$scope.formState = { id, fullname, password, email };
				$scope.changeTab("#myTab button[data-bs-target='#home']");
				$scope.errorMessage = '';
				$scope.successMessage = '';
			} else {
				alert('User not found!');
			}
		});
	};

	$scope.updateRow = (user) => {
		const rows = document.getElementById(user.id);
		if (rows) {
			let cols = rows.querySelectorAll('.col');
			const { id, password, fullname, email } = user;
			cols[0].innerText = id;
			cols[1].innerText = password;
			cols[2].innerText = fullname;
			cols[3].innerText = email;
		}
	};

	$scope.deleteRow = (id) => {
		document.getElementById(id).remove();
	};

	$scope.update = (e) => {
		e.preventDefault();
		$scope.isLoading = true;
		$http.post(`${baseUrl}/update-user`, $scope.formState).then((res) => {
			$scope.isLoading = false;
			if (res.data.status) {
				$scope.updateRow($scope.formState);
				$scope.errorMessage = '';
				$scope.successMessage = res.data.message;
				$scope.formState = { ...initFormState };
			} else {
				$scope.errorMessage = res.data.message;
				$scope.successMessage = '';
			}
		},(err) => {
			console.log(err)
			$scope.isLoading = false;
			$scope.errorMessage ="Something went wrong";
		});
	};

	$scope.delete = (e) => {
		e.preventDefault();
		$scope.isLoading = true;
		$http.delete(`${baseUrl}/admin/delete-user?id=${$scope.formState.id}`).then((res) => {
			$scope.isLoading = false;
			if (res.data.status) {
				$scope.deleteRow($scope.formState.id);
				$scope.errorMessage = '';
				$scope.successMessage = res.data.message;
				$scope.formState = { ...initFormState };
			} else {
				$scope.errorMessage = res.data.message;
				$scope.successMessage = '';
			}
		},(err) => {
			console.log(err)
			$scope.isLoading = false;
			$scope.errorMessage ="Something went wrong";
		});
	};
});
