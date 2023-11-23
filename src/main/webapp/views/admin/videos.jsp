<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin videos</title>
<link rel="stylesheet" href="/asm/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/asm/css/icons/bootstrap-icons.css" />
    <link rel="stylesheet" href="/asm/css/index.css" />
</head>
<body>
<jsp:include page="../common/admin-header.jsp"></jsp:include>
<div class="container mt-5" ng-app="app" ng-controller="videoController">
      <h2>Video Management</h2>
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
          <button
            class="nav-link "
            id="video-edition-tab"
            data-bs-toggle="tab"
            data-bs-target="#video-edition"
            type="button"
            role="tab"
            aria-controls="video-edition"
            aria-selected="true"
          >
            Video Edition
          </button>
        </li>
        <li class="nav-item" role="presentation">
          <button
            class="nav-link active"
            id="video-list-tab"
            data-bs-toggle="tab"
            data-bs-target="#video-list"
            type="button"
            role="tab"
            aria-controls="video-list"
            aria-selected="false"
          >
            Video List
          </button>
        </li>
      </ul>
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade " id="video-edition" role="tabpanel" aria-labelledby="video-edition-tab">
          <form name="videoManage" class="container-fluid row px-2 mt-5">
            <div class="col-4">
              <img src="{{videoImg}}" id="thumbnail" class="w-100" alt="Video Thumbnail" />
            </div>
            <div class="col-8">
              <div class="mb-3">
                <label for="video-id" class="form-label">Youtube Id</label>
                <input
                  ng-keyup="onChangeId()"
                  ng-model="formState.id"
                  type="text"
                  class="form-control"
                  id="video-id"
                  name="id"
                  ng-disabled="isEdit"
                />
              </div>
              <div class="mb-3">
                <label for="title" class="form-label">Video Title</label>
                <input ng-model="formState.title" type="text" class="form-control" id="title" name="title" />
              </div>
              <div class="mb-3">
                <label for="views" class="form-label">View Count</label>
                <input
                  ng-model="formState.views"
                  value="0"
                  type="number"
                  class="form-control"
                  id="views"
                  name="views"
                />
              </div>
              <div class="mb-3">
                <div class="form-check form-check-inline">
                  <input
                    ng-model="formState.active"
                    checked
                    class="form-check-input"
                    type="radio"
                    name="active"
                    id="active"
                    value="true"
                  />
                  <label class="form-check-label" for="active">Active</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    ng-model="formState.active"
                    class="form-check-input"
                    type="radio"
                    name="active"
                    id="inactive"
                    value="false"
                  />
                  <label class="form-check-label" for="inactive">Inactive</label>
                </div>
              </div>
            </div>
            <div class="col-12 mt-2">
              <div class="form-floating">
                <textarea
                  class="form-control"
                  ng-model="formState.shortDescription"
                  id="des"
                  style="height: 140px"
                  name="shortDescription"
                ></textarea>
                <label for="des">Description</label>
              </div>
            </div>
            <div class="col-12 mt-2">
            	<div id="editor"></div>
            </div>
            <div class="col-12 d-flex justify-content-end mt-3 align-items-center mb-3">
            	<div ng-if="isLoading"  class="spinner-border text-primary me-2" role="status"></div>
              <button ng-disabled="isEdit || isLoading" ng-click="submit($event)" class="btn btn-primary me-2 ">
              	Create 
              </button>
              <button ng-disabled="!isEdit || isLoading" ng-click="update($event)" class="btn btn-primary me-2 ">
              	Update 
              </button>
              <button ng-disabled="!isEdit || isLoading" ng-click="delete($event)" class="btn btn-danger me-2 ">
              	Delete 
              </button>
              <button ng-disabled="isLoading" ng-click="reset($event)" class="btn btn-primary me-2">
              	Reset
              </button>
            </div>
          </form>
        </div>
        <div class="tab-pane fade show active" id="video-list" role="tabpanel" aria-labelledby="video-list-tab">
          <table class="table table-bordered mt-5">
            <thead>
              <tr>
                <th>Youtube Id</th>
                <th>Video Title</th>
                <th>View Count</th>
                <th>Status</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody id="root-list-video">
              <c:forEach var="video" items="${videos}">
              	<tr id="${video.id}">
                <td class="col">${video.id}</td>
                <td class="col">${video.title}</td>
                <td class="col">${video.views}</td>
                <td class="col">${video.active}</td>
                <td ng-click="setForm('${video.id}')">Edit</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
          <jsp:include page="../common/pagination.jsp">
       		<jsp:param value="${totalPage}" name="totalPage"/>
       		<jsp:param value="${currentPage}" name="currentPage"/>
       		<jsp:param value="${pagesize}" name="pagesize"/>
       		<jsp:param value="${pageContext.request.contextPath}/admin/videos" name="baseUrl"/>
       		<jsp:param value="container mt-5 d-flex justify-content-center" name="className"/>
       		<jsp:param value="6" name="point"/>
       </jsp:include>
        </div>
      </div>
      <!-- Toast -->
    <div class="toast-container position-fixed top-0 start-50 translate-middle-x">
      <div id="videoToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
          <strong class="me-auto title {{toastData.class}}">Message</strong>
          <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body d-flex align-items-center {{toastData.class}}">
          <i class="me-2 icon {{toastData.icon}}"></i>
          <span class="message fw-semibold">{{toastData.message}}</span>
        </div>
      </div>
    </div>
    </div>
    
    <script src="/asm/js/bootstrap.bundle.min.js"></script>
    <script src="/asm/js/index.js"></script>
    <script src="/asm/js/ckeditor.js"></script>
    <script src="/asm/js/editor-config.js"></script>
    <script src="/asm/js/angular.min.js"></script>
    <script src="/asm/js/video.js"></script>
</body>
</html>