<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>
<c:if test="${isAdmin}">
    <a href="/ways/addWay">Добавить путь</a>
</c:if>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <c:if test="${empty wayList}">
        <h2>На данный момент путей перевозок нет</h2>
    </c:if>
    <c:if test="${!empty wayList}">
        <table>
            <caption>Список путей перевозки</caption>
            <tr>
                <th>ID</th>
                <th>Длинна</th>
                <th>Пункт отправки</th>
                <th>Пункт прибытия</th>
            </tr>

            <c:forEach items="${wayList}" var="way">
                <tr id="${way.id}">
                    <td>${way.id}</td>
                    <td>${way.length}</td>
                    <td>${way.startPoint.name}</td>
                    <td>${way.endPoint.name}</td>
                    <td>
                        <form action="/ways/wayView/${way.id}" method="get">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="submit" value="Подробнее">
                        </form>
                    </td>
                        <%--<td>${way}</td>--%>
                        <%--<td>
                            <form action="/ways/wayEdit/${way.id}" method="get">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="submit" value="Редактировать">
                            </form>
                        </td>--%>
                    <c:if test="${isAdmin}">
                        <td><a href="<c:url value='/ways/wayRemove/${way.id}'/>">Удалить</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <%@include file='parts/footer.jsp'%>
</div>
