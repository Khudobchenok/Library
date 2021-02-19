<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<nav>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <ul class="topmenu">
        <li><a href="/">Интро</a></li>
        <li><a href="/home">Главная</a></li>
        <ul class="submenu">
            <li><a href="">Category</a></li>
            <li><a href="">Author</a></li>
            <li><a href="">Archive</a></li>
            <li><a href="">Tags</a></li>
        </ul>
        </li>
        <li><a href="" class="down">Книги</a>
            <ul class="submenu">
                <li><a href="">Category</a></li>
                <li><a href="">Author</a></li>
                <li><a href="">Archive</a></li>
                <li><a href="">Tags</a></li>
            </ul>
        </li>
        <li><a href="/login" class="down">Войти</a>
            <ul class="submenu">
                <li><form method="POST" action="/login" class="transparent">
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
                </form></li>
            </ul>
        <li><a href="/registration">Зарегестрироваться</a></li>
    </ul>
    </ul>
</nav>
</body>
</html>