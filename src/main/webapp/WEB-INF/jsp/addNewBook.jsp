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
    <script type="text/javascript" src="${contextPath}/resources/js/main.js"></script>
    <script language="javascript">
        function symbols()
        {
            var symbols = textarea.value.length;
            if (symbols <= 1500) {
            document.getElementById('symbols').innerHTML =
                'Entered <span class="red">' + symbols + '</span> symbols out of 1500';
            }
            else document.getElementById('symbols').innerHTML =
                'You entered a lot of symbols (out of 1500)';
        }
        function symbols2()
        {
            var symbols2 = textarea2.value.length;
            if (symbols2 <= 1500) {
                document.getElementById('symbols2').innerHTML =
                    'Entered <span class="red">' + symbols2 + '</span> symbols out of 1500';
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
<form:form class="new_form" method="POST" modelAttribute="bookForm">
    <center>
        <ul>
            <li>
                <h2>Add new Book</h2>
                <p></p>
                <span class="required_notification">* Denotes Required Field</span>
            </li>
            <li>
                <label for="author">Author*:</label>
                <div class="dropdown">
                <form:select path="author" class="dropdown-select">
                    <c:forEach items="${allAuthors}" var="author">
                        <form:option value="${author.id}">${author.name}</form:option>
                    </c:forEach>
                </form:select>
                </div>
                <form:errors path="author"></form:errors>
            </li>
            <li>
                <label for="name">Name*:</label>
                <form:input type="message" path="name" placeholder="Captain's daughter" ></form:input>
                <form:errors path="name"></form:errors>
                    ${error}
            </li>
            <li>
                <label for="imageFileName">Image:</label>
                <form:textarea path="imageFileName" cols="10" rows="1" placeholder="Add url for photo"></form:textarea>
            </li>
            <li>
                <label for="description"><span class="textForTable">Description*:</span></label>
                <form:textarea onkeyup="symbols(this)" id="textarea" path="description" cols="120" rows="10" placeholder="Alexander Pushkin's short novel is set during the reign of Catherine the Great, when the Cossacks rose up in rebellion against the Russian empress. Presented as the memoir of Pyotr Grinyov, a nobleman, The Captain's Daughter tells how, as a feckless youth and fledgling officer, Grinyov was sent from St."></form:textarea>
                <div id="symbols" class="symbols"></div>
                <form:errors path="description"></form:errors>
            </li>
            <li>
                <label for="demoVersion"><span class="textForTable">Demo version*:</span></label>
                <form:textarea onkeyup="symbols2(this)" id="textarea2" path="demoVersion" cols="10" rows="15" placeholder="My father, Andrey Petrovitch Grineff, who served in his youth under Count Münich, had retired with the rank of senior major, in the year 17—. He then settled on his property in the government of Simbirsk, where he married Avdotia Vassilievna U——, the daughter of a poor nobleman in the neighbourhood. Nine children were born to my parents. All my brothers and sisters died in their infancy. My name had been entered on the strength of the Semionoffsky regiment, thanks to Prince B——, a major in the Guards, and our near relative. I was checked as being on leave of absence until the completion of my studies. At that time the system of education was not what it is now. At the age of five I was turned over to the care of the groom Savelitch, whose sober character had earned for him the distinction of being constituted my governor. I managed, under his supervision, to learn to read and write in Russian by the time I was twelve years of age, and was also able to discuss in a creditable manner the merits of a sporting dog. At about that period my father, in writing to Moscow for his yearly supply of wines and salad oil, engaged a Frenchman, M. Beaupré, to be my tutor. Savelitch was much put out upon his arrival."></form:textarea>
                <div id="symbols2" class="symbols"></div>
                <form:errors path="demoVersion"></form:errors>
            </li>
            <li>
                <button class="submit" type="submit">Submit Form</button>
            </li>
        </ul>
    </center>
</form:form>
</body>
</html>