
<%--
  Created by IntelliJ IDEA.
  User: kola
  Date: 6.7.23
  Time: 10:53 e paradites
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Manager Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
    <div class="container w-50">
        <div>
            <h2 class="text-success">Hello, ${user.userName}. Here are some name suggestions..</h2>
            <a href="/logout">log out</a>
        </div>
        <h3 class="fw-bold">Baby Names</h3>
        <table class="table table-striped">
            <c:forEach var="baby" items="${allBabies}">
                <tr>
                    <td><a href="/names/${baby.id}"><c:out value="${baby.name}"/></a> </td>
                    <td><c:out value="${baby.genders}" /></td>
                    <td>Origin: <c:out value="${baby.origin}"/> </td>
                </tr>
            </c:forEach>
        </table>
        <button type="button" class="btn btn-success text-decoration-none"><a class="text-decoration-none" style="color: aliceblue" href="/names/new"><span><</span> new name</a></button>

    </div>

</body>
</html>
