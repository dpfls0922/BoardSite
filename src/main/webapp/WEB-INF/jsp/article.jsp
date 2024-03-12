<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="com.Board.Board.Dto.BoardDto" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    Logger logger = LoggerFactory.getLogger("article.jsp");
    BoardDto board = (BoardDto) request.getAttribute("board");
    logger.info("Board received in JSP: {}", board);
    logger.info("Board received in JSP: {}", board.getHitCount());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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

    <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/list">게 시 판</a>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><%=board.getName() %></h5>

                <div class="d-flex justify-content-between">
                    <p class="card-text text-muted"> <%=board.getCreatedDate().format(formatter)%></p>
                    <span class="text-muted">조회수 <%=board.getHitCount()%></span>
                </div>

                <br/>
                <h5 class="card-title"><%=board.getSubject() %></h5>
                <hr/>
                <h4 class="card-text"><%=board.getContent() %></h4>
                <br/><br/>

                </div>
            </div>

            <div class="row mt-3">
                <div class="col-auto mr-auto"></div>
                <div class="col-auto">
                    <a class="btn btn-primary" href="/list/edit/<%=board.getNum() %>" role="button">수정</a>
                </div>
                <div class="col-auto">
                    <form id="delete-form" href="/list/<%=board.getNum() %>" method="post">
                        <input type="hidden" name="_method" value="delete" />
                        <button id="delete-btn" type="submit" class="btn btn-danger">삭제</button>
                    </form>
                </div>
            </div>
    </div>


</body>
</html>