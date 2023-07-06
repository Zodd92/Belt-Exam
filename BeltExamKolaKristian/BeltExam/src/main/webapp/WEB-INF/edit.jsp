<%--
  Created by IntelliJ IDEA.
  User: kola
  Date: 6.7.23
  Time: 2:39 e pasdites
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
    <title>Create a Task</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container w-50">
    <h2 class="fw-bold">Change ${baby.name}</h2>
    <form:form action="/names/${baby.id}/edit" modelAttribute="baby" class="form" method="put">
        <table class="table table-borderless">
            <tr>
                <td>
                    <form:label for="name" path="name">New Name:</form:label>
                    <form:input type="text" path="name" class="form-control"/>
                    <form:errors path="name"></form:errors>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label for="genders" path="genders">Gender:</form:label>
                    <form:select path="genders">
                        <form:option value="Neutral">Neutral</form:option>
                        <form:option value="Female">Female</form:option>
                        <form:option value="Male">Male</form:option>
                    </form:select>
                    <form:errors path="genders"></form:errors>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label for="origin" path="origin">Origin:</form:label>
                    <form:input type="text" path="origin" class="form-control"/>
                    <form:errors path="origin" class="error"></form:errors>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label for="meaning" path="meaning">Meaning:</form:label>
                    <form:input type="text" path="meaning" class="form-control"/>
                    <form:errors path="meaning" ></form:errors>
                </td>
            </tr>
        </table>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button type="submit" class="btn btn-outline-secondary" style="box-shadow: 8px 8px grey">Submit</button>
            <button type="button" class="btn btn-outline-secondary text-decoration-none" style="box-shadow: 8px 8px grey"><a class="text-decoration-none text-secondary" href="/home">Cancel</a></button>
        </div>
    </form:form>
    <form:form action="/delete/${baby.id}" method="delete"><button class="btn btn-danger d-grid gap-2 d-md-block" style="  position: relative;bottom: 54px;left: 16px;">Delete</button></form:form>
</div>
</body>
</html>
