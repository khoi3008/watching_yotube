<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign-up</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/icons/bootstrap-icons.css" />
    <link rel="stylesheet" href="css/index.css" />
    <link rel="stylesheet" href="css/form.css" />
</head>
<body>
<div
      class="container-fluid d-flex align-items-center justify-content-center"
      ng-app="app"
      ng-controller="signupController"
    >
      <form
        name="signup"
        ng-submit="signUp()"
        class="form-wrapper mt-5 border px-3 py-2 pb-5 border-secondary-subtle shadow-lg rounded-3"
      >
        <a href="${pageContext.request.contextPath}/home" class="d-flex align-items-center justify-content-center">
          <i class="bi bi-youtube form-icon"></i>
        </a>
        <h4 class="form-tittle text-center text-primary mb-4">Sign up with youtube</h4>
        <div class="form-floating mb-4">
          <input
            ng-model="formState.id"
            required
            name="id"
            type="text"
            class="form-control"
            id="username"
            placeholder="Username"
          />
          <label for="username">Username</label>
        </div>
        <div class="form-floating mb-4">
          <input
            ng-model="formState.password"
            required
            name="password"
            type="password"
            class="form-control"
            id="floatingPassword"
            placeholder="Password"
          />
          <label for="floatingPassword">Password</label>
        </div>
        <div class="form-floating mb-4">
          <input
            ng-model="formState.confirmPassword"
            required
            name="confirmPassword"
            type="password"
            class="form-control"
            id="confirmPassword"
            placeholder="Password"
          />
          <label for="confirmPassword">Confirm Password</label>
        </div>
        <div class="d-flex justify-content-between align-items-center">
          	<button ng-disabled="isLoading" class="btn btn-primary d-flex align-items-center">
        		Sign up <div ng-if="isLoading" class="spinner-border text-light ms-2" role="status"></div>
        	</button>
          <a href="${pageContext.request.contextPath}/sign-in" class="fw-semibold d-block text-decoration-none">Sign in</a>
        </div>
        <div ng-show="!!errorMessage" class="alert alert-danger mt-4" role="alert">{{errorMessage}}</div>
        <div ng-show="!!successMessage" class="alert alert-success mt-4" role="alert">{{successMessage}}</div>
      </form>
    </div>
    <script src="/asm/js/index.js"></script>
    <script src="/asm/js/angular.min.js"></script>
    <script src="/asm/js/sign-up.js"></script>
</body>
</html>