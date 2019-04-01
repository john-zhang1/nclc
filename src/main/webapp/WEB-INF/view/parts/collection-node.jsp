<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="collection" items="${collections}">
    <ul class="list-group">
        <li class="list-group-item">
            <a href="<c:url value='/show-${categoryId}-${collection.id}-collection' />">${collection.title}</a>
            <a href="<c:url value='/add-${categoryId}-${collection.id}-ecase' />" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-plus"></span> add case</a>
        </li>
    </ul>
</c:forEach>