<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <jsp:include page="parts/navbar.jsp" />
        <h2>Registration Form</h2>

        <form:form method="POST" modelAttribute="person">
            <form:input type="hidden" path="id" id="id"/>
            <table>
                <tr>
                    <td><label for="email">Email: </label> </td>
                    <td><form:input path="email" id="email"/></td>
                    <td><form:errors path="email" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label for="firstName">First Name: </label> </td>
                    <td><form:input path="firstName" id="firstName"/></td>
                    <td><form:errors path="firstName" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label for="lastName">Last Name: </label> </td>
                    <td><form:input path="lastName" id="lastName"/></td>
                    <td><form:errors path="lastName" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label for="gender">Gender: </label> </td>
                    <td>
                        <form:select type="gender" path="gender" id="gender">
                            <form:option value="">-- Select Type Gender --</form:option>
                            <c:forEach items="${genderList}" var="option">
                                <form:option value="${option}">
                                    <c:out value="${option}"></c:out>
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                    <td><form:errors path="gender" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label for="nativeLanguage">Native Language: </label> </td>
                    <td>
                        <form:select type="nativeLanguage" path="nativeLanguage" id="nativeLanguage">
                            <form:option value="">-- Select Type Language --</form:option>
                            <c:forEach items="${languageList}" var="option">
                                <form:option value="${option}">
                                    <c:out value="${option}"></c:out>
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                    <td><form:errors path="nativeLanguage" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label for="major">Major: </label> </td>
                    <td><form:input path="major" id="major"/></td>
                    <td><form:errors path="major" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label for="educationalStage">Educational Stage: </label> </td>
                    <td>
                        <form:select type="EducationalStage" path="educationalStage" id="educationalStage">
                            <form:option value="">-- Select Type Educational Stage --</form:option>
                            <c:forEach items="${stageList}" var="option">
                                <form:option value="${option}">
                                    <c:out value="${option}"></c:out>
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><label for="yearsOfLearning">Years of Learning: </label> </td>
                    <td><form:input path="yearsOfLearning" id="yearsOfLearning"/></td>
                    <td><form:errors path="yearsOfLearning" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label for="hasStudiedAbroad">Has Studied Abroad: </label> </td>
                    <td>
                        <form:select type="hasStudiedAbroad" path="hasStudiedAbroad" id="hasStudiedAbroad">
                            <form:option value="">-- Select Yes or No --</form:option>
                            <form:option value="true">Yes</form:option>
                            <form:option value="false">No</form:option>
                        </form:select>
                    </td>
                    <td><form:errors path="nativeLanguage" cssClass="error"/></td>
                </tr>

                <tr>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${edit}">
                                <input type="submit" value="Update"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Register"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </form:form>
        <br/>
        <br/>
        Go back to <a href="<c:url value='/personlist' />">List of All Testers</a>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    </body>
</html>