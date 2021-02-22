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
    <script src="${contextPath}/resources/js/main.js"></script>
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
<form:form class="new_form" method="POST" modelAttribute="bookForm">
    <center>
        <ul>
            <li>
                <h2>Add new Book</h2>
                <p></p>
                <span class="required_notification">* Denotes Required Field</span>
            </li>
            <li>
                <label for="author" >Author*:</label>
                <form:select id="select-box1" class="select" path="author">
                    <c:forEach items="${allAuthors}" var="author">
                        <form:option value="${author.id}">${author.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="author"></form:errors>
                    ${authorError}
            </li>
            <li>
                <label for="name">Name*:</label>
                <form:input type="message" path="name" placeholder="Captain's daughter"></form:input>
                <form:errors path="name"></form:errors>
                    ${nameError}
            </li>
            <li>
                <label for="description">Description*:</label>
                <form:textarea path="description" cols="120" rows="10" placeholder="Alexander Pushkin's short novel is set during the reign of Catherine the Great, when the Cossacks rose up in rebellion against the Russian empress. Presented as the memoir of Pyotr Grinyov, a nobleman, The Captain's Daughter tells how, as a feckless youth and fledgling officer, Grinyov was sent from St."></form:textarea>
                <form:errors path="description"></form:errors>
                    ${descriptionError}
            </li>
            <li>
                <label for="demoVersion">Demo version*:</label>
                <form:textarea path="demoVersion" cols="100" rows="15" placeholder="My father, Andrey Petrovitch Grineff, who served in his youth under Count Münich, had retired with the rank of senior major, in the year 17—. He then settled on his property in the government of Simbirsk, where he married Avdotia Vassilievna U——, the daughter of a poor nobleman in the neighbourhood. Nine children were born to my parents. All my brothers and sisters died in their infancy. My name had been entered on the strength of the Semionoffsky regiment, thanks to Prince B——, a major in the Guards, and our near relative. I was checked as being on leave of absence until the completion of my studies. At that time the system of education was not what it is now. At the age of five I was turned over to the care of the groom Savelitch, whose sober character had earned for him the distinction of being constituted my governor. I managed, under his supervision, to learn to read and write in Russian by the time I was twelve years of age, and was also able to discuss in a creditable manner the merits of a sporting dog. At about that period my father, in writing to Moscow for his yearly supply of wines and salad oil, engaged a Frenchman, M. Beaupré, to be my tutor. Savelitch was much put out upon his arrival."></form:textarea>
                <form:errors path="demoVersion"></form:errors>
                    ${demoVersionError}
            </li>
            <li>
                <button class="submit" type="submit">Submit Form</button>
            </li>
        </ul>
    </center>
</form:form>
<blockquote>
    <p>The great aim of education is not knowledge but action.</p>
    <footer>— <cite>Herbert Spencer</cite></footer>
</blockquote>
</body>
</html>