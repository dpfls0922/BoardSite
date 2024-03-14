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
                        <label for="name"> 작성자 </label>
                        <input type="text" class="form-control" id="name" name="name" value="<%=name%>" readonly>
                    </div>
                    <div class="form-group mb-3">
                        <label for="subject">제목</label>
                        <input type="text" class="form-control" id="subject" name="subject" placeholder="제목을 입력하세요">
                    </div>
                    <div class="form-group mb-4">
                        <label for="content"> 내용 </label>
                        <textarea class="form-control" id="content" name="content" placeholder="내용을 입력하세요" rows="5"></textarea>
                    </div>
                    <div class="text-center mt-3">
                        <a href="/list" role="button" class="btn btn-secondary">취소</a>
                        <input class="btn btn-primary" type="submit"  role="button" value="등록">
                    </div>
                </form>
            </div>
        </div>
    </div>

<%@ include file ="../common/footer.jsp"%>