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

<%@ include file ="../common/header.jsp"%>

    <div class="container mt-4">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6">
                <form action="/list/edit/<%=board.getId() %>" method="post">
                    <input type="hidden" name="_method" value="put" />
                    <input type="hidden" name="id" value="<%=board.getId() %>" />
                    <div class="form-group mb-3">
                        <label for="subject">제목</label>
                        <input type="text" class="form-control" id="subject" name="subject" value="<%=board.getSubject() %>">
                    </div>
                    <div class="form-group mb-3">
                        <label for="name"> 작성자 </label>
                        <input type="text" class="form-control" id="name" name="name" value="<%=board.getWriter() %>">
                    </div>
                    <div class="form-group mb-4">
                        <label for="content"> 카테고리 </label>
                        <ul class="list-group list-group-horizontal">
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="question" id="1" name="categories" <%= board.getSelectedCategories().contains("question") ? "checked" : "" %>>
                                <input type="hidden" name="_categories" value="on">
                                <label class="form-check-label" for="1">질문</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="general" id="2" name="categories" <%= board.getSelectedCategories().contains("general") ? "checked" : "" %>>
                                <label class="form-check-label" for="2">자유</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="worry" id="3" name="categories" <%= board.getSelectedCategories().contains("worry") ? "checked" : "" %>>
                                <label class="form-check-label" for="3">고민</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="hobby" id="4" name="categories" <%= board.getSelectedCategories().contains("hobby") ? "checked" : "" %>>
                                <label class="form-check-label" for="4">취미</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="travel" id="5" name="categories" <%= board.getSelectedCategories().contains("travel") ? "checked" : "" %>>
                                <label class="form-check-label" for="5">여행</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="study" id="6" name="categories" <%= board.getSelectedCategories().contains("study") ? "checked" : "" %>>
                                <label class="form-check-label" for="6">공부</label>
                            </li>
                        </ul>
                    </div>
                    <div class="form-group mb-4">
                        <label for="content"> 내용 </label>
                        <textarea class="form-control" id="content" name="content" rows="5"><%=board.getContent() %></textarea>
                    </div>
                    <div class="text-center mt-3">
                        <a href="/list/<%=board.getId() %>" role="button" class="btn btn-secondary">취소</a>
                        <input class="btn btn-primary" type="submit" role="button" value="수정">
                    </div>
                </form>
            </div>
        </div>
    </div>

<%@ include file ="../common/footer.jsp"%>