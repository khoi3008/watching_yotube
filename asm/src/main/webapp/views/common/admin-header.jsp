<nav class="navbar navbar-expand-lg shadow-sm">
  <div class="container-fluid">
    <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/admin/videos">
      <i class="bi bi-youtube me-2 fs-2" style="color: #f00"></i>
      <span>YouTube Admin</span>
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
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/admin/videos">Videos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/admin/users">Users</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/admin/reports">Reports</a>
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
    