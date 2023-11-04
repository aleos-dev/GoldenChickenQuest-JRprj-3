<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Quest for the Golden Chicken</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/welcome.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/body.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/content.css">
</head>
<body>

<%-- Welcome back message --%>
<c:if test="${not empty sessionScope.playerName}">
    <p class="welcome-back">Welcome back, <span class="player-name"><c:out value="${sessionScope.playerName}"/></span>!</p>
</c:if>

<div class="content">
    <div class="focal-point"></div>
    <h1>The Quest for the Golden Chicken</h1>
    <p>Welcome to the magical kingdom of Cluckland! A place where chickens rule and every year, the
        bravest of them race for the coveted Golden Chicken Trophy. But, oh no! The trophy has
        vanished! Will you, Sir Cluckles, our mightiest knight and renowned poultry whisperer,
        embark on a quest to find it?</p>

    <c:if test="${not empty sessionScope.playerName}">
        <p>Welcome back, <c:out value="${sessionScope.playerName}"/>!</p>
    </c:if>

    <button id="startButtonPlaceholder" style="visibility: hidden; position: relative;">Holder
    </button>
    <button id="startButton" onclick="location.href='initQuest'">Start the Quest</button>
    <button id="startButtonPlaceholder" style="visibility: hidden; position: relative;">Holder
    </button>

</div>

<script src="${pageContext.request.contextPath}/static/js/welcome.js"></script>

</body>
</html>
