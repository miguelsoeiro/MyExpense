<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Miguel Soeiro
  Date: 09/01/2017
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
    <div class="page">
        <tiles:insertAttribute name = "header" />
        <div class="content">
            <tiles:insertAttribute name = "body" />
        </div>
        <tiles:insertAttribute name = "footer" />
    </div>
    </body>
</html>
