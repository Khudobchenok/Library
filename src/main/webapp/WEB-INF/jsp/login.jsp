<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
    <form method="POST" action="/login" class="transparent">
        <div class="form-inner">
            <h3>Регистрация</h3>
            <label for="username">Имя пользователя</label>
            <input type="text" id="username">
            <label for="password">Пароль</label>
            <input id="password" type="password">
            <input type="checkbox" id="custom-checkbox">
            <label for="custom-checkbox">Запомнить меня</label>
            <input type="submit" value="Отправить">
        </div>
    </form>
</div>

</body>
</html>
