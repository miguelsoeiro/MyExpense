<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Nova Despesa</title>
    <ul>
        <li><a class="home" href="/">Home</a></li>
        <li><a href="/form">Registar Despesas</a></li>
        <li><a href="/list">Consultar despesas</a></li>
        <li><a href="/map">Mapa mensal de despesas</a></li>
        <li><a href="/household">Seu Agregado Familia</a></li>
        <li><a href="/householdmap">Mapa do Agregado Familia</a></li>
        <li><a href="/upload">Upload do Homebanking</a></li>
        <security:authorize access="hasRole('ROLE_ADMIN')"><li><a href="/category">Categorias</a></li></security:authorize>
        <li style="float:right"><a class="about" href="about">About</a></li>
    </ul>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            border: 1px solid #e7e7e7;
            background-color: #f3f3f3;
        }
        li {
            float:left;
        }
        li a {
            display: block;
            color: #666;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        li a:hover:not(.active) {
            background-color: #ddd;
        }
        li a.home {
            color: white;
            background-color: #008cba;
        }
        li a.about {
            color: white;
            background-color: #008cba;
        }
        a:link {
            text-decoration: none;
            color: #008cba
        }
        a:visited {
            text-decoration: none;
            color: #008cba
        }

        a:hover {
            text-decoration: underline;
        }

        a:active {
            text-decoration: underline;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #008cba;
            color: white;
        }
        div {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
        input[type=text], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type=submit] {
            width: 100%;
            background-color: #008cba;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type=submit]:hover {
            background-color: #008cba;
        }
        input[type=button], input[type=reset] {
            background-color: #008cba;
            border: none;
            color: white;
            padding: 16px 32px;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<br>
<div>
    <security:authorize access="isAuthenticated()">


    <p>
        <form:form method="POST" modelAttribute="userForm" action="/form">
            <form:hidden path="id"/>
        <p>
            Categoria:
            <form:select  path="category" id="category">
                <% /*
                <form:option value="">Escolher Categoria</form:option>
                <form:option value="Transportes">Transportes</form:option>
                <form:option value="Alimentação">Alimentação</form:option>
                <form:option value="Propinas">Propinas</form:option>
                <form:option value="Renda">Renda</form:option>
                <form:option value="Outros">Outros</form:option>
                */ %>
                <c:choose>
                    <c:when test="${not empty categorias}">
                        <c:forEach var="categoriasValue" items="${categorias}">
                            <form:option value="${categoriasValue.category}">${categoriasValue.category}</form:option>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </form:select>
            <form:errors path="category" cssClass="error" />
        </p>

        <p>
            <form:label path="description">Descrição</form:label>
            <form:input path="description" label="Descrição" />
            <form:errors path="description" cssClass="error" />
        </p>

        <p>
            <form:label path="value">Valor(EUR)</form:label>
            <form:input path="value" label="Valor"/>
            <form:errors path="value" cssClass="error" />
        </p>

        <p>
            <form:label path="local">Localização</form:label>
            <form:input path="local" label="Localização" />
            <form:errors path="local" cssClass="error" />
        </p>

          <input type="submit" name="Gravar"/>

        </form:form>
    </p>
    <p>Hello <security:authentication property="principal.username"/>, <a href="/logout">Logout</a></p>
    </security:authorize>

    <security:authorize access="!isAuthenticated()">
        TESTE!!! Não estando logoda!
    </security:authorize>
</div>
</body>
</html>

