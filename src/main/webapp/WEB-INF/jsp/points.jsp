<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>
<c:if test="${isAdmin}">
    <a href="${pageContext.request.contextPath}/points/addPoint">Добавить точку</a>
</c:if>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <c:if test="${empty pointList}">
        <h2>На данный момент точек снабжения нет</h2>
    </c:if>
    <c:if test="${!empty pointList}">
        <table>
            <caption>Список точек снабжения</caption>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
            </tr>

            <c:forEach items="${pointList}" var="point">
                <tr id="${point.id}">
                    <td>${point.id}</td>
                    <td>${point.name}</td>
                    <c:if test="${isAdmin}">
                        <td>
                            <form action="/points/pointEdit/${point.id}" method="get">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="submit" value="Редактировать">
                            </form>
                        </td>
                        <td><a href="<c:url value='/points/pointRemove/${point.id}'/>">Удалить</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>




<%@include file='parts/footer.jsp'%>
