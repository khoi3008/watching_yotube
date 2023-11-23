<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shares</title>
<link rel="stylesheet" href="/asm/css/bootstrap.min.css" />
<link rel="stylesheet" href="/asm/css/icons/bootstrap-icons.css" />
<link rel="stylesheet" href="/asm/css/index.css" />
<link rel="stylesheet" href="/asm/css/search.css" />
</head>
<body>
	<jsp:include page="../common/admin-header.jsp"></jsp:include>

	<div class="container mt-5 position-relative" ng-app="app"
		ng-controller="shareController">
			<div ng-if="isLoading" class="spinner-border text-primary position-absolute top-0 end-0" role="status">
				<span class="visually-hidden">Loading...</span>
			</div>
		<nav>
			<div class="nav nav-tabs" id="nav-tab" role="tablist">
				<button class="nav-link active" id="nav-home-tab"
					data-bs-toggle="tab" data-bs-target="#nav-home" type="button"
					role="tab" aria-controls="nav-home" aria-selected="true">
					Favorite</button>
				<button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
					data-bs-target="#nav-profile" type="button" role="tab"
					aria-controls="nav-profile" aria-selected="false"
					ng-click="changeTab()">Favorite Users</button>
				<button class="nav-link" id="nav-contact-tab" data-bs-toggle="tab"
					data-bs-target="#nav-contact" type="button" role="tab"
					aria-controls="nav-contact" aria-selected="false"
					ng-click="changeTab()">Favorite Shares</button>
			</div>
		</nav>
		<div class="tab-content" id="nav-tabContent">
			<div class="tab-pane fade show active" id="nav-home" role="tabpanel"
				aria-labelledby="nav-home-tab">
				<table class="mt-5 table table-bordered">
					<thead>
						<tr>
							<th>Video Title</th>
							<th>Favorite Count</th>
							<th>Newest Date</th>
							<th>Oldest Date</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="report in reports">
							<td>{{report.group}}</td>
							<td>{{report.likes}}</td>
							<td>{{report.newest}}</td>
							<td>{{report.oldest}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tab-pane fade" id="nav-profile" role="tabpanel"
				aria-labelledby="nav-profile-tab">
				<div class="container-fluid mt-3 p-0 position-relative">
					<input type="text" class="form-control w-50 mb-4"
						placeholder="Video Title" aria-label="Username"
						aria-describedby="basic-addon1" id="search-box"
						ng-keyup="searchFavo()" ng-model="searchFavoriteValue" />
					<div
						class="position-absolute border rounded-2 z-3 w-50 search-result">
						<div ng-mousedown="selectFavoriteVideo($event, video) "
							ng-repeat="video in videos" class="w-100 px-2 py-2 search-item">
							{{video.title}}</div>
					</div>
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Username</th>
							<th>Fullname</th>
							<th>Email</th>
							<th>Favorite Date</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="user in favoUsers">
							<td>{{user.username}}</td>
							<td>{{user.fullname}}</td>
							<td>{{user.email}}</td>
							<td>{{user.favoriteDate}}</td>
						</tr>
					</tbody>
				</table>
				<div ng-if="noResult"
					class="alert alert-danger" role="alert">No one like this
					video.</div>
			</div>
			<div class="tab-pane fade" id="nav-contact" role="tabpanel"
				aria-labelledby="nav-contact-tab">
				<div class="container-fluid mt-3 p-0 position-relative">
					<input type="text" class="form-control w-50 mb-4"
						placeholder="Video Title" aria-label="Username"
						aria-describedby="basic-addon1" id="search-share-box"
						ng-keyup="searchShare()" ng-model="searchShareValue" />
					<div
						class="position-absolute border rounded-2 z-3 w-50 search-share-result">
						<div ng-mousedown="selectShareVideo($event, video) "
							ng-repeat="video in videos" class="w-100 px-2 py-2 search-item">
							{{video.title}}</div>
					</div>
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Sender Name</th>
							<th>Sender Email</th>
							<th>Reciever Email</th>
							<th>Send Date</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="user in shareUsers">
							<td>{{user.username}}</td>
							<td>{{user.email}}</td>
							<td>{{user.receiveEmails}}</td>
							<td>{{user.shareDate}}</td>
						</tr>
					</tbody>
				</table>
				<div ng-if="noResult"
					class="alert alert-danger" role="alert">No one share this
					video.</div>
			</div>
		</div>
	</div>

	<script src="/asm/js/bootstrap.bundle.min.js"></script>
	<script src="/asm/js/index.js"></script>
	<script src="/asm/js/angular.min.js"></script>
	<script src="/asm/js/share.js"></script>
</body>
</html>