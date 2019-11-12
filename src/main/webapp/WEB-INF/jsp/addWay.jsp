<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Авторизация</h3>
    <form action="" method="post">
        <div style="position:relative; padding: 5px"><label>Пункт отправки :<select name="startPoint"><c:forEach items="${pointList}" var="point"><option>${point.name}</option></c:forEach></select></label></div>
        <div style="position:relative; padding: 5px"><label>Конечный пункт :<select name="endPoint"><c:forEach items="${pointList}" var="point"><option>${point.name}</option></c:forEach></select></label></div>
        <div style="position:relative; padding: 5px"><label>Количество промежуточных пунктов : <input type="text" name="nSubPoints"></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Добавить"/></div>
    </form>
</div>
<%@include file='parts/footer.jsp'%>
