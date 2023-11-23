var app = angular.module('app', []);
const baseUrl = 'http://localhost:8080/asm/api';
const likeBtn = document.getElementById('react');
var myModalEl = document.getElementById('staticBackdrop');
var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
const rexgex =
	/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
app.controller('watchController', ($scope, $http) => {
	$scope.videoId = likeBtn.dataset.videoId
	$scope.count = likeBtn.dataset.countFavorite
	if ($scope.videoId) {
		let ids = JSON.parse(localStorage.getItem("justwatched")) || []
		ids = [$scope.videoId, ...ids]
		if (ids.length > 6) {
			ids = ids.filter((item, index) => index < 6)
		}
		localStorage.setItem("justwatched", JSON.stringify(ids))
	}
	$scope.videoLink = `http://localhost:8080/asm/watch?id=${$scope.videoId}`
	$scope.copyText = 'Copy';
	$scope.emailInput = '';
	$scope.emails = [];
	$scope.message = '';
	$scope.isLoading = false;
	$scope.errorMessage = '';
	$scope.successMessage = '';
	$scope.copy = () => {
		navigator.clipboard.writeText($scope.videoLink);
		$scope.copyText = 'Copied!';
	};

	$scope.showModal = () => {
		modal.show();
	};

	$scope.addEmail = () => {
		if ($scope.emailInput.match(rexgex)) {
			if ($scope.emails.includes($scope.emailInput)) {
				$scope.message = 'Email is exsist in list!';
			} else {
				$scope.emails = [...$scope.emails, $scope.emailInput];
				$scope.emailInput = '';
			}
		} else {
			$scope.message = 'Email is invalid!';
		}
	};

	$scope.removeEmail = (email) => {
		$scope.emails = $scope.emails.filter((item) => item !== email);
	};

	$scope.onType = () => {
		if ($scope.message) {
			$scope.message = '';
		}
	};

	$scope.share = () => {
		$scope.isLoading = true
		const data = {
			videoId: $scope.videoId,
			emails: $scope.emails.join(",")
		}
		$http.post(`${baseUrl}/share/user`, data).then((res) => {
			$scope.isLoading = false
			if (res.data.status) {
				$scope.emails = []
				$scope.successMessage = res.data.message;
				$scope.errorMessage = '';

			} else {
				$scope.errorMessage = res.data.message;
				$scope.successMessage = '';
			}
		}), (err) => {
			console.log(err)
			$scope.errorMessage ="Something went wrong";
			$scope.isLoading = false;
		}
	};

	$scope.react = () => {
		const isLike = likeBtn.dataset.like === 1;
		const url = isLike ? `${baseUrl}/favorite/unlike` : `${baseUrl}/favorite/like`;
		$http.post(url, { id: $scope.videoId }).then((res) => {
			if (res.data.status) {
				const icon = likeBtn.querySelector('.bi');
				icon.classList.toggle('bi-heart');
				icon.classList.toggle('bi-heart-fill');
				likeBtn.classList.toggle('like-color');
				likeBtn.dataset.like = isLike ? 0 : 1;
				if(isLike){
					$scope.count = $scope.count*1 - 1;
				}else{
					$scope.count = $scope.count*1 + 1;
				}
			} else {
				alert(res.data.message);
			}
		});
	};
});
