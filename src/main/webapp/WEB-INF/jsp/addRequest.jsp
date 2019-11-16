<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Авторизация</h3>
    <form method="post">

        <div style="position:relative; padding: 5px"><label>Вес груза : <input type="number" step="0.01" id="weight" name="weight"></label></div>
        <input type="radio" name="type" id="normal" checked value="normal"><label for="normal">Обычная перевозка</label><br>
        <input type="radio" name="type" id="dangerous" value="dangerous"><label for="dangerous">Опасный груз</label><br>
        <input type="radio" name="type" id="fragile" value="fragile"><label for="fragile">Хрупкий груз</label><br>
        <input type="radio" name="type" id="perishable" value="perishable"><label for="perishable">Скоропортящийся груз</label>
        <script>
            function f() {
                var n = input.value;
                var ways = document.getElementsByName('w');
                for (i = 0; i < ways.length; i++) {
                    id = ways[i].parentElement.id;
                    cost = n;
                    if(document.getElementById("dangerous").checked) {
                        cost *= document.getElementById(id+"dangerous").textContent}
                    if(document.getElementById("fragile").checked) {
                        cost *= document.getElementById(id + "fragile").textContent}
                    if(document.getElementById("perishable").checked) {
                        cost *= document.getElementById(id + "perishable").textContent}
                    if(document.getElementById("normal").checked) {
                        cost *= ways[i].id}
                    ways[i].childNodes[1].innerHTML = cost;
                }
            }
            input = document.getElementById('weight');
            input.oninput = function() { f() };
            document.getElementById('dangerous').onclick = function() { f() };
            document.getElementById('fragile').onclick = function() { f() };
            document.getElementById('perishable').onclick = function() { f() };
            document.getElementById('normal').onclick = function() { f() };
        </script>
        <table border="1">
            <caption>Список путей перевозки</caption>
            <tr>
                <th>ID</th>
                <th>Пункт отправки</th>
                <th>Пункт прибытия</th>
                <th>Длинна пути</th>
                <th>Врем в пути</th>
                <th>Цена перевозки</th>
            </tr>
            <c:forEach items="${wayList}" var="way">
                <tr id="${way.id}">
                    <td>${way.id}</td>
                    <td>${way.startPoint.name}</td>
                    <td>${way.endPoint.name}</td>
                    <td id="${way.id}length">${wayService.getLength(way)}</td>
                    <td>${wayService.getTime(way)}</td>
                    <td name="w" id="${wayService.getCost(way)}">
                        <label id="${way.id}cost" name="cost">0</label>
                    </td>
                    <td hidden id="${way.id}dangerous">
                            ${wayService.getDangerous(way)}
                    </td>
                    <td hidden id="${way.id}fragile">
                            ${wayService.getFragile(way)}
                    </td>
                    <td hidden id="${way.id}perishable">
                            ${wayService.getPerishable(way)}
                    </td>
                    <td><input formaction="/requests/addRequest?id=${way.id}" type="submit" value="Выбрать"></td>
                </tr>
            </c:forEach>
        </table>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</div>
<%@include file='parts/footer.jsp'%>
