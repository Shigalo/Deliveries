<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Авторизация</h3>
    <form action="/transport/transportEdit/${id}" method="post">
        <label>ID: ${id}</label>
        <div style="position:relative; padding: 5px"><label>Название : <input type="text" name="transportName" value="${name}"></label></div>
        <div style="position:relative; padding: 5px"><label>Максимальная нагрузка : <input type="text" name="max_capacity" value="${max_capacity}"></label></div>
        <div style="position:relative; padding: 5px"><label>Цена за кг. : <input type="text" name="unit_cost" value="${unit_cost}"></label></div>
        <div style="position:relative; padding: 5px"><label>Скорость : <input type="text" name="speed" value="${speed}"></label></div>
        <span>${error}</span>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Сохранить"/></div>
    </form>
</div>
<%@include file='parts/footer.jsp'%>
