<%@ page import="java.util.List" %>
<%@ page import="com.Board.Board.Domain.Entity.Board" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.Board.Board.Domain.Entity.BoardCategory" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
    request.setCharacterEncoding("UTF-8");
    List<Board> boards = (List<Board>) request.getAttribute("boards");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
%>

<%@ include file ="../common/header.jsp"%>

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
                    <th style="padding-left: 40px;">카테고리</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                </tr>
                </thead>
                <tbody>
                    <% int index = boards.size(); %>
                    <%for(Board board : boards) { %>
                        <tr>
                            <td><%= index-- %></td>
                            <td>
                                <a href="/list/<%=board.getId()%>"><%=board.getSubject() %></a>
                            </td>
                            <td>
                                <ul>
                                    <% for (BoardCategory category : board.getBoardCategories()) { %>
                                    <span class="badge rounded-pill bg-light text-dark"><%= category.getCategory().getDescription() %></span>
                                    <% } %>
                                </ul>
                            </td>
                            <% if(board.getWriter() != null && board.getWriter().equals("(탈퇴한 회원)")) { %>
                            <td style="color: grey;"><%= board.getWriter() %></td>
                            <% } else { %>
                            <td><%= board.getWriter() %></td>
                            <% } %>
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

<%@ include file ="../common/footer.jsp"%>