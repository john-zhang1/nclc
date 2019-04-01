<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Person List</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <link href="static/css/style.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="parts/navbar.jsp" />
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item"><a href="#">Library</a></li>
            <li class="breadcrumb-item active" aria-current="page">Data</li>
          </ol>
        </nav>
        <%--<jsp:include page="parts/vertbar.jsp" />--%>
<div class="container">
        <img src="parts/logo.png"/>
        <img src="<c:url value="/resources/logo.png"/>" alt="Logo"
            height="126" width="411">
        <br>
        <h2>List of Person</h2>  
        <table>
            <tr>
                <td>ID</td><td>Email</td><td>First Name</td><td>Last Name</td><td>Gender</td><td>Native Language</td><td>Major</td><td>College</td><td>Years</td><td>Study Abroad</td></td>
            </tr>
            <c:forEach items="${persons}" var="person">
                <tr>
                <td>${person.id}</td>
                <td>${person.email}</td>
                <td>${person.firstName}</td>
                <td>${person.lastName}</td>
                <td>${person.gender}</td>
                <td>${person.nativeLanguage}</td>
                <td>${person.major}</td>
                <td>${person.college}</td>
                <td>${person.yearsOfLearning}</td>
                <td>${person.hasStudiedAbroad}</td>
                <td><a href="<c:url value='/edit-${person.id}-person' />">${person.firstName} ${person.lastName}</a></td>
                <td><a href="<c:url value='/delete-${person.id}-person' />">delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="<c:url value='/new' />">Add New Tester</a>
</div>

        <jsp:include page="parts/footer.jsp" />
    </body>
</html>