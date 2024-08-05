<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.example.CSRFProtection" %>
<%
    HttpSession session = request.getSession();
    String csrfToken = CSRFProtection.generateCSRFToken(session);
%>
<!DOCTYPE html>
<html>
<head>
    <title>CSRF Protected Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        <input type="hidden" name="csrf_token" value="<%= csrfToken %>">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>