<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>
<c:if test="${isAdmin}">
    <a href="${pageContext.request.contextPath}/transport/addTransport">Добавить транспорт</a>
</c:if>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <c:if test="${empty transportList}">
        <h2>На данный момент Транспорт отсутствует</h2>
    </c:if>
    <c:if test="${!empty transportList}">
        <table>
            <caption>Список транспортных средств компании</caption>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Максимальная нагрузка</th>
                <th>Цена за кг.</th>
                <th>Скорость</th>
            </tr>

            <c:forEach items="${transportList}" var="transport">
                <tr id="${transport.id}">
                    <td>${transport.id}</td>
                    <td>${transport.name}</td>
                    <td>${transport.max_capacity}</td>
                    <td>${transport.unit_cost}</td>
                    <td>${transport.speed}</td>
                    <c:if test="${isAdmin}">
                        <td>
                            <form action="/transport/transportEdit/${transport.id}" method="get">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="submit" value="Редактировать">
                            </form>
                        </td>
                        <td><a href="<c:url value='/transport/transportRemove/${transport.id}'/>">Удалить</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@include file='parts/footer.jsp'%>
