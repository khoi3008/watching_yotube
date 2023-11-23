<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forget Password</title>
	<link rel="stylesheet" href="/asm/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/asm/css/icons/bootstrap-icons.css" />
    <link rel="stylesheet" href="/asm/css/index.css" />
    <link rel="stylesheet" href="/asm/css/form.css" />
</head>
<body>
 <div
      ng-app="app"
      ng-controller="forgetPasswordController"
      class="container-fluid d-flex align-items-center justify-content-center"
    >
      <form
        name="forgetPassword"
        ng-submit="submit()"
        class="form-wrapper mt-5 border px-3 py-2 pb-5 border-secondary-subtle shadow-lg rounded-3"
      >
        <a href="${pageContext.request.contextPath}/home" class="d-flex align-items-center justify-content-center">
          <i class="bi bi-youtube form-icon"></i>
        </a>
        <h4 class="form-tittle text-center text-primary mb-4">Forget Password!</h4>
        <div class="form-floating mb-4">
          <input
            ng-model="formState.id"
            name="id"
            type="text"
            class="form-control"
            id="username"
            placeholder="Username"
            required
          />
          <label for="username">Username</label>
        </div>
        <div class="form-floating mb-4">
          <input
            ng-model="formState.email"
            name="email"
            type="email"
            class="form-control"
            id="floatingInput"
            placeholder="name@example.com"
            required
          />
          <label for="floatingInput">Email address</label>
        </div>
        <div class="d-flex justify-content-between">
          <a href="${pageContext.request.contextPath}/sign-in" class="mt-3 fw-semibold d-block text-decoration-none">Sign in</a>
          <a href="${pageContext.request.contextPath}/sign-up" class="mt-3 fw-semibold d-block text-decoration-none">Signup now</a>
        </div>
        <div class="mt-4">
        	<button ng-disabled="isLoading" class="btn btn-primary d-flex align-items-center">
        		Confirm <div ng-if="isLoading" class="spinner-border text-light ms-2" role="status"></div>
        	</button>
        </div>
        <div ng-if="!!successMessage" class="alert alert-success mt-2" role="alert">{{successMessage}}</div>
        <div ng-if="!!errorMessage" class="alert alert-danger mt-2" role="alert">{{errorMessage}}</div>
      </form>
    </div>
    <script src="/asm/js/angular.min.js"></script>
    <script src="/asm/js/index.js"></script>
    <script src="/asm/js/forget-password.js"></script>
</body>
</html>