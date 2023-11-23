  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
  <nav class="navbar navbar-expand-lg shadow-sm">
      <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/home">
          <i class="bi bi-youtube me-2 fs-2" style="color: #f00"></i>
          <span>YouTube</span>
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/<c:if test="${isLogin}">favorite </c:if><c:if test="${!isLogin}">sign-in</c:if>">My Favorites</a>
            </li>
            
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                My Account
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <c:if test="${isLogin}">
                <li>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/profile"><i class="bi bi-person-fill me-2"></i>Profile </a>
                </li>
                </c:if>
                <li>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/forget-password"><i class="bi bi-pencil-fill me-2"></i>Forget Password</a>
                </li>
                <li><hr class="dropdown-divider" /></li>
                <c:if test="${!isLogin}">
                <li>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/sign-in"><i class="bi bi-person-fill-add me-2"></i>Sign In / Sign up</a>
                </li>
                </c:if>
                <c:if test="${param.isAdmin}">
                  <li>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/videos"><i class="bi bi-gear me-2"></i> Admin</a>
                  </li>
               </c:if>
                <c:if test="${param.isLogin}">
                  <li>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/api/account/sign-out"><i class="bi bi-box-arrow-left me-2"></i>Sign out</a>
                  </li>
                </c:if>
              </ul>
            </li>
          </ul>
          <div class="ms-1">
            <input type="checkbox" class="tonggle-theme-btn" id="tonggleTheme" />
            <label for="tonggleTheme" class="tonggle-theme-label">
              <i class="bi bi-moon-fill"></i>
              <i class="bi bi-sun-fill"></i>
              <div class="tonggle-theme-ball"></div>
            </label>
          </div>
        </div>
      </div>
    </nav>