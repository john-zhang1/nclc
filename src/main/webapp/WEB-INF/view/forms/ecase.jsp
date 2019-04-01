<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Add New Case</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <link href="static/css/style.css" rel="stylesheet">
        <link href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" rel="Stylesheet"></link>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <script>
          $( function() {
              $( "#email" ).autocomplete({
                source: "search-eperson",
                minLength: 2
              });
            } );
        </script>

    </head>
 
    <body>
        <jsp:include page="../parts/navbar.jsp" />
        <c:set var="crumbs" value="${crumbs}" scope="request" />
        <jsp:include page="../parts/breadcrumb.jsp" />

        <div class="container">
            <c:choose>
                <c:when test="${registed eq 1}">
                    <div class="alert alert-success" role="alert">
                        <c:out value="${message}"></c:out>
                    </div>
                </c:when>
                <c:when test="${registed eq 0}">
                    <div class="alert alert-danger" role="alert">
                        <c:out value="${message}"></c:out>
                    </div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>

            <h2>Add a new case</h2>
            <!-- Search form -->
            <div class="board-form">
                <form:form method="POST" modelAttribute="eCaseData">
                    <div class="form-group">
                        <label for="email">Search a person by email </label>
                        <form:input path="email" id="email" class="form-control ui-widget" placeholder="email@example.com" required="required" />
                        <form:errors path="email" cssClass="error" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label for="text">Text </label>
                        <form:input path="text" id="text" class="form-control" placeholder="text" required="required" />
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
                                <button type="submit" class="btn btn-primary">Add</button>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="form-group">
                        <label for="testDate">Test Date </label>
                        <form:input path="testDate" id="testDate" class="form-control" placeholder="Test Date" />
                        <form:errors path="testDate" cssClass="error" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label for="resourceFileId">Resource File </label>
                        <form:input path="resourceFileId" id="resourceFileId" class="form-control" placeholder="Resource File" />
                        <form:errors path="resourceFileId" cssClass="error" class="form-control" />
                    </div>
                    
                </form:form>
            </div>

            <br/>
            Go back to <a href="<c:url value='/ecase-list' />">List of All Cases</a>
        </div>

        <jsp:include page="../parts/footer.jsp" />
    </body>
</html>