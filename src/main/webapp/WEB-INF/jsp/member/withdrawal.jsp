<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding("UTF-8");
    Object wrongPassword = request.getAttribute("wrongPassword");
%>
<%@ include file ="../common/header.jsp"%>

<div class="container mt-4">
    <div class="card card-login mx-auto mt-5" style="max-width: 400px;">
        <div class="card-header">회원 탈퇴</div>
        <div class="card-body text-center">
            <form method="post">
                <% if (wrongPassword == null) { %>
                <div class="form-floating mb-3">
                    <input type="password" name="password" class="form-control" id="password" required="required" autofocus="autofocus">
                    <label for="password">비밀번호</label>
                </div>
                <% } else { %>
                <div class="form-floating mb-3 has-danger">
                    <input type="password" name="password" class="form-control is-invalid" id="password" required>
                    <label for="password">비밀번호</label>
                    <div class="invalid-feedback"><%= wrongPassword %></div>
                </div>
                <% } %>
                <button type="submit" class="btn btn-primary" onclick="removeMember();">회원 탈퇴</button>
            </form>
        </div>
    </div>
</div>

<script>
    function removeMember() {
        if(window.confirm("정말 탈퇴하시겠습니까?")){
            location.href="/user/withdrawal";
        }
    }
</script>

<%@ include file ="../common/footer.jsp"%>