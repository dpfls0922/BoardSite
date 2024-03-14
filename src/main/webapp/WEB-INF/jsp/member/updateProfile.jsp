<%@ page import="com.Board.Board.Dto.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    MemberDto member = (MemberDto) request.getAttribute("member");
%>
<%@ include file ="../common/header.jsp"%>

    <div class="container mt-4">
        <div class = "card card-login mx-auto mt-5" style="max-width: 400px;">
            <div class = "card-header">프로필 수정</div>
            <div class = "card-body text-center">
                <form action="/user/update" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" id="userid" name="userid" value="<%= member.getUserid() %>" readonly>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="email" name="email" value="<%= member.getEmail() %>">
                        <label for="email">이메일</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="username" name="username" value="<%= member.getUsername() %>" maxlength="20">
                        <label for="username">이름</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="password" name="password" maxlength="20">
                        <label for="password">비밀번호</label>
                    </div>
                    <input type="submit" class="btn btn-primary" value="수정">
                </form>
            </div>
    </div>
</div>

<%@ include file ="../common/footer.jsp"%>