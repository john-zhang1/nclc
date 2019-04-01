<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>File Upload Form</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <link href="static/css/style.css" rel="stylesheet">
    </head>

   <body>
        <jsp:include page="../parts/navbar.jsp" />
        <c:set var="crumbs" value="${crumbs}" scope="request" />
        <jsp:include page="../parts/breadcrumb.jsp" />

        <div class="container">
            <div class="board-form">
                <form:form method="POST" modelAttribute = "tFileData" enctype = "multipart/form-data">
                    <div class="form-group">
                        <label for="file">Please select a PDF file to upload: </label>
                        <form:input path="file" type = "file" id="file" class="form-control" />
                        <form:errors path="file" cssClass="error" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label for="description">Description </label>
                        <form:textarea path="description" id="description" class="form-control" rows="5" cols="30" />
                        <form:errors path="description" cssClass="error" class="form-control" />
                    </div>
                    <input type = "submit" value = "upload" />
                </form:form>
            </div>
        </div>
        <jsp:include page="../parts/footer.jsp" />

   </body>
</html>

