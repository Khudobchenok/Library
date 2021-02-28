<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Main</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
    <link rel="icon" type="image/png" href="${contextPath}/resources/images/icons/favicon.ico"/>
    <script src="${contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
    <script language="javascript">
        function symbols()
        {
            var symbols = textarea.value.length;
            if (symbols <= 1500) {
                document.getElementById('symbols').innerHTML =
                    'Entered <span class="red">' + symbols + '</span> symbols out of 1500';
            }
            else document.getElementById('symbols2').innerHTML =
                'You entered a lot of symbols (out of 1500)';
        }
    </script>
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
<form:form class="new_form" method="POST" modelAttribute="authorForm">
    <center>
        <ul>
            <li>
                <h2>Add new Author</h2>
                <p></p>
                <span class="required_notification">* Denotes Required Field</span>
            </li>
            <li>
                <label for="name">Name*:</label>
                <form:input type="text" path="name" placeholder="Alexander Sergeyevich Pushkin"></form:input>
                <form:errors path="name"></form:errors>
                    ${authorError}
            </li>
            <li>
                <label for="imageFileName">Image:</label>
                <form:textarea path="imageFileName" cols="10" rows="1" placeholder="Add url for photo"></form:textarea>
            </li>
            <li>
                <label for="biography"><span class="textForTable">Biography*:</span></label>
                <form:textarea onkeyup="symbols(this)" id="textarea" path="biography" cols="120" rows="10" placeholder="Aleksandr Pushkin, in full Aleksandr Sergeyevich Pushkin, (born May 26 [June 6, New Style], 1799, Moscow, Russia—died January 29 [February 10], 1837, St. Petersburg), Russian poet, novelist, dramatist, and short-story writer; he has often been considered his country’s greatest poet and the founder of modern Russian literature."></form:textarea>
                <div id="symbols" class="symbols"></div>
                <form:errors path="biography"></form:errors>
            </li>
            <li>
                <button class="submit" type="submit">Submit Form</button>
            </li>
        </ul>
    </center>
</form:form>
<blockquote>
    <p>The roots of education are bitter, but the fruit is sweet.</p>
    <footer>— <cite>Aristotle</cite></footer>
</blockquote>
</body>
</html>