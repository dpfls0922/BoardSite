<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file ="./common/header.jsp"%>

    <div class="container mt-4">
        <div class = "card card-login mx-auto mt-5" style="max-width: 400px;">
            <div class = "card-header">회원가입</div>
            <div class = "card-body">
                <form action="/user/signup" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="이름" name="username" maxlength="20" autofocus ="autofocus">
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" placeholder="이메일" name="email" maxlength="20">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="아이디" name="userid" maxlength="20">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20">
                    </div>
                    <input type="submit" class="btn btn-primary form-control" value="회원가입">
                </form>
            </div>
        </div>
    </div>

<%@ include file ="./common/footer.jsp"%>