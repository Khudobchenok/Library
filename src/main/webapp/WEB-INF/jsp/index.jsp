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
        <li><a href="/">Главная</a></li>
        <li><a href="/news">Новости</a></li>
            <ul class="submenu">
                <li><a href="">Category</a></li>
                <li><a href="">Author</a></li>
                <li><a href="">Archive</a></li>
                <li><a href="">Tags</a></li>
            </ul>
        </li>
        <li><a href="" class="down">Portfolio</a>
            <ul class="submenu">
                <li><a href="">Category</a></li>
                <li><a href="">Author</a></li>
                <li><a href="">Archive</a></li>
                <li><a href="">Tags</a></li>
            </ul>
        </li>
        <li><a href="/login" class="down">Войти</a>
        <li><a href="/registration">Зарегестрироваться</a></li>
    </ul>
</nav>
<%--<div>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/login">Войти</a></h4>
        <h4><a href="/registration">Зарегистрироваться</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Выйти</a></h4>
    </sec:authorize>
    <h4><a href="/news">Новости (только пользователь)</a></h4>
    <h4><a href="/admin">Пользователи (только админ)</a></h4>
</div>--%>
</body>
</html>