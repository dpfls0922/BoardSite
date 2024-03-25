<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file ="../common/header.jsp"%>

<style>
    body {
        background-color: #f8f9fa;
    }
    .container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .error-card {
        max-width: 400px;
        padding: 20px;
        border-radius: 10px;
        background-color: #ffffff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .error-title {
        font-size: 40px;
        color: black;
        text-align: center;
        margin-bottom: 20px;
    }
    .error-message {
        font-size: 18px;
        color: #6c757d;
        text-align: center;
        margin-bottom: 30px;
    }
    .back-home-btn {
        display: block;
        width: 100%;
        padding: 10px 0;
        text-align: center;
        background-color: #007bff;
        color: #ffffff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }
</style>

<div class="container">
    <div class="error-card">
        <h1 class="error-title">403</h1>
        <p class="error-message">죄송합니다. 해당 페이지에 액세스할 권한이 없습니다.</p>
        <a href="/" class="back-home-btn">홈으로 돌아가기</a>
    </div>
</div>

<%@ include file ="../common/footer.jsp"%>