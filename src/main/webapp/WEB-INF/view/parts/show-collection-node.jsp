<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="collection" items="${collections}">
    <div class="media-list">
        <div class="media well">
            <div class="media-body">
                <h4 class="media-heading">
                    <a href="<c:url value='/show-${categoryId}-${collection.id}-collection' />">${collection.title}</a>
                    <span class="badge badge-pill badge-primary">10</span>
                </h4>
            </div>
        </div>
    </div>
</c:forEach>