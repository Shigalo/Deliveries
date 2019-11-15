<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Авторизация</h3>
    <form action="" method="post">

        <div style="position:relative; padding: 5px"><label>Вес груза : <input type="number" step="0.01" name="weight" id="weight"></label></div>
        <script>
            var input = document.getElementById('weight');
            input.oninput = function() {
                var n = input.value;
                var ways = document.getElementsByName('w');

                for (i = 0; i < ways.length; i++) {
                    // alert(ways[i].id)
                    ways[i].innerHTML = ways[i].id * n;
                }
            };
        </script>
        <label id="result"></label>

        <table>
            <caption>Список путей перевозки</caption>
            <tr>
                <th>ID</th>
                <th>Длинна</th>
                <th>Пункт отправки</th>
                <th>Пункт прибытия</th>
                <th>Длинна пути</th>
                <th>Врем в пути</th>
                <th>Цена перевозки</th>
            </tr>
            <c:forEach items="${wayList}" var="way">
                <tr id="${way.id}">
                    <td>${way.id}</td>
                    <td>${way.length}</td>
                    <td>${way.startPoint.name}</td>
                    <td>${way.endPoint.name}</td>
                    <td>${wayService.getLength(way)}</td>
                    <td>${wayService.getTime(way)}</td>
                    <td><label name="w" id = "${wayService.getCost(way)}">${wayService.getCost(way)}</label></td>
                </tr>
            </c:forEach>
        </table>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Добавить"/></div>
    </form>
</div>
<%@include file='parts/footer.jsp'%>
