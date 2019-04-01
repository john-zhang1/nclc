<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Home</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <link href="static/css/style.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="parts/navbar.jsp" />
        <c:set var="crumbs" value="${crumbs}" scope="request" />
        <jsp:include page="parts/breadcrumb.jsp" />
        <main role="main" class="container">
          <div class="jumbotron">
            <h2>Data Ingestion</h2>
            <div class="lead px-2">Create a <a href="/add-person">person</a> if not exists</div>
            <div class="lead px-2">Create a <a href="/category-list">category/sub-category or collection</a> if not exists</div>
            <div class="lead px-2">Create a error <a href="/add-ecase">case</a></div>
          </div>
        </main>

        <jsp:include page="parts/footer.jsp" />
    </body>
</html>


