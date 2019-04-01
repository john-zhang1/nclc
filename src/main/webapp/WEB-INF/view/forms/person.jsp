<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Error Case Form</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <link href="static/css/style.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="../parts/navbar.jsp" />
        <jsp:include page="../parts/breadcrumb.jsp">
            <jsp:param name="crumbs" value="${crumbs}"/>
        </jsp:include>

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

            <h2>Create a person</h2>

            <div class="board-form">            
                <form:form method="POST" modelAttribute="eperson">
                    <form:input type="hidden" path="id" id="id"/>

                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label for="email">Email: </label>
                            <form:input path="email" id="email" type="email" class="form-control" placeholder="Email" required="required"/>
                            <form:errors path="email" cssClass="error" class="form-control" />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="firstName">First Name: </label>
                            <form:input path="firstName" id="firstName" class="form-control" placeholder="First Name" />
                            <form:errors path="firstName" cssClass="error" class="form-control" />
                        </div>
                        <div class="form-group col-md-6">
                            <label for="lastName">Last Name: </label>
                            <form:input path="lastName" id="lastName" class="form-control" placeholder="Last Name" />
                            <form:errors path="lastName" cssClass="error" class="form-control" />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="gender">Gender: </label>
                            <form:select type="gender" path="gender" id="gender" class="form-control" required="required">
                                <form:option value="">-- Select Type Gender --</form:option>
                                <c:forEach items="${genderList}" var="option">
                                    <form:option value="${option}">
                                        <c:out value="${option}"></c:out>
                                    </form:option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="gender" cssClass="error" class="form-control" />
                        </div>
                        <div class="form-group col-md-6">
                            <label for="nativeLanguage">Native Language: </label>
                            <form:select type="nativeLanguage" path="nativeLanguage" id="nativeLanguage" class="form-control" required="required">
                                <form:option value="">-- Select Type Language --</form:option>
                                <c:forEach items="${languageList}" var="option">
                                    <form:option value="${option}">
                                        <c:out value="${option}"></c:out>
                                    </form:option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="nativeLanguage" cssClass="error" class="form-control" />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="major">Major: </label>
                            <form:input path="major" id="major" class="form-control" placeholder="Major" />
                            <form:errors path="major" cssClass="error" class="form-control" />
                        </div>
                        <div class="form-group col-md-6">
                            <label for="educationalStage">Educational Stage: </label>
                            <form:select type="EducationalStage" path="educationalStage" id="educationalStage" class="form-control" required="required">
                                <form:option value="">-- Select Type Educational Stage --</form:option>
                                <c:forEach items="${stageList}" var="option">
                                    <form:option value="${option}">
                                        <c:out value="${option}"></c:out>
                                    </form:option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="educationalStage" cssClass="error" class="form-control" />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="yearsOfLearning">Years of Learning: </label>
                            <form:input path="yearsOfLearning" id="yearsOfLearning" class="form-control" placeholder="Years of Learning" />
                            <form:errors path="yearsOfLearning" cssClass="error" class="form-control" />
                        </div>
                        <div class="form-group col-md-6">
                            <label for="hasStudiedAbroad">Has Studied Abroad: </label>
                            <form:select type="hasStudiedAbroad" path="hasStudiedAbroad" id="hasStudiedAbroad" class="form-control" required="required">
                                <form:option value="">-- Select Yes or No --</form:option>
                                <form:option value="true">Yes</form:option>
                                <form:option value="false">No</form:option>
                            </form:select>
                            <form:errors path="hasStudiedAbroad" cssClass="error" class="form-control" />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <c:choose>
                                <c:when test="${edit}">
                                    <button type="submit" class="btn btn-primary">Update</button>
                                    <a class="btn btn-primary" href="/add-person" role="button">New</a>
                                    <a class="btn btn-primary" href="/person-list" role="button">Return</a>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary">Register</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form:form>
            </div>
            <br/>
            Go back to <a href="<c:url value='/person-list' />">List of All Persons</a>
        </div>

        <jsp:include page="../parts/footer.jsp" />
    </body>
</html>