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
            <h1>CRead</h1>
            <div class="lead">
CRead is to build a Chinese oral reading error analysis system using the protocol as well as the existing and additional data collected from college and high school CFL learners. This system can help store, analyze, and find patterns about the oral reading errors made by CFL learners when they read Chinese texts. The system will have five functions, namely collecting data, storing data, discovering data, analyzing and visualizing data, and predicting trends. The system may offer new insights into the reading processes and strategies employed by CFL readers so that more effective Chinese language instruction can be designed and implemented.
            </div>
          </div>
        </main>

        <jsp:include page="parts/footer.jsp" />
    </body>
</html>