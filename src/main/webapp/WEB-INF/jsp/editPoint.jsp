<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Авторизация</h3>
    <form action="/points/pointEdit/${id}" method="post">
        <label>ID: ${id}</label>
        <div style="position:relative; padding: 5px"><label>Название : <input type="text" name="pointName" value="${name}"></label></div>
        <span>${error}</span>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Сохранить"/></div>
    </form>
</div>
<%@include file='parts/footer.jsp'%>
