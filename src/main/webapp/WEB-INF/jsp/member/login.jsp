<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file ="../common/header.jsp"%>

    <div class="container mt-4">
        <div class="card card-login mx-auto mt-5" style="max-width: 400px;">
            <div class="card-header">로그인</div>
            <div class="card-body">
                <form action="/user/login" method="post">
                    <% if (request.getParameter("error") != null) { %>
                    <div class="alert alert-danger">
                        사용자ID 또는 비밀번호를 확인해 주세요.
                    </div>
                    <% } %>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="아이디" id="username" name="username" required="required" autofocus ="autofocus">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="비밀번호" id="password" name="password" required="required">
                    </div>
                    <input type="submit" class="btn btn-primary form-control" value="로그인">
                </form>
            </div>
        </div>
    </div>

<%@ include file ="../common/footer.jsp"%>