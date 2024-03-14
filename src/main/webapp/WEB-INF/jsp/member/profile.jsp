<%@ page import="com.Board.Board.Dto.MemberDto" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    MemberDto member = (MemberDto) request.getAttribute("member");
%>

<%@ include file ="../common/header.jsp"%>

<div class="container mt-4">
    <div class = "card card-login mx-auto mt-5" style="max-width: 400px;">
        <div class = "card-header text-center"><%= member.getUserid() %>님 정보</div>
            <div class="card-body  text-center">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <p>이름 : <%= member.getUsername() %></p>
                        <p>이메일 : <%= member.getEmail() %></p>

                        <div class="row justify-content-center mt-5">
                            <div class="col">
                                <a href="/user/update" class="btn btn-primary form-control">수정</a>
                            </div>
                            <div class="col">
                                <a href="/user/withdrawal" class="btn btn-secondary form-control">탈퇴</a>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

<%@ include file ="../common/footer.jsp"%>