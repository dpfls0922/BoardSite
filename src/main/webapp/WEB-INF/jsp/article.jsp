<%@ page contentType="text/html; charset=UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
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

<div>
    <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/list">게 시 판</a>
        </div>
    </nav>

    <div>
        </span> 작성자 : <span><i>${BoardEntity.user.username}</i></span>
    </div>
    <div>
        <dl>
            <dt>등록일 : ${BoardEntity.createdDate}</dt>
            <dt>조회수 : ${BoardEntity.hitCount}</dt>
        </dl>
    </div>
    <div>
        <h3>${BoardEntity.subject}</h3>
    </div>
    </hr>
    <div>
        <h3>${BoardEntity.content}</h3>
    </div>
    </hr>
</div>

<div>
    <div>
        <input class="btn btn-secondary" type="button" role="button" value="삭제"/>
        <input class="btn btn-primary" type="button" role="button" value="수정"/>
    </div>
</div>

</body>
</html>