<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Tester Registration Form</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <link href="static/css/style.css" rel="stylesheet">
    </head>
 
    <body>
        <jsp:include page="../parts/navbar.jsp" />
        <jsp:include page="../parts/breadcrumb.jsp">
            <jsp:param name="crumbs" value="${crumbs}"/>
        </jsp:include>

        <div class="container">
            <div class="board-form">
                <form:form method="POST" modelAttribute="sample">
                    <form:input type="hidden" path="id" id="id"/>

                    <div class="form-group">
                        <label for="text">Text </label>
                        <form:input path="text" id="text" class="form-control" placeholder="text" />
                        <form:errors path="text" cssClass="error" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label for="errorText">Error Text </label>
                        <form:input path="errorText" id="errorText" class="form-control" placeholder="Error Text" />
                        <form:errors path="errorText" cssClass="error" class="form-control" />
                    </div>

                    <div class="form-group">
                        <c:choose>
                            <c:when test="${edit}">
                                <button type="submit" class="btn btn-primary">Update</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-primary">Register</button>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </form:form>
            </div>

            <br/>
            Go back to <a href="<c:url value='/sample-list' />">List of All Samples</a>
        </div>

        <jsp:include page="../parts/footer.jsp" />
    </body>
</html>