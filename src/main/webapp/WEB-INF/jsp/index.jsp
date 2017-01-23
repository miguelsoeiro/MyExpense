<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <title>Lista de Despesas</title>
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
        .footer {
            position: absolute;
            right: 0;
            bottom: 0;
            left: 0;
            padding: 1rem;
            background-color: #efefef;
            text-align: center;
        }
    </style>
</head>
<body>
<footer>
    <div class="footer">Todos os Direitos reservados ©<security:authentication property="principal.username"/>, <a href="/logout">Logout</a></strong>.</div>
</footer>
</body>
</html>