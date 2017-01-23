<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Lista de Despesas do ADMIN</title>
    <ul>
        <li><a class="home" href="/">Home</a></li>
        <li><a href="form">Registar Despesas</a></li>
        <li><a href="list">Consultar despesas</a></li>
        <li><a href="map">Mapa mensal de despesas</a></li>
        <li><a href="/household">Seu Agregado Familia</a></li>
        <li><a href="/householdmap">Mapa do Agregado Familia</a></li>
        <li><a href="/upload">Upload do Homebanking</a></li>
        <security:authorize access="hasRole('ROLE_ADMIN')"><li><a href="/category">Categorias</a></li></security:authorize>
        <li style="float:right"><a class="about" href="about">About</a></li>
    </ul>
</head>
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
    input[type=button], input[type=submit], input[type=reset] {
        background-color: #008cba;
        border: none;
        color: white;
        padding: 16px 32px;
        text-decoration: none;
        margin: 4px 2px;
        cursor: pointer;
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
</style>
<body>
<br>
<div>
    <c:choose>
        <c:when test="${not empty utilizadores}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Morada</th>
                    <th>Descrição</th>
                    <th>Categoria</th>
                    <th>Valor</th>
                    <th>Local</th>
                    <th>Data</th>
                    <th>Infos</th>
                </tr>
                <c:forEach var="utilizador" items="${utilizadores}">
                    <tr>
                        <td>${utilizador.id}</td>
                        <td>${utilizador.name}</td>
                        <td>${utilizador.address}</td>
                        <td>${utilizador.description}</td>
                        <td>${utilizador.category}</td>
                        <td>${utilizador.value}</td>
                        <td>${utilizador.local}</td>
                        <td>${utilizador.date}</td>
                        <td><a href="/info/${utilizador.id}">Mais info</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>Não tem resultados</p>
        </c:otherwise>
    </c:choose>
</div>
<br>
<div>
    COMEÇA AGORA!!!
    <c:choose>
        <c:when test="${not empty utilizadores}">
            <table border="1">
                <tr>
                    <th>Data</th>
                    <th>Categoria</th>
                    <th>Valor</th>
                    <th>Detalhes</th>
                </tr>
                <c:forEach var="utilizador" items="${utilizadores}">
                    <tr>
                        <td>${utilizador.date}</td>
                        <td>${utilizador.category}</td>
                        <td>${utilizador.value}</td>
                        <td><a href="/info/${utilizador.id}">Mais Detalhes</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>Não tem resultados</p>
        </c:otherwise>
    </c:choose>
    <br>
    <form action="/form" method="get" style="display: inline">
        <input type="submit" value="Inserir Nova Despesa" />
    </form>
</div>
</body>
</html>

