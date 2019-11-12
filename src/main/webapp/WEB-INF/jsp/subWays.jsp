<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Проиежуточные отрезки</h3>
    <form action="" method="post">
        <%String[] subPoints = request.getParameter("subPoints").split(","); int n = subPoints.length;%>
        <table>
            <tr>
                <td>Промежуток</td>
                <td>Начало пути</td>
                <td>Конец пути</td>
                <td>Длинна</td>
                <td>Транспорт</td>
            </tr>
            <% for(int i = 1; i < n; i ++) { %>
            <tr>
                <td><label><%=i%></label></td>
                <td><label><%=subPoints[i-1]%></label></td>
                <td><label><%=subPoints[i]%></label></td>
                <td><input type="text" name="length"></td>
                <td>
                    <select name="transport">
                        <c:forEach items="${transportList}" var="transport">
                        <option value="${transport.name}">${transport.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <%}%>
        </table>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Продолжить"/></div>
    </form>
</div>
<%@include file='parts/footer.jsp'%>
