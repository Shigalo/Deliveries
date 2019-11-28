<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Проиежуточные пункты</h3>
    <form action="" method="post">
       <%-- <input type="hidden" name="startPoint" value="startPoint" />
        <input type="hidden" name="endPoint" value="endPoint" />--%>
        <input type="hidden" name="nSubPoints" value="nSubPoints" />
        <%Integer n = Integer.valueOf(request.getParameter("nSubPoints"));%>
        <% for(int i = 0; i < n; i ++) { %>
            <div style="position:relative; padding: 5px"><label>Промежуточная точка <%=i+1 %>:<input type="text" name="subPoints"></label></div>
        <%}%>


        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Продолжить"/></div>
    </form>
</div>
<%@include file='parts/footer.jsp'%>
