<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">

<div style="text-align: left; position: relative; top: 18%; left: -14%; ">
    <h3>Компания располагает:</h3>
    <p>пользователи (убрать): ${userList}</p>
    <p>кол-во путей (убрать): ${wayList}</p>
    <p>Разнообразие транспортных средств (изменить): ${transportList} ед.</p>
    <p>Количество транспортных средств: ${transportList} ед.</p>
    <p>Точек снабжения: ${pointList}</p>
</div>
<%@include file='parts/footer.jsp'%>
