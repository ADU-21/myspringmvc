<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: adu
  Date: 09/05/2018
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:eval expression="@featureToggles.enabled('test-toggle')" var="testToggle" />

<html>
<head>
    <title>${name}</title>
</head>
<body>
<h1>${msg}</h1><br>
<p><b>handlerFeatureToggle:</b> ${handlerFeatureToggle}</p><br>
<c:choose>
    <c:when test="${testToggle}">
        <%System.out.println("This is feature Toggle for JSP on");%>
        <p>This is feature Toggle for JSP on</p>
    </c:when>
    <c:otherwise>
        <%System.out.println("This is feature Toggle for JSP off");%>
        <p>This is feature Toggle for JSP off</p>
    </c:otherwise>
</c:choose>
</body>
</html>
