<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<c:if test="${isLogin}">
    <a href="${pageContext.request.contextPath}/requests/addRequest">Подать новую заявку</a>
</c:if>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <c:if test="${empty requestList}">
        <h2>На данный момент вы не оставляли заявок</h2>
    </c:if>
    <c:if test="${!empty requestList}">
        <table border="1">
            <caption>Список заявок пользователя</caption>
            <tr>
                <th>ID пути</th>
                <th>Вес груза</th>
                <th>Цена поставки</th>
            </tr>

            <c:forEach items="${requestList}" var="request">
                <tr id="${request.id}">
                    <td>${request.way.id}</td>
                    <td>${request.weight}</td>
                    <td>${request.cost}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@include file='parts/footer.jsp'%>
