<%@ page contentType="text/html; charset=UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    String name = (String) request.getAttribute("userid");
%>

<%@ include file ="../common/header.jsp"%>

    <div class="container mt-4">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6">
                <form action="/register" method="post">
                    <div class="form-group mb-3">
                        <label for="writer"> 작성자 </label>
                        <input type="text" class="form-control" id="writer" name="writer" value="<%=name%>" readonly>
                    </div>
                    <div class="form-group mb-3">
                        <label for="subject">제목</label>
                        <input type="text" class="form-control" id="subject" name="subject" placeholder="제목을 입력하세요">
                    </div>
                    <div class="form-group mb-4">
                        <label for="content"> 카테고리 </label>
                        <ul class="list-group list-group-horizontal">
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="question" id="1" name="categories">
                                <input type="hidden" name="_categories" value="on">
                                <label class="form-check-label" for="1">질문</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="general" id="2" name="categories">
                                <label class="form-check-label" for="2">자유</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="worry" id="3" name="categories">
                                <label class="form-check-label" for="3">고민</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="hobby" id="4" name="categories">
                                <label class="form-check-label" for="4">취미</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="travel" id="5" name="categories">
                                <label class="form-check-label" for="5">여행</label>
                            </li>
                            <li class="list-group-item flex-grow-1">
                                <input class="form-check-input me-1" type="checkbox" value="study" id="6" name="categories">
                                <label class="form-check-label" for="6">공부</label>
                            </li>
                        </ul>
                    </div>
                    <div class="form-group mb-4">
                        <label for="content"> 내용 </label>
                        <textarea class="form-control" id="content" name="content" placeholder="내용을 입력하세요" rows="5"></textarea>
                    </div>
                    <div class="text-center mt-3">
                        <a href="/list" role="button" class="btn btn-secondary">취소</a>
                        <input class="btn btn-primary" type="submit" role="button" value="등록">
                    </div>
                </form>
            </div>
        </div>
    </div>

<%@ include file ="../common/footer.jsp"%>