<%@ page import="java.util.List" %>
<%@ page import="com.Board.Board.Domain.Entity.Board" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    // 로깅 확인
    Logger logger = LoggerFactory.getLogger("list.jsp");
    List<Board> boards = (List<Board>) request.getAttribute("boards");
    logger.info("Boards received in JSP: {}", boards);

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
                    <a class="nav-link" href="/user/signup">회원가입</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="검색어를 입력해주세요" aria-label="Search">
                <button class="btn btn-outline-primary" type="submit" >Search</button>
            </form>
        </div>
    </div>
    <div class="row justify-content-center align-items-center align-items-center mt-4">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
            </thead>
            <tbody>
                <%for(Board board : boards) { %>
                    <tr>
                        <td><%=board.getNum() %></td>
                        <td>
                            <a href="/list/<%=board.getNum()%>"><%=board.getSubject() %></a>
                        </td>
                        <td><%=board.getName() %></td>
                        <td>
                            <%=board.getCreatedDate().format(formatter)%>
                        </td>
                        <td><%=board.getHitCount() %></td>
                    </tr>
                <%} %>
            </tbody>
        </table>
    </div>
        <div class="text-right">
            <a href="/register" role="button" class="btn btn-primary">글쓰기</a>
        </div>
        <div class="text-center">
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href="#">이전</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">4</a></li>
                <li class="page-item"><a class="page-link" href="#">5</a></li>
                <li class="page-item"><a class="page-link" href="#">다음</a></li>
            </ul>
        </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
<footer class=" container d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top"></footer>
</html>