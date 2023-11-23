<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Watch</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/icons/bootstrap-icons.css"/>
    <link rel="stylesheet" href="css/index.css"/>
    <link rel="stylesheet" href="css/watch.css"/>
</head>
<body>
<jsp:include page="../common/header.jsp">
    <jsp:param value="${isLogin}" name="isLogin"/>
    <jsp:param value="${isAdmin}" name="isAdmin"/>
</jsp:include>
<div class="container mt-5" ng-app="app" ng-controller="watchController">
    <div class="row g-4">
        <div class="col-sm-12 col-md-12 col-lg-8 d-block mb-2">
            <div class="ratio ratio-16x9">
                <iframe
                        src="https://www.youtube.com/embed/${video.id}?rel=0"
                        title="YouTube video"
                        allowfullscreen
                ></iframe>
            </div>
            <c:if test="${isLogin}">
                <div class="my-3">
                    <c:if test="${!isLikedVideo}">
                        <div data-count-favorite="${favoriteCount}" id="react" data-like="0" data-video-id="${videoId}"
                             ng-click="react()" class="d-inline-block border pointer px-3 py-2 rounded-5"><i
                                class="bi bi-heart"> {{count}}</i></div>
                    </c:if>
                    <c:if test="${isLikedVideo}">
                        <div data-count-favorite="${favoriteCount}" id="react" data-like="1" data-video-id="${videoId}"
                             ng-click="react()" class="d-inline-block border pointer px-3 py-2 rounded-5 like-color">
                            <i class="bi bi-heart-fill"></i> {{count}}
                        </div>
                    </c:if>
                    <div ng-click="showModal()" class="d-inline-block border pointer px-3 py-2 rounded-5"><i
                            class="bi bi-share-fill"></i> Share
                    </div>
                </div>
            </c:if>
            <c:if test="${!isLogin}">
                <div class="my-3">
                    <a href="${pageContext.request.contextPath}/sign-in"
                       class="d-inline-block border pointer px-3 py-2 rounded-5 text-secondary text-decoration-none">
                        <i class="bi bi-heart"> ${favoriteCount}</i>
                    </a>
                    <a href="${pageContext.request.contextPath}/sign-in"
                       class="d-inline-block border pointer px-3 py-2 rounded-5 text-secondary text-decoration-none">
                        <i class="bi bi-share-fill"></i> Share
                    </a>
                </div>
            </c:if>
            <h4 class="mt-2 mb-4">${video.title}</h4>
            <div class="rounded-2 border px-2 py-2 description">
                <div class="mb-2">${video.views} lượt xem</div>
                <p>${video.shortDescription}</p>
                <div>${video.description}</div>
            </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-4">
            <h4 class="mb-4">Video đề xuất</h4>
            <c:forEach var="item" items="${videos}">
                <a href="${pageContext.request.contextPath}/watch?id=${item.id}"
                   class="w-100 d-flex mb-2 text-decoration-none text-secondary">
                    <div
                            class="watch-img"
                            style="background-image: url('${item.poster}')"
                    ></div>
                    <div class="watch-content">
                        <div class="ps-2 watch-video-title fs-6 fw-semibold">
                                ${item.title}
                        </div>
                        <div class="ps-2 fst-italic mt-2">${item.views} lượt xem</div>
                    </div>
                </a>
            </c:forEach>
        </div>
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
                    <h5 class="modal-title" id="staticBackdropLabel">Share video now!</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body px-4">
                    <div class="input-group mt-3">
                        <input
                                type="text"
                                class="form-control add-email-input"
                                aria-describedby="basic-addon2"
                                ng-model="videoLink"
                                disabled
                        />
                        <span class="input-group-text" ng-click="copy()" id="basic-addon2">{{copyText}}</span>
                    </div>
                    <h5 class="mt-2">Or send to your friends's email</h5>
                    <div class="input-group mt-3">
                        <input
                                type="text"
                                class="form-control add-email-input"
                                placeholder="Friend's Email"
                                aria-label="Friend's Email"
                                aria-describedby="basic-addon2"
                                id="add-email-input"
                                ng-keyup="onType()"
                                ng-model="emailInput"
                        />
                        <span class="input-group-text" ng-click="addEmail()" id="basic-addon2">Add email</span>
                    </div>
                    <div ng-show="!!message" class="text-danger fs-6">{{message}}</div>
                    <div class="w-100 border rounded-3 px-2 py-2 pb-1 share-email-box mt-3">
                        <div
                                ng-repeat="email in emails track by $index"
                                class="share-email-item border rounded-1 d-flex align-items-center px-2 me-1 justify-content-between"
                        >
                            <span>{{email}}</span> <i class="bi bi-x" ng-click="removeEmail(email)"></i>
                        </div>
                    </div>
                    <button
                            type="button"
                            id="share-btn"
                            data-video-id=""
                            class="btn btn-primary mt-3 d-flex align-items-center"
                            ng-click="share()"
                            ng-disabled="isLoading"
                    >
                        <span>Share</span>
                        <i class="mx-2 bi bi-share"></i>
                        <div ng-if="isLoading" class="spinner-border spinner-border-md text-light" role="status"></div>
                    </button>
                    <div ng-if="!!successMessage" class="alert alert-success mt-2" role="alert">{{successMessage}}</div>
                    <div ng-if="!!errorMessage" class="alert alert-danger mt-2" role="alert">{{errorMessage}}</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/index.js?abc"></script>
<script src="js/angular.min.js"></script>
<script src="js/watch.js?123"></script>
</body>
</html>