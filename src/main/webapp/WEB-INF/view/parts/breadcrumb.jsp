<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <c:forEach var="crumb" items="${crumbs}" varStatus="status">
            <c:choose>
                <c:when test="${status.last}">
                    <li class="breadcrumb-item active" aria-current="page">${crumb.value}</li>
                </c:when>
                <c:otherwise>
                    <li class="breadcrumb-item"><a href="${crumb.key}">${crumb.value}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ol>
</nav>