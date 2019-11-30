<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>
<script>
    function optimum(array) {
        var wei = document.getElementById("wei");
        var res = [];
        var sum = 0;
        for(var i = 0; i < array.length; i++) {
            <c:forEach items="${requests}" var="request">
            if(${request.id} == array[i]) {
                if(sum + ${request.weight} <= wei.innerHTML) {
                    sum += ${request.weight};
                    res.push(array[i]);
                }
            }
            </c:forEach>
        }
        return res.toString();
    }

    function sort(rows) {
        rows.sort(function (a, b) { return b.children[5].innerHTML/b.children[4].innerHTML - a.children[5].innerHTML/a.children[4].innerHTML });
        var arr = [];
        for(var i = 0; i < rows.length; i++) {
            arr.push(rows[i].children[0].innerHTML);
        }
        var res = document.getElementById("res");
        res.hidden = false;
        res.innerHTML = "Приоритет заявок: " + arr.toString() + "<br/>" + "Рекомендуемые заявки:";
        document.getElementById("opt").innerHTML = optimum(arr);

    }
    function x(id) {
        var collection = [];
        var table = document.getElementById("requestsTable");
        table.hidden = false;
        var tableBody = table.children[1];
        for (var i=1; row = tableBody.children[i]; i++) {
            if (row.children[1].innerHTML == id) {
                row.hidden = false;
                collection.push(row);
            }
            else { row.hidden = true; }
        }
        <c:forEach items="${wayList}" var="way">
        if(${way.id} == id) {
            document.getElementById("wei").innerHTML = ${wayService.getMaxWeight(way.id)};
        }
        </c:forEach>
        return collection;
    }
</script>
<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <select name="startPoint" onchange="sort(x(value))">
        <option selected hidden>Путь</option>
        <c:forEach items="${wayList}" var="way">
            <option value="${way.id}">${way.id}) ${way.startPoint.name} - ${way.endPoint.name}</option>
        </c:forEach>
    </select><br/>
    Максимальная нагрузка: <label id="wei">0</label><br/>
    <table border="1" id="requestsTable" hidden="true">
        <caption>Список заявок пользователя</caption>
        <tr>
            <th>ID заявки</th>
            <th>ID пути</th>
            <th>Пункт отправления</th>
            <th>Пункт прибытия</th>
            <th>Вес груза</th>
            <th>Цена поставки</th>
            <th>Отправлено</th>
            <th>Пользователь</th>
        </tr>
        <c:forEach items="${requests}" var="request">
            <tr id="${request.id}">
                <td>${request.id}</td>
                <td>${request.way.id}</td>
                <td>${request.way.startPoint.name}</td>
                <td>${request.way.endPoint.name}</td>
                <td>${request.weight}</td>
                <td>${request.cost}</td>
                <td>${request.complete}</td>
                <td>${request.user.name}</td>
            </tr>
        </c:forEach>
    </table><br/>
    <label id="res" hidden="true"></label> <label id="opt"></label><br/>
    <button onclick="window.location.href = 'http://localhost:8080/requests/pushing?get='+document.getElementById('opt').innerHTML;">Отправить</button>
</div>
<%@include file='parts/footer.jsp'%>

