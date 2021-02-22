<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Books</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/books.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/author.css">
    <link rel="icon" type="image/png" href="${contextPath}/resources/images/icons/favicon.ico"/>
</head>
<body>
<nav>
    <ul class="topmenu">
        <li><a href="/welcome">Main</a></li>
        <li><a href="/books">Books</a></li>
        <sec:authorize access="!isAuthenticated()">
        <li><a href="/login">Sign in</a>
            <ul class="submenu">
                <li><a href="/registration">Sign up</a></li>
            </ul>
            </sec:authorize>
        <li><a href="/">Contacts</a></li>
        <sec:authorize access="isAuthenticated()">
        <li><a href="/logout">Sign out</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
        <li><a href="/admin">Admin</a>
            </sec:authorize>
    </ul>
</nav>
<div class="container">
    <div class="post">
        <div class="post-image">
            <img src="https://html5book.ru/wp-content/uploads/2019/03/postimage1.jpg">
        </div>
        <div class="post-content">
            <div class="post-header">
                <h1>Сложно сказать, почему чистый разум не скован границами</h1>
                <div class="post-meta">
                    <time datetime="2019-04-01">Апрель, 1</time>
                    <span class="author">Мария Малиновая</span>
                    <span class="category">Философия</span>
                </div>
            </div>
            <p>Идейные соображения высшего порядка, а также курс на социально-ориентированный национальный проект создает
                необходимость включения в производственный план целого ряда внеочередных мероприятий с учетом комплекса
                прогресса профессионального сообщества.
            </p>
        </div>
    </div>
</div>
</body>
</html>