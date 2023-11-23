<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="/asm/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/asm/css/icons/bootstrap-icons.css"/>
    <link rel="stylesheet" href="/asm/css/index.css"/>
    <link rel="stylesheet" href="/asm/css/profile.css"/>
</head>
<body>
<jsp:include page="../common/header.jsp">
    <jsp:param value="${isLogin}" name="isLogin"/>
    <jsp:param value="${isAdmin}" name="isAdmin"/>
</jsp:include>
<div class="container-fluid mt-5 row" ng-app="app" ng-controller="profileController">
    <div class="col-sm-12 col-md-2">
        <h5 class="ps-2 text-title-gray mb-4">Personalized</h5>
        <div
                ng-click="changeTab(0)"
                class="mt-3 ps-2 py-2 text-gray cus-nav-item rounded-3 mb-1 {{activeTab === 0 ? 'active':''}}"
        >
            Information
        </div>
        <div
                ng-click="changeTab(1)"
                class="ps-2 py-2 text-gray cus-nav-item rounded-3 mb-1 {{activeTab === 1 ? 'active':''}}"
        >
            Change your password
        </div>
        <a href="${pageContext.request.contextPath}/favorite"
           class="ps-2 d-block text-decoration-none py-2 text-gray cus-nav-item rounded-3 mb-1">Your favorite videos</a>
        <div
                ng-click="changeTab(2)"
                class="ps-2 py-2 text-gray cus-nav-item rounded-3 mb-1 {{activeTab === 2 ? 'active':''}}"
        >
            Videos just watched
        </div>
    </div>
    <div class="col-sm-12 col-md-10">
        <h3 class="ps-2 text-title-gray mb-4">Welcome to my website</h3>
        <h5 class="ps-2 text-title-gray mb-4">Hello: {{fullname}}</h5>
        <div class="cus-nav-content {{activeTab === 0 ? 'active':''}}">
            <h5 class="ps-2 text-title-gray mb-4">Edit your infomation</h5>
            <form name="profileForm" ng-submit="updateInfomation()" class="rounded-2 w-50 ps-2">
                <div class="form-floating mb-3">
                    <input
                            required
                            ng-model="information.id"
                            disabled
                            type="text"
                            class="form-control"
                            id="userName"
                            placeholder="Username"
                    />
                    <label for="userName">Username</label>
                </div>
                <div class="form-floating mb-3">
                    <input
                            required
                            ng-model="information.fullname"
                            type="text"
                            class="form-control"
                            id="fullname"
                            placeholder="Fullname"
                    />
                    <label for="fullname">Fullname</label>
                </div>
                <div class="form-floating mb-3">
                    <input
                            required
                            ng-model="information.email"
                            type="email"
                            class="form-control"
                            id="email"
                            placeholder="name@example.com"
                    />
                    <label for="email">Email</label>
                </div>
                <button ng-disabled="isUpdatingInfo" class="btn btn-primary d-flex align-items-center">
                    Save
                    <div ng-if="isUpdatingInfo" class="spinner-border text-light ms-2" role="status"></div>
                </button>
            </form>
        </div>
        <div class="cus-nav-content {{activeTab === 1 ? 'active':''}}">
            <h5 class="ps-2 text-title-gray mb-4">Change your password</h5>
            <form name="profileForm" ng-submit="changePassword()" class="rounded-2 w-50 ps-2">
                <div class="form-floating mb-3">
                    <input
                            required
                            ng-model="passwordData.password"
                            type="password"
                            class="form-control"
                            id="oldPassword"
                            placeholder="Old Password"
                    />
                    <label for="oldPassword">Old Password</label>
                </div>
                <div class="form-floating mb-3">
                    <input
                            required
                            ng-model="passwordData.newPassword"
                            type="password"
                            class="form-control"
                            id="newPassword"
                            placeholder="New Password"
                    />
                    <label for="newPassword">New Password</label>
                </div>
                <div class="form-floating mb-3">
                    <input
                            required
                            ng-model="passwordData.confirmPassword"
                            type="password"
                            class="form-control"
                            id="confirmPassword"
                            placeholder="Confirm Password"
                    />
                    <label for="confirmPassword">Confirm Password</label>
                </div>
                <button ng-disabled="isChangingPassword" class="btn btn-primary d-flex align-items-center">
                    Confirm
                    <div ng-if="isChangingPassword" class="spinner-border text-light ms-2" role="status"></div>
                </button>
            </form>
        </div>
        <div class="cus-nav-content {{activeTab === 2 ? 'active':''}}">
            <div class="row g-2">
                <div ng-repeat="video in justWatchVideos" class="col-sm-12 col-md-4 col-lg-3 video-item">
                    <div class="card h-100">
                        <a href="${pageContext.request.contextPath}/watch?id={{video.id}}"><img src="{{video.poster}}"
                                                                                                class="card-img-top"
                                                                                                alt="poster"/></a>
                        <div class="card-body">
                            <h5 class="card-title video-title">{{video.title}}</h5>
                            <p class="card-text video-short-des">
                                {{video.shortDescription}}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div ng-if="!!successMessage && loaded" class="alert alert-success mt-2 w-50" role="alert">{{successMessage}}
        </div>
        <div ng-if="!!errorMessage && loaded" class="alert alert-danger mt-2 w-50" role="alert">{{errorMessage}}</div>
    </div>
    <!-- Modal -->
    <div
            class="modal fade"
            id="staticBackdrop"
            data-bs-backdrop="static"
            data-bs-keyboard="false"
            tabindex="-1"
            aria-labelledby="staticBackdropLabel"
            aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <a href="${pageContext.request.contextPath}/home" type="button"
                       class="btn-close text-decoration-none"></a>
                </div>
                <div class="modal-body px-4">
                    <div>{{successMessage}}</div>
                    <a href="${pageContext.request.contextPath}/sign-in"
                       class="fw-semibold d-block text-decoration-none">Sign in now!</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/asm/js/bootstrap.bundle.min.js"></script>
<script src="/asm/js/angular.min.js"></script>
<script src="/asm/js/index.js?abc"></script>
<script src="/asm/js/profile.js?1234"></script>
</body>
</html>