var app = angular.module('app', []);

const getUrl = (path) => {
	return `http://localhost:8080/asm/api${path}`;
};

app.controller('videoController', ($scope, $http) => {
	$scope.isLoading = false;
	const initFormState = {
		id: '',
		title: '',
		views: 0,
		shortDescription: '',
		active: 'true',
	};
	const toastOptions = {
		success: {
			class: 'text-success',
			icon: 'bi bi-check-circle',
			message: 'Success',
		},
		faile: {
			class: 'text-danger',
			icon: 'bi bi-x-circle',
			message: 'Faile',
		},
	};
	$scope.toastData = {
		class: 'text-success',
		icon: 'bi-check-circle',
		message: 'Success',
	};
	$scope.videoImg = 'https://img.youtube.com/vi//hqdefault.jpg';
	$scope.formState = initFormState;
	$scope.listVideo = [];
	$scope.page = 0;
	$scope.pagesize = 10;
	$scope.isEdit = false;

	$scope.onChangeId = () => {
		$scope.videoImg = `https://img.youtube.com/vi/${$scope.formState.id}/hqdefault.jpg`;
	};
	$scope.changeTab = (selector) => {
		const tab = document.querySelector(selector);
		bootstrap.Tab.getOrCreateInstance(tab).show();
	};
	$scope.loadData = () => {
		$http.get(getUrl(`/videos?page=${$scope.page}&pagesize=${$scope.pagesize}`)).then(
			(res) => {
				$scope.listVideo = res.data;
			},
			(err) => console.log(err),
		);
	};

	$scope.showToast = (selector, message = '', type = 'success', options = { delay: 2000 }) => {
		const myToastEl = document.querySelector(selector);
		if (type) {
			$scope.toastData = toastOptions[type];
		}
		if (message) {
			$scope.toastData.message = message;
		}
		bootstrap.Toast.getOrCreateInstance(myToastEl, options).show();
	};

	$scope.setForm = (id) => {
		$http.get(getUrl(`/videos?id=${id}`)).then((res) => {
			const video = res.data;
			if (video?.id) {
				$scope.isEdit = true;
				$scope.formState = {
					id: video.id,
					title: video.title,
					views: video.views,
					shortDescription: video.shortDescription,
					active: video.active ? "true" : "false",
				};
				editor.setData(res.data.description)
				$scope.videoImg = `https://img.youtube.com/vi/${video.id}/hqdefault.jpg`;
				$scope.changeTab("#myTab button[data-bs-target='#video-edition']");
			} else {
				$scope.showToast('#videoToast', 'Something went wrong', 'faile');
			}
		});
	};

	$scope.submit = (e) => {
		e.preventDefault();
		$scope.isLoading = true;
		const description = editor.getData();
		const data = { ...$scope.formState, poster: $scope.videoImg, description };

		$http.post(getUrl('/admin/videos'), data).then(
			(res) => {
				$scope.isLoading = false;
				if (res.data.status) {
					$scope.showToast('#videoToast', res.data.message);
					$scope.reset(null);
				}else{
					$scope.showToast('#videoToast', res.data.message, "faile");
				}
			},
			(err) => {
				console.log(err)
				$scope.showToast('#videoToast', 'Something went wrong', 'faile');
				$scope.isLoading = false;
			},
		);
	};

	$scope.update = (e) => {
		e.preventDefault();
		$scope.isLoading = true;
		const description = editor.getData();
		const data = { ...$scope.formState, poster: $scope.videoImg, description };
		$http.put(getUrl('/admin/videos'), data).then(
			(res) => {
				$scope.isLoading = false;
				if (res.data.status) {
					$scope.showToast('#videoToast', res.data.message);
					$scope.reset(null);
					const videoRow = document.getElementById(data.id)
					if (videoRow) {
						let cols = videoRow.querySelectorAll(".col")
						cols[1].innerText = data.title
						cols[2].innerText = data.views
						cols[3].innerText = data.active
					}
				}else{
					$scope.showToast('#videoToast', res.data.message, 'faile');
				}
			},
			(err) => {
				console.log(err)
				$scope.showToast('#videoToast', 'Something went wrong', 'faile');
				$scope.isLoading = false;
			},
		);
	};

	$scope.delete = (e) => {
		e.preventDefault();
		$scope.isLoading = true;
		$http.delete(getUrl(`/admin/videos?id=${$scope.formState.id}`)).then(
			(res) => {
				$scope.isLoading = false;
				$scope.showToast('#videoToast', res.data.message);
				if (res.data.status) {
					document.getElementById($scope.formState.id).remove();
					$scope.reset(null);
				}
			}, (err) => {
				console.log(err)
				$scope.showToast('#videoToast', 'Something went wrong', 'faile');
				$scope.isLoading = false;
			},
		);
	};

	$scope.reset = (e) => {
		if (e) {
			e.preventDefault();
		}
		$scope.isEdit = false;
		editor.setData("")
		$scope.formState = { ...initFormState };
		$scope.videoImg = 'https://img.youtube.com/vi//hqdefault.jpg';
	};

	$scope.loadData();
});
