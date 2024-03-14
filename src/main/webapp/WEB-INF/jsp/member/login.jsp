<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file ="../common/header.jsp"%>

<div class="container mt-4">
    <div class="card card-login mx-auto mt-5" style="max-width: 400px;">
        <div class="card-header">로그인</div>
        <div class="card-body text-center">
            <form action="/user/login" method="post">
                <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-danger">
                    사용자ID 또는 비밀번호를 확인해 주세요.
                </div>
                <% } %>
                <div class="form-group">
                    <div class="form-floating mb-3">
                        <input type="text" name="username" class="form-control" id="floatingUsername" autofocus="autofocus">
                        <label for="floatingUsername">아이디</label>
                    </div>
                    <div class="form-floating mb-4">
                        <input type="password" name="password" class="form-control" id="floatingPassword">
                        <label for="floatingPassword">비밀번호</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">로그인</button>
            </form>
        </div>
    </div>
</div>

<%@ include file ="../common/footer.jsp"%>