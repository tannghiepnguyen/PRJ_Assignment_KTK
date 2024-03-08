<%-- 
    Document   : register
    Created on : Mar 7, 2024, 7:52:31 AM
    Author     : tanng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <div>Registration</div>
        <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>

        <form action="DispatchServlet">
            Username: <input type="text" name="txtUsername" value="${param.txtUsername}" /><br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <p style="color: red">${errors.usernameLengthError}</p>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <p style="color: red">${errors.usernameIsExisted}</p>
            </c:if>
            Password: <input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <p style="color: red">${errors.passwordLengthError}</p>
            </c:if>
            Confirm Password: <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNoMatched}">
                <p style="color: red">${errors.confirmNoMatched}</p>
            </c:if>
                Full name: <input type="text" name="txtFullname" value="${param.txtFullname}" /><br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <p style="color: red">${errors.fullnameLengthError}</p>
            </c:if>
            <input type="submit" value="Create New Account" name="btnAction" />
        </form>
    </body>
</html>
