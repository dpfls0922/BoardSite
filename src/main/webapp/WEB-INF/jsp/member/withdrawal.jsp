<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding("UTF-8");
    Object wrongPassword = request.getAttribute("wrongPassword");
%>
<%@ include file ="../common/header.jsp"%>

<div class="container">
    <form method="post">
        <label class="form-label mt-4">회원 탈퇴</label>
        <% if (wrongPassword == null) { %>
        <div class="form-floating mb-3">
            <input type="password" name="password" class="form-control" id="password" required>
            <label for="password">비밀번호</label>
        </div>
        <% } else { %>
        <div class="form-floating mb-3 has-danger">
            <input type="password" name="password" class="form-control is-invalid" id="password" required>
            <label for="password">비밀번호</label>
            <div class="invalid-feedback"><%= wrongPassword %></div>
        </div>
        <% } %>
        <button type="submit" class="btn btn-primary">회원 탈퇴</button>
    </form>
</div>

<%@ include file ="../common/footer.jsp"%>