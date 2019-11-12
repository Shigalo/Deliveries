<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Авторизация</h3>
    <form action="/login" method="post">
        <div style="position:relative; padding: 5px"><label>Логин : <input type="text" name="username"></label></div>
        <div style="position:relative; padding: 5px;"><label>Пароль : <input type="password" name="password"></label></div>
        <span>${error}</span>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Войти"/></div>
    </form>
    <a href="${pageContext.request.contextPath}/registration">Регистрация</a>
</div>
<%@include file='parts/footer.jsp'%>
