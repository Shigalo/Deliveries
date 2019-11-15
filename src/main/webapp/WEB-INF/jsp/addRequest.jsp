<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='parts/header.jsp'%>

<div style="text-align: center; position: relative; top: 20%; left: -20%; ">
    <h3>Авторизация</h3>
    <form action="" method="post">

        <div style="position:relative; padding: 5px"><label>Вес груза : <input type="number" step="0.01" name="weight" id="weight" onchange="alert(this.value)"></label></div>
        <script>
            var input = document.getElementById('weight');
            input.oninput = function() {
                document.getElementById('result').innerHTML = input.value;
            };
        </script>
        <label id="result"></label>

    <%--<div style="position:relative; padding: 5px"><label>Название : <input type="text" name="transportName"></label></div>
        <div style="position:relative; padding: 5px"><label>Максимальная нагрузка : <input type="number" step="0.01" name="max_capacity"></label></div>
        <div style="position:relative; padding: 5px"><label>Цена за кг. : <input type="number" step="0.01" name="unit_cost"></label></div>
        <div style="position:relative; padding: 5px"><label>Скорость : <input type="number" step="0.01" name="speed"></label></div>
        <div style="position:relative; padding: 5px"><label>Коэффициент  опасного груза: <input type="number" step="0.01" name="dangerous"></label></div>
        <div style="position:relative; padding: 5px"><label>Коэффициент хрупкого груза : <input type="number" step="0.01" name="fragile"></label></div>
        <div style="position:relative; padding: 5px"><label>Коэффициент скоропортящегося груза : <input type="number" step="0.01" name="perishable"></label></div>--%>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div style="position:relative; padding: 5px"><input type="submit" value="Добавить"/></div>
    </form>
</div>
<%@include file='parts/footer.jsp'%>
