<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="category" items="${categories}">
    <div class="media-list">
        <div class="media well">
            <div class="media-body">
                <h4 class="media-heading">
                    <a href="<c:url value='/show-${category.id}-category' />">${category.title}</a>
                    <span class="badge badge-pill badge-primary">10</span>
                </h4>
                <c:set var="collections" value="${category.collections}" scope="request"/>
                <c:set var="categoryId" value="${category.id}" scope="request"/>
                <jsp:include page="show-collection-node.jsp" />
                <c:set var="categories" value="${category.subCategories}" scope="request"/>
                <jsp:include page="show-category-node.jsp" />
            </div>
        </div>
    </div>
</c:forEach>