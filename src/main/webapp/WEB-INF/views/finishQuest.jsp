<%--
  Created by IntelliJ IDEA.
  User: aleos
  Date: 11/4/23
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Finish Page</title>
    <link rel="text/css" href="${pageContext.request.contextPath}/static/css/body.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/finishQuest.css">
</head>
<body>

<div class="finish-container">
    <h1 class="finish-header">Congratulations!</h1>
    <p class="finish-message">
        You've successfully completed the process. We appreciate your effort.
    </p>
    <%-- You might want to include dynamic data here, for example: --%>
    <p class="finish-message">
        Your score is: <%= request.getAttribute("score") %>
    </p>
    <%-- Replace "score" with the actual attribute you are passing to the JSP --%>
    <button id="finishButton" class="finish-button" onclick="location.href='index.jsp';">Go Home</button>
    <div class="finish-image">
        <img src="../images/finish.jpg" alt="Finish Celebration" />
    </div>
</div>

<script src="scripts/finish.js"></script> <!-- If you have any JavaScript -->

</body>
</html>
