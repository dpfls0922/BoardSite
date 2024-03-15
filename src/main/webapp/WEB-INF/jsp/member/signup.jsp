<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="com.Board.Board.Dto.MemberSignupDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    MemberSignupDto dto = (MemberSignupDto) request.getAttribute("dto");
    String valid_email = (String) request.getAttribute("valid_email");
    String valid_username = (String) request.getAttribute("valid_username");
    String valid_userid = (String) request.getAttribute("valid_userid");
    String valid_password1 = (String) request.getAttribute("valid_password1");
    String valid_password2 = (String) request.getAttribute("valid_password2");

    Logger logger = LoggerFactory.getLogger("signup.jsp");
    logger.info("valid_email in JSP: {}", valid_email);
    logger.info("valid_userid in JSP: {}", valid_username);
    logger.info("valid_password1 in JSP: {}", valid_password1);
    logger.info("valid_password2 in JSP: {}", valid_password2);
%>

<%@ include file ="../common/header.jsp"%>

    <div class="container mt-4">
        <div class = "card card-login mx-auto mt-5" style="max-width: 400px;">
            <div class = "card-header">회원가입</div>
            <div class = "card-body text-center">
                <% if (dto == null) { %>
                <form action="/user/signup" method="post">
                    <div class="form-group">
                        <div class="form-floating mb-3">
                            <input type="text" name="username" class="form-control" id="floatingUsername" autofocus="autofocus">
                            <label for="floatingInput">이름</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" name="email" class="form-control" id="floatingInput">
                            <label for="floatingInput">이메일</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" name="userid" class="form-control" id="floatingUsername">
                            <label for="floatingInput">아이디</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" name="password1" class="form-control" id="floatingPassword1">
                            <label for="floatingPassword1">비밀번호</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" name="password2" class="form-control" id="floatingPassword2">
                            <label for="floatingPassword2">비밀번호</label>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">회원가입</button>
                </form>
                <% }
                else { %>
                <form action="/user/signup" method="post">
                    <div class="form-group">
                        <% if (valid_username == null) { %>
                        <div class="form-floating mb-3">
                            <input type="text" name="username" class="form-control" value=<%= dto.getUsername() %> id="floatingUsername">
                            <label for="floatingInput">이름</label>
                        </div>
                        <% } else { %>
                        <div class="form-floating mb-3 has-danger">
                            <input type="text" name="username" class="form-control is-invalid" id="floatingUsername">
                            <label for="floatingInput">이름</label>
                            <div class="invalid-feedback"><%= valid_username %></div>
                        </div>
                        <% } %>
                        <% if (valid_email == null) { %>
                        <div class="form-floating mb-3">
                            <input type="email" name="email" class="form-control" value=<%= dto.getEmail() %> id="floatingInput">
                            <label for="floatingInput">이메일</label>
                        </div>
                        <% } else { %>
                        <div class="form-floating mb-3 has-danger">
                            <input type="email" name="email" class="form-control is-invalid" id="floatingInput">
                            <label for="floatingInput">이메일</label>
                            <div class="invalid-feedback"><%= valid_email %></div>
                        </div>
                        <% } %>
                        <% if (valid_userid == null) { %>
                        <div class="form-floating mb-3">
                            <input type="text" name="userid" class="form-control" value=<%= dto.getUserid() %> id="floatingUsername">
                            <label for="floatingInput">아이디</label>
                        </div>
                        <% } else { %>
                        <div class="form-floating mb-3 has-danger">
                            <input type="text" name="userid" class="form-control is-invalid" id="floatingUsername">
                            <label for="floatingInput">아이디</label>
                            <div class="invalid-feedback"><%= valid_userid %></div>
                        </div>
                        <% } %>
                        <% if (valid_password2 == null) { %>
                        <div class="form-floating mb-3">
                            <input type="password" name="password1" class="form-control" id="floatingPassword1">
                            <label for="floatingPassword1">비밀번호</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" name="password2" class="form-control" id="floatingPassword2">
                            <label for="floatingPassword2">비밀번호</label>
                        </div>
                        <% } else { %>
                        <div class="form-floating mb-3 has-danger">
                            <input type="password" name="password1" class="form-control is-invalid" id="floatingPassword1">
                            <label for="floatingPassword1">비밀번호</label>
                            <div class="invalid-feedback"><%= valid_password2 %></div>
                        </div>
                        <div class="form-floating mb-4 has-danger">
                            <input type="password" name="password2" class="form-control is-invalid" id="floatingPassword2">
                            <label for="floatingPassword2">비밀번호</label>
                            <div class="invalid-feedback"><%= valid_password2 %></div>
                        </div>
                        <% } %>
                    </div>
                    <button type="submit" class="btn btn-primary">회원가입</button>
                </form>
                <% } %>
            </div>
        </div>
    </div>

<%@ include file ="../common/footer.jsp"%>