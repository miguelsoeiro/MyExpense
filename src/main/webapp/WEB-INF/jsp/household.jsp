<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Agregado Familiar</title>
    <ul>
        <li><a class="home" href="/">Home</a></li>
        <li><a href="/form">Registar Despesas</a></li>
        <li><a href="/list">Consultar despesas</a></li>
        <li><a href="/map">Mapa mensal de despesas</a></li>
        <li><a href="/household">Seu Agregado Familia</a></li>
        <li><a href="/householdmap">Mapa do Agregado Familia</a></li>
        <li><a href="/upload">Upload do Homebanking</a></li>
        <security:authorize access="hasRole('ROLE_ADMIN')"><li><a href="/category">Categorias</a></li></security:authorize>
        <li style="float:right"><a class="about" href="/about">About</a></li>
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
    <%--
    <form:form method="POST" modelAttribute="agregadoForm" action="/household">
        <form:hidden path="id"/>
    <p>
        <form:label path="user"><b>Procurar Utilizador:</b></form:label>
        <form:input path="user" label="user" />
        <form:errors path="user" cssClass="error" />
    </p>
    <input type="submit" name="Gravar"/>
    </form:form>
    --%>
    <table border="1">
        <tr>
            <th>Utilizadores do seu Agregado (Agregado: ${IDagregado} )</th>
        </tr>
        <c:if test="${not empty nomes}">
            <c:forEach var="nomeValue" items="${nomes}">
                <tr>
                    <td>${nomeValue}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <br></br>
    <table border="1">
        <tr>
            <th>Utilizadores:</th>
            <th>Perfil Com Seus Agregados:</th>
        </tr>
        <tr>
            <td>ADMIN</td>
            <td>
                <form action="/user/1" method="get" style="display: inline">
                    <input type="submit" value="Pefil" />
                </form>
            </td>
        </tr>
        <tr>
            <td>USER1</td>
            <td>
                <form action="/user/2" method="get" style="display: inline">
                    <input type="submit" value="Pefil" />
                </form>
            </td>
        </tr>
        <tr>
            <td>USER2</td>
            <td>
                <form action="/user/3" method="get" style="display: inline">
                    <input type="submit" value="Pefil" />
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

