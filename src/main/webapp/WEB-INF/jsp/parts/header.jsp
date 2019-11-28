<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Поставки</title>

</head>
<body>
<header>
    <table>
        <tr>
            <td><a href="${pageContext.request.contextPath}/">Главная</a></td>
            <td><a href="${pageContext.request.contextPath}/points">Точки снабжения</a></td>
            <td><a href="${pageContext.request.contextPath}/ways">Пути снабжения</a></td>
            <td><a href="${pageContext.request.contextPath}/transport">Транспорт компании</a></td>
            <c:if test="${isLogin}"><td><a href="${pageContext.request.contextPath}/requests">Заявки</a></td></c:if>
            <c:if test="${isAdmin}"><td><a href="${pageContext.request.contextPath}/requests/push">Отправить</a></td></c:if>
            <c:if test="${!isLogin}"><td class = "log" id = "login"><a href="${pageContext.request.contextPath}/login">Вход</a></td></c:if>
            <c:if test="${isLogin}">
            <td class = "log" id = "logout">
                <form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="submit" value="Выход">
                </form>
            </td>
            </c:if>
        </tr>
    </table>
</header>
<article>