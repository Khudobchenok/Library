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
    <sec:authorize access="hasRole('ADMIN')">
        <a href="/addAuthor" class="floating-button">New author</a>
        <a href="/addBook" class="floating-button">New book</a>
    </sec:authorize>
<table>
    <c:forEach items="${allBooks}" var="book">
        <tr>
            <td><font size="6" color="black" face="Times, Times New Roman, serif">${book.author.name}</font> <%--<br> ${book.author.name}--%></td>
            <td><img src="https://html5book.ru/wp-content/uploads/2015/04/dress-2.png"></td>
            <td><font size="6" color="black", face="Marker Felt, fantasy">${book.name}</font>
                <br><font size="5" color="black", face="Snell Roundhand, cursive"> ${book.description} </font></td>
        </tr>
    </c:forEach>
</table>
</center>
<blockquote>
    <p>While we are postponing, life speeds by.</p>
    <footer>— <cite>Seneca</cite></footer>
</blockquote>
</body>
</html>