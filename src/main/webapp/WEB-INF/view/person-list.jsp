<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Category List</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <link href="static/css/style.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="parts/navbar.jsp" />
        <c:set var="crumbs" value="${crumbs}" scope="request" />
        <jsp:include page="parts/breadcrumb.jsp" />

        <div class="container">
            <h2>Person List</h2>
            <c:forEach items="${persons}" var="person">
                <tr>
                <td>${person.email}</td>
                <td>${person.gender}</td>
                <td>${person.nativeLanguage}</td>
                <td>${person.major}</td>
                <td>${person.educationalStage}</td>
                <td>${person.yearsOfLearning}</td>
                <td>${person.hasStudiedAbroad}</td>
                <td><a href="<c:url value='/edit-${person.id}-person' />">${person.email}</a></td>
                <td><a href="<c:url value='/show-${person.id}-person' />">Details</a></td>
                </tr>
            </c:forEach>

            <a href="<c:url value='/add-category' />">Add New Person</a>
        </div>

        <jsp:include page="parts/footer.jsp" />
    </body>
</html>