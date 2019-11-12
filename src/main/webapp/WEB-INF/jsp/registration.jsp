<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <form action="" method="post">
        <div style="position:relative; padding: 5px"><label>Имя : <input type="text" name="username"></label></div>
        <div style="position:relative; padding: 5px"><label>Пароль : <input type="password" name="password"></label></div>
        <div style="position:relative; padding: 5px"><label>Подтверждение : <input type="password" name="passwordConfirm"></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <span>${massage}</span>
        <div style="position:relative; padding: 5px"><input type="submit" value="Регистрация"/></div>
    </form>
</div>
<%@include file='parts/footer.jsp'%>
