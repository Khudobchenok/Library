<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Данные пользователей</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
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
    <div class="post-title">
        <h2>
            Users table
        </h2>
    </div>
    <table class="table_blur">
        <tr>
            <th>ID</th>
            <th>UserName</th>
            <th>Password</th>
            <th>Roles</th>
            <th>Delete</th>
            <th>SetRoles</th>
        </tr>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">${role.name}; </c:forEach>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/adminDeleteUser" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/addAdmin" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action" value="newAdmin"/>
                        <button type="submit">Make admin</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="post-title">
        <h2>
            Authors table
        </h2>
        <table class="table_blur">
            <tr>
                <th>ID</th>
                <th>Photo</th>
                <th>Name</th>
                <th>Delete</th>
            </tr>
            <form:errors></form:errors>
            <div class="blue">
            ${adminError}
            </div>
            <c:forEach items="${allAuthors}" var="author">
            <tr>
                <td>${author.id}</td>
                <td>${author.id}</td>
                <td>${author.name}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/adminDeleteAuthor" method="post">
                        <input type="hidden" name="authorId" value="${author.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
        <h2>
            Books table
        </h2>
            <table class="table_blur">
                <tr>
                    <th>ID</th>
                    <th>Photo</th>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${allBooks}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.author.name}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/adminDeleteBook" method="post">
                            <input type="hidden" name="bookId" value="${book.id}"/>
                            <input type="hidden" name="action" value="delete"/>
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </table>
    </div>
</center>
</body>
</html>