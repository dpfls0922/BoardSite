<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="com.Board.Board.Dto.BoardDto" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    Logger logger = LoggerFactory.getLogger("article.jsp");
    BoardDto board = (BoardDto) request.getAttribute("board");
    logger.info("Board received in JSP: {}", board);
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

    <div class="container mt-4">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6">
                <form action="/list/edit/<%=board.getNum() %>" method="post">
                    <input type="hidden" name="_method" value="put" />
                    <input type="hidden" name="id" value="<%=board.getNum() %>" />
                    <div class="form-group mb-3">
                        <label for="subject">제목</label>
                        <input type="text" class="form-control" id="subject" name="subject" value="<%=board.getSubject() %>">
                    </div>
                    <div class="form-group mb-3">
                        <label for="name"> 작성자 </label>
                        <input type="text" class="form-control" id="name" name="name" value="<%=board.getName() %>">
                    </div>
                    <div class="form-group mb-4">
                        <label for="content"> 내용 </label>
                        <textarea class="form-control" id="content" name="content" rows="5"><%=board.getContent() %></textarea>
                    </div>
                    <div class="text-center mt-3">
                        <a href="/list/<%=board.getNum() %>" role="button" class="btn btn-secondary">취소</a>
                        <input class="btn btn-primary" type="submit" role="button" value="수정">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
