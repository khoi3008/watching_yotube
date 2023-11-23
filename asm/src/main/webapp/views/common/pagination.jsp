<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav
        id="page-root"
        class="${param.className}"
>
    <c:if test="${param.totalPage <= 6}">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="${param.baseUrl}?page=1&pagesize=${param.pagesize}" tabindex="-1">
                    <i class="bi bi-chevron-double-left"></i>
                </a>
            </li>
            <c:forEach var="i" begin="1" end="${param.totalPage}">
                <li class="page-item <c:if test="${param.currentPage == i}">active</c:if>">
                    <a class="page-link" href="${param.baseUrl}?page=${i}&pagesize=${param.pagesize}"><c:out
                            value="${i}"/></a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="${param.baseUrl}?page=${param.totalPage}&pagesize=${param.pagesize}">
                    <i class="bi bi-chevron-double-right"></i>
                </a>
            </li>
        </ul>
    </c:if>

    <c:if test="${param.totalPage > 6}">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="${param.baseUrl}?page=1&pagesize=${param.pagesize}" tabindex="-1">
                    <i class="bi bi-chevron-double-left"></i>
                </a>
            </li>

            <li class="page-item <c:if test="${param.currentPage == 1}">active</c:if>">
                <a class="page-link" href="${param.baseUrl}?page=${1}&pagesize=${param.pagesize}">1</a>
            </li>

            <c:if test="${param.currentPage >= 4 }">
                <li class="page-item ">
                    <a class="page-link">...</a>
                </li>
            </c:if>

            <c:if test="${param.currentPage < param.totalPage - 2 }">
                <c:forEach var="i" begin="${param.currentPage >= 2 ? param.currentPage-1 : 1}"
                           end="${param.currentPage >= 2 ? param.currentPage +1 : 3}">
                    <c:if test="${i !=1 }">
                        <li class="page-item <c:if test="${param.currentPage == i}">active</c:if>">
                            <a class="page-link" href="${param.baseUrl}?page=${i}&pagesize=${param.pagesize}"><c:out
                                    value="${i}"/></a>
                        </li>
                    </c:if>
                </c:forEach>
            </c:if>


            <c:if test="${param.currentPage >= param.totalPage - 2 }">
                <c:forEach var="i" begin="${param.totalPage - 3}" end="${ param.totalPage - 1}">
                    <li class="page-item <c:if test="${param.currentPage == i}">active</c:if>">
                        <a class="page-link" href="${param.baseUrl}?page=${i}&pagesize=${param.pagesize}"><c:out
                                value="${i}"/></a>
                    </li>
                </c:forEach>
            </c:if>

            <c:if test="${(param.currentPage >= 4 && param.currentPage < param.totalPage-2) || (param.currentPage <= 3) }">
                <li class="page-item ">
                    <a class="page-link">...</a>
                </li>
            </c:if>
            <li class="page-item <c:if test="${param.currentPage == param.totalPage}">active</c:if>">
                <a class="page-link"
                   href="${param.baseUrl}?page=${param.totalPage}&pagesize=${param.pagesize}">${param.totalPage}</a>
            </li>


            <li class="page-item">
                <a class="page-link" href="${param.baseUrl}?page=${param.totalPage}&pagesize=${param.pagesize}">
                    <i class="bi bi-chevron-double-right"></i>
                </a>
            </li>
        </ul>
    </c:if>
</nav>