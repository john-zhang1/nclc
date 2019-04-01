<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="category" items="${categories}">
    <ul class="list-group">
        <li class="list-group-item">
            <a href="<c:url value='/show-${category.id}-category' />">${category.title}</a>
            <a href="<c:url value='/add-${category.id}-category' />" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-plus"></span> add sub-category</a>
            <a href="<c:url value='/add-${category.id}-collection' />" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-plus"></span> add collection</a>

            <c:set var="collections" value="${category.collections}" scope="request"/>
            <c:set var="categoryId" value="${category.id}" scope="request"/>
            <jsp:include page="collection-node.jsp" />
            <c:set var="categories" value="${category.subCategories}" scope="request"/>
            <jsp:include page="category-node.jsp" />
        </li>
    </ul>
</c:forEach>