<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>게시판</title>
    <%--bootstrap css--%>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/list">게 시 판</a>
            <div class="ms-auto">
                <ul class="navbar-nav d-flex flex-row">
                    <li class="nav-item mr-2">
                        <% if (request.getUserPrincipal() == null) { %>
                        <a class="nav-link" href="/user/login">로그인</a>
                        <% } else { %>
                        <a class="nav-link" href="/user/logout">로그아웃</a>
                        <% } %>
                    </li>
                    <li class="nav-item">
                    <li class="nav-item mr-2">
                        <% if (request.getUserPrincipal() == null) { %>
                        <a class="nav-link" href="/user/signup">회원가입</a>
                        <% } else { %>
                        <a class="nav-link" href="/user/profile">마이페이지</a>
                        <% } %>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</head>
<body>