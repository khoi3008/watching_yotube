var app = angular.module('app', []);
const baseUrl = 'http://localhost:8080/asm/api';
app.controller('shareController', ($scope, $http) => {
	let data = [];
	$scope.isLoading = true
	$scope.videos = [];
	$scope.reports = [];
	$scope.favoUsers = [];
	$scope.shareUsers = [];
	$scope.searchFavoriteValue = '';
	$scope.searchShareValue = '';
	$scope.noResult = false

	$http.get(`${baseUrl}/admin/report`).then((res) => {
		$scope.reports = res.data;
		$scope.isLoading = false
	});

	$http.get(`${baseUrl}/videos?list=1`).then((res) => {
		$scope.videos = res.data;
		data = [...res.data];
		$scope.isLoading = false
	});

	$scope.searchFavo = () => {
		if ($scope.noResult) {
			$scope.noResult = false
		}
		const regex = new RegExp($scope.searchFavoriteValue.toUpperCase());
		$scope.videos = data.filter((item) => item.title.toUpperCase().match(regex));
	};

	$scope.searchShare = () => {
		if ($scope.noResult) {
			$scope.noResult = tru
		}
		const regex = new RegExp($scope.searchShareValue.toUpperCase());
		$scope.videos = data.filter((item) => item.title.toUpperCase().match(regex));
	};

	$scope.changeTab = () => {
		if (data.length > 0) {
			$scope.videos = [...data];
		}
	};

	$scope.selectFavoriteVideo = (e, video) => {
		$scope.isLoading = true
		e.preventDefault();
		$scope.searchFavoriteValue = video.title;
		$http.get(`${baseUrl}/admin/favorite-user?id=${video.id}`).then((res) => {
			if (res.data.length === 0) {
				$scope.noResult = true
			}
			$scope.favoUsers = res.data;
			$scope.isLoading = false
		});
		document.getElementById('search-box').blur();
	};

	$scope.selectShareVideo = (e, video) => {
		$scope.isLoading = true
		e.preventDefault();
		$scope.searchShareValue = video.title;
		$http.get(`${baseUrl}/admin/share-user?id=${video.id}`).then((res) => {
			if (res.data.length === 0) {
				$scope.noResult = true
			}
			$scope.shareUsers = res.data;
			$scope.isLoading = false
		});
		document.getElementById('search-share-box').blur();
	};
});
