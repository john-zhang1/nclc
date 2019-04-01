<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Collection</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <link href="static/css/style.css" rel="stylesheet">
    </head>
 
    <body>
        <jsp:include page="../parts/navbar.jsp" />
        <c:set var="crumbs" value="${crumbs}" scope="request" />
        <jsp:include page="../parts/breadcrumb.jsp" />

        <div class="container">
            <div class="board-form">
                <form:form method="POST" modelAttribute="collection">
                    <form:input type="hidden" path="id" id="id"/>

                    <div class="form-group">
                        <label for="title">Title: </label>
                        <form:input path="title" id="title" class="form-control" placeholder="title" />
                        <form:errors path="title" cssClass="error" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label for="description">Description: </label>
                        <form:input path="description" id="description" class="form-control" placeholder="description" />
                        <form:errors path="description" cssClass="error" class="form-control" />
                    </div>

                </form:form>
            </div>

            <br/>
            Go back to <a href="<c:url value='/collection-list' />">List of All Collections</a>
        </div>

        <jsp:include page="../parts/footer.jsp" />
    </body>
</html>