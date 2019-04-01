<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Sample List</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <link href="static/css/style.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="parts/navbar.jsp" />
        <c:set var="crumbs" value="${crumbs}" scope="request" />
        <jsp:include page="parts/breadcrumb.jsp" />

        <div class="container">
            <h2>Samples</h2>
                <c:forEach var="sample" items="${samples}">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="">${sample.text}</a>
                            <a href="">${sample.errorText}</a>
                        </li>
                    </ul>
                </c:forEach>
        </div>

        <jsp:include page="parts/footer.jsp" />
    </body>
</html>