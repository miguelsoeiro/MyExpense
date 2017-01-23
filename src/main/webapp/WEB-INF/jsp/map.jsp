<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Mapa Mensal de Despesas</title>
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
    <link href="../css/stylenodiv.css" rel="stylesheet" type="text/css">
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['bar']});

        google.charts.setOnLoadCallback(drawChart);
        google.charts.setOnLoadCallback(drawChartTotal);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Meses', 'Transportes', 'Alimentação', 'Propinas', 'Renda', 'Outro', 'Total Mensal'],
                ['Janeiro', ${totaisJaneiro.transportesValue}, ${totaisJaneiro.alimentacaoValue}, ${totaisJaneiro.propinasValue}, ${totaisJaneiro.redaValue}, ${totaisJaneiro.outroValue}, ${totaisJaneiro.totalValue}],
                ['Fevereiro', ${totaisFevereiro.transportesValue}, ${totaisFevereiro.alimentacaoValue}, ${totaisFevereiro.propinasValue}, ${totaisFevereiro.redaValue}, ${totaisFevereiro.outroValue}, ${totaisFevereiro.totalValue}],
                ['Março', ${totaisMarco.transportesValue}, ${totaisMarco.alimentacaoValue}, ${totaisMarco.propinasValue}, ${totaisMarco.redaValue}, ${totaisMarco.outroValue}, ${totaisMarco.totalValue}],
                ['Abril', ${totaisAbril.transportesValue}, ${totaisAbril.alimentacaoValue}, ${totaisAbril.propinasValue}, ${totaisAbril.redaValue}, ${totaisAbril.outroValue}, ${totaisAbril.totalValue}],
                ['Maio', ${totaisMaio.transportesValue}, ${totaisMaio.alimentacaoValue}, ${totaisMaio.propinasValue}, ${totaisMaio.redaValue}, ${totaisMaio.outroValue}, ${totaisMaio.totalValue}],
                ['Junho', ${totaisJunho.transportesValue}, ${totaisJunho.alimentacaoValue}, ${totaisJunho.propinasValue}, ${totaisJunho.redaValue}, ${totaisJunho.outroValue}, ${totaisJunho.totalValue}],
                ['Julho', ${totaisJulho.transportesValue}, ${totaisJulho.alimentacaoValue}, ${totaisJulho.propinasValue}, ${totaisJulho.redaValue}, ${totaisJulho.outroValue}, ${totaisJulho.totalValue}],
                ['Agosto', ${totaisAgosto.transportesValue}, ${totaisAgosto.alimentacaoValue}, ${totaisAgosto.propinasValue}, ${totaisAgosto.redaValue}, ${totaisAgosto.outroValue}, ${totaisAgosto.totalValue}],
                ['Setembro', ${totaisSetembro.transportesValue}, ${totaisSetembro.alimentacaoValue}, ${totaisSetembro.propinasValue}, ${totaisSetembro.redaValue}, ${totaisSetembro.outroValue}, ${totaisSetembro.totalValue}],
                ['Outubro', ${totaisOutubro.transportesValue}, ${totaisOutubro.alimentacaoValue}, ${totaisOutubro.propinasValue}, ${totaisOutubro.redaValue}, ${totaisOutubro.outroValue}, ${totaisOutubro.totalValue}],
                ['Novembro', ${totaisNovembro.transportesValue}, ${totaisNovembro.alimentacaoValue}, ${totaisNovembro.propinasValue}, ${totaisNovembro.redaValue}, ${totaisNovembro.outroValue}, ${totaisNovembro.totalValue}],
                ['Dezembro', ${totaisDezembro.transportesValue}, ${totaisDezembro.alimentacaoValue}, ${totaisDezembro.propinasValue}, ${totaisDezembro.redaValue}, ${totaisDezembro.outroValue}, ${totaisDezembro.totalValue}],

            ]);

            var options = {
                chart: {
                    title: 'Total Mensal de Despesas',
                    subtitle: 'Grafico Demonstrativo dos Gastos Feitos durante cada Mês e seus Totais.',
                }
            };

            var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

            chart.draw(data, options);
        }
        function drawChartTotal() {
            var data = google.visualization.arrayToDataTable([
                ['Despesas', 'Transportes', 'Alimentação', 'Propinas', 'Renda', 'Outro', 'Total Mensal'],
                ['Total', ${totalissimo.transportesValue}, ${totalissimo.alimentacaoValue}, ${totalissimo.propinasValue}, ${totalissimo.redaValue}, ${totalissimo.outroValue}, ${totalissimo.totalValue}]
            ]);

            var options = {
                chart: {
                    title: 'Total Anual de Despesas',
                    subtitle: 'Grafico Demonstrativo dos Gastos Feitos durante o Ano e seus Totais.',
                }
            };

            var chart = new google.charts.Bar(document.getElementById('columnchart_material_div'));

            chart.draw(data, options);
        }
    </script>
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
    .error {
        color: red; font-weight: bold;
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
    .error {
        color: red; font-weight: bold;
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
        /*background-color: #f2f2f2;*/
        padding: 20px;
    }
</style>
<br>
<body>
<div>
    <table border="1">
        <tr>
            <th>Mês</th>
            <th>Transportes</th>
            <th>Alimentação</th>
            <th>Propinas</th>
            <th>Renda</th>
            <th>Outros</th>
            <th>Total</th>
            <th>Variação</th>
        </tr>
        <tr>
            <td>Janeiro</td>
            <td>${totaisJaneiro.transportesValue}</td>
            <td>${totaisJaneiro.alimentacaoValue}</td>
            <td>${totaisJaneiro.propinasValue}</td>
            <td>${totaisJaneiro.redaValue}</td>
            <td>${totaisJaneiro.outroValue}</td>
            <td>${totaisJaneiro.totalValue}</td>
            <td>-</td>
        </tr>
        <tr>
            <td>Fevereiro</td>
            <td>${totaisFevereiro.transportesValue}</td>
            <td>${totaisFevereiro.alimentacaoValue}</td>
            <td>${totaisFevereiro.propinasValue}</td>
            <td>${totaisFevereiro.redaValue}</td>
            <td>${totaisFevereiro.outroValue}</td>
            <td>${totaisFevereiro.totalValue}</td>
            <td>${variacaoJaneiroFevereiro.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Março</td>
            <td>${totaisMarco.transportesValue}</td>
            <td>${totaisMarco.alimentacaoValue}</td>
            <td>${totaisMarco.propinasValue}</td>
            <td>${totaisMarco.redaValue}</td>
            <td>${totaisMarco.outroValue}</td>
            <td>${totaisMarco.totalValue}</td>
            <td>${variacaoFevereiroMarco.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Abrirl</td>
            <td>${totaisAbril.transportesValue}</td>
            <td>${totaisAbril.alimentacaoValue}</td>
            <td>${totaisAbril.propinasValue}</td>
            <td>${totaisAbril.redaValue}</td>
            <td>${totaisAbril.outroValue}</td>
            <td>${totaisAbril.totalValue}</td>
            <td>${variacaoMarcoAbril.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Maio</td>
            <td>${totaisMaio.transportesValue}</td>
            <td>${totaisMaio.alimentacaoValue}</td>
            <td>${totaisMaio.propinasValue}</td>
            <td>${totaisMaio.redaValue}</td>
            <td>${totaisMaio.outroValue}</td>
            <td>${totaisMaio.totalValue}</td>
            <td>${variacaoAbrilMaio.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Junho</td>
            <td>${totaisJunho.transportesValue}</td>
            <td>${totaisJunho.alimentacaoValue}</td>
            <td>${totaisJunho.propinasValue}</td>
            <td>${totaisJunho.redaValue}</td>
            <td>${totaisJunho.outroValue}</td>
            <td>${totaisJunho.totalValue}</td>
            <td>${variacaoMaioJunho.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Julho</td>
            <td>${totaisJulho.transportesValue}</td>
            <td>${totaisJulho.alimentacaoValue}</td>
            <td>${totaisJulho.propinasValue}</td>
            <td>${totaisJulho.redaValue}</td>
            <td>${totaisJulho.outroValue}</td>
            <td>${totaisJulho.totalValue}</td>
            <td>${variacaoJunhoJulho.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Agosto</td>
            <td>${totaisAgosto.transportesValue}</td>
            <td>${totaisAgosto.alimentacaoValue}</td>
            <td>${totaisAgosto.propinasValue}</td>
            <td>${totaisAgosto.redaValue}</td>
            <td>${totaisAgosto.outroValue}</td>
            <td>${totaisAgosto.totalValue}</td>
            <td>${variacaoJulhoAgosto.variacaoValue} %</td>
        <tr>
            <td>Setembro</td>
            <td>${totaisSetembro.transportesValue}</td>
            <td>${totaisSetembro.alimentacaoValue}</td>
            <td>${totaisSetembro.propinasValue}</td>
            <td>${totaisSetembro.redaValue}</td>
            <td>${totaisSetembro.outroValue}</td>
            <td>${totaisSetembro.totalValue}</td>
            <td>${variacaoAgostoSetembro.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Outobro</td>
            <td>${totaisOutubro.transportesValue}</td>
            <td>${totaisOutubro.alimentacaoValue}</td>
            <td>${totaisOutubro.propinasValue}</td>
            <td>${totaisOutubro.redaValue}</td>
            <td>${totaisOutubro.outroValue}</td>
            <td>${totaisOutubro.totalValue}</td>
            <td>${variacaoSetembroOutobro.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Novembro</td>
            <td>${totaisNovembro.transportesValue}</td>
            <td>${totaisNovembro.alimentacaoValue}</td>
            <td>${totaisNovembro.propinasValue}</td>
            <td>${totaisNovembro.redaValue}</td>
            <td>${totaisNovembro.outroValue}</td>
            <td>${totaisNovembro.totalValue}</td>
            <td>${variacaoOutobroNovembro.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Dezembro</td>
            <td>${totaisDezembro.transportesValue}</td>
            <td>${totaisDezembro.alimentacaoValue}</td>
            <td>${totaisDezembro.propinasValue}</td>
            <td>${totaisDezembro.redaValue}</td>
            <td>${totaisDezembro.outroValue}</td>
            <td>${totaisDezembro.totalValue}</td>
            <td>${variacaoNovembroDezembro.variacaoValue} %</td>
        </tr>
        <tr>
            <td>Total</td>
            <td>${totalissimo.transportesValue}</td>
            <td>${totalissimo.alimentacaoValue}</td>
            <td>${totalissimo.propinasValue}</td>
            <td>${totalissimo.redaValue}</td>
            <td>${totalissimo.outroValue}</td>
            <td>${totalissimo.totalValue}</td>
            <td>-</td>
        </tr>
    </table>
</div>

<div id="columnchart_material" style="width: 1280px; height: 720px;"></div>
<div id="columnchart_material_div" style="width: 1280px; height: 720px;"></div>
</body>
</html>
