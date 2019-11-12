<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Путь ${way.getId()}</h3>
    <div style="position:relative; padding: 5px"><label>Пункт отправления : ${way.getStartPoint().getName()}</label></div>
    <div style="position:relative; padding: 5px"><label>Пункт прибытия : ${way.getEndPoint().getName()}</label></div>
    <div style="position:relative; padding: 5px"><label>Общая длинна пути : ${sumLength}</label></div>
    <div style="position:relative; padding: 5px"><label>Время в пути : ${sumTime}</label></div>

    <%--<div style="position:relative; padding: 5px"><label>sssss : ${pointList}</label></div>--%>

    <table>
        <tr>
            <td><label>Пункт отправления</label></td>
            <td><label>Пункт прибытия</label></td>
            <td><label>Длинна пути</label></td>
            <td><label>Транспорт</label></td>
            <td><label>Скорость</label></td>
            <td><label>Время в пути</label></td>
            <td><label>Цена за кг.</label></td>

        </tr>
    <c:forEach items="${wayList}" var="way">
    <tr>
        <td><label>${way.startPoint.name}</label></td>
        <td><label>${way.endPoint.name}</label></td>
        <td><label>${way.length}</label></td>
        <td><label>${way.transport.name}</label></td>
        <td><label>${way.transport.speed}</label></td>
        <td><label>${way.length/way.transport.speed}</label></td>
        <td><label>${way.transport.unit_cost}</label></td>

    </tr>
    </c:forEach>
    </table>

   <%-- <c:forEach items="${pointList}" var="point"><label>${point.name}</label></c:forEach>
    <c:forEach items="${wayList}" var="way"><label>${way.id}</label></c:forEach>--%>
</div>
<%@include file='parts/footer.jsp'%>
