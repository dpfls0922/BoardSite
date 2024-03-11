<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>게시판</title>
    <%--bootstrap css--%>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/list">게 시 판</a>
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" id="dropdownBtn">
                dropdown button
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/user/login">로그인</a>
                <a class="dropdown-item" href="/user/signup">회원가입</a>
            </div>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <div class = "card card-login mx-auto mt-5" style="max-width: 400px;">
        <div class = "card-header">회원가입</div>
        <div class = "card-body">
            <form action="/user/login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="아이디" name="userID" required="required" autofocus ="autofocus">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="비밀번호" name="userPassword" required="required">
                </div>
                <input type="submit" class="btn btn-primary form-control" value="로그인">
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>