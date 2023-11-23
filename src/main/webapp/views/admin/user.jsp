<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
<link rel="stylesheet" href="/asm/css/bootstrap.min.css" />
<link rel="stylesheet" href="/asm/css/icons/bootstrap-icons.css" />
<link rel="stylesheet" href="/asm/css/index.css" />
</head>
<body>

	<jsp:include page="../common/admin-header.jsp"></jsp:include>

	<div class="container mt-4" ng-controller="userController" ng-app="app">
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
          <button
            class="nav-link"
            id="home-tab"
            data-bs-toggle="tab"
            data-bs-target="#home"
            type="button"
            role="tab"
            aria-controls="home"
            aria-selected="true"
          >
            User Manage
          </button>
        </li>
        <li class="nav-item" role="presentation">
          <button
            class="nav-link active"
            id="profile-tab"
            data-bs-toggle="tab"
            data-bs-target="#profile"
            type="button"
            role="tab"
            aria-controls="profile"
            aria-selected="false"
          >
            User List
          </button>
        </li>
      </ul>
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="home-tab">
         <form name="userForm" class="w-50 ms-2 mt-4">
            <div class="mb-3">
              <label for="username" class="form-label">Username</label>
              <input
                disabled
                required
                ng-model="formState.id"
                type="text"
                name="id"
                class="form-control"
                id="username"
              />
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">Password</label>
              <input required ng-model="formState.password" type="text" class="form-control" id="password" />
            </div>
            <div class="mb-3">
              <label for="fullname" class="form-label">Fullname</label>
              <input required ng-model="formState.fullname" type="text" class="form-control" id="fullname" />
            </div>
            <div class="mb-3">
              <label for="email" class="form-label">Email address</label>
              <input required ng-model="formState.email" type="email" class="form-control" id="email" />
            </div>
            <div class="d-flex justify-content-start align-items-center">
            	<button ng-disabled="isLoading" ng-click="update($event)" class="btn btn-primary ms-2">Update</button>
            	<button ng-disabled="isLoading" ng-click="delete($event)" class="btn btn-danger ms-2">Delete</button>
            	<div ng-if="isLoading"  class="spinner-border text-primary ms-2" role="status"></div>
            </div>
          </form>
          <div ng-show="!!successMessage" class="alert alert-success mt-3 w-50 custom-alert-theme" role="alert">{{successMessage}}</div>
          <div ng-show="!!errorMessage" class="alert alert-danger mt-3 w-50 custom-alert-theme" role="alert">{{errorMessage}}</div>
        </div>
        <div class="tab-pane fade show active" id="profile" role="tabpanel" aria-labelledby="profile-tab">
          <table class="table table-bordered mt-4">
            <thead>
              <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Fullname</th>
                <th>Email</th>
                <th>Role</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="user" items="${users}">
                <tr id="${user.id}">
                  <td class="col">${user.id}</td>
                  <td class="col">${user.password}</td>
                  <td class="col">${user.fullname}</td>
                  <td class="col">${user.email}</td>
                  <td class="col">${user.admin}</td>
                  <td ng-click="setForm('${user.id}')">Edit</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <div>
            <jsp:include page="../common/pagination.jsp">
              <jsp:param value="${totalPage}" name="totalPage" />
              <jsp:param value="${currentPage}" name="currentPage" />
              <jsp:param value="${pagesize}" name="pagesize" />
              <jsp:param value="${pageContext.request.contextPath}/admin/users" name="baseUrl" />
              <jsp:param value="container mt-5 d-flex justify-content-center" name="className" />
              <jsp:param value="6" name="point" />
            </jsp:include>
          </div>
        </div>
      </div>
    </div>
	<script src="/asm/js/bootstrap.bundle.min.js"></script>
    <script src="/asm/js/angular.min.js"></script>
    <script src="/asm/js/index.js"></script>
     <script src="/asm/js/admin-user.js?abc"></script>
</body>
</html>