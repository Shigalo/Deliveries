<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Авторизация</h3>
    <form action="" method="post">
        <div style="position:relative; padding: 5px"><label>Название : <input type="text" name="transportName"></label></div>
        <div style="position:relative; padding: 5px"><label>Максимальная нагрузка : <input type="text" name="max_capacity"></label></div>
        <div style="position:relative; padding: 5px"><label>Цена за кг. : <input type="text" name="unit_cost"></label></div>
        <div style="position:relative; padding: 5px"><label>Скорость : <input type="text" name="speed"></label></div>
        <span>${error}</span>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Добавить"/></div>
    </form>
</div>
<%@include file='parts/footer.jsp'%>
