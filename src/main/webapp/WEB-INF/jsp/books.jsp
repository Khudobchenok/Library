<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Books</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/books.css">
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
<center>
<table style="display: inline-block;">
    <tr>
        <th colspan="2">
            <sec:authorize access="hasRole('ADMIN')">
                <a href="/addAuthor" class="floating-button">New author</a>
            </sec:authorize>
        </th>
    </tr>
    <c:forEach items="${allAuthors}" var="author">
        <tr>
            <td><a href="authorPage/?actualAuthor=${author.id}"><img src="${author.imageFileName}" width="100" height="100"></a></td>
            <td><a href="authorPage/?actualAuthor=${author.id}" class="book-name">
                    <div class="book-name">${author.name}</div>
                </a>
                <hr>
                <br>
                <a href="authorPage/?actualAuthor=${author.id}" class="description">
                    <div class="description">${author.biography}</div>
                </a>
            </td>
        </tr>
    </c:forEach>

</table>
<table style="float: left;">
    <tr>
        <th colspan="2">
            <sec:authorize access="hasRole('ADMIN')">
                <a href="/addBook" class="floating-button">New book</a>
            </sec:authorize>
        </th>
    </tr>
    <c:forEach items="${allBooks}" var="book">
        <tr>
            <td><a href="bookPage/?actualBook=${book.id}"><img src="${book.imageFileName}" width="100" height="100"></a></td>
            <td><a href="bookPage/?actualBook=${book.id}" class="book-name">
                    <div class="book-name">${book.name}</div>
                </a>
                <hr>
                <br>
                <a href="bookPage/?actualBook=${book.id}" class="description">
                    <div class="description">${book.description}</div>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</center>
</body>
</html>