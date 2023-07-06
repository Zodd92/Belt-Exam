<%--        @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
  Created by IntelliJ IDEA.
  User: kola
  Date: 6.7.23
  Time: 10:43 e paradites
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
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
    <div class="container w-50" style="border-bottom: black">
        <h2 class="fw-bold">${baby.name}</h2>
        <h4>(added by ${baby.user.userName})</h4>
        <h4>Gender: ${baby.genders}</h4>
        <h4>Origin: ${baby.origin}</h4>
        <p>Meaning: ${baby.meaning}.</p>
        <button type="button" class="btn btn-success text-decoration-none"><a class="text-decoration-none text-secondary" href="/names/${baby.id}/update"><span><</span> edit</a></button>
    </div>

</body>
</html>
