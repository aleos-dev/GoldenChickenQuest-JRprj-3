<%--
  Created by IntelliJ IDEA.
  User: aleos
  Date: 11/4/23
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Finish Page</title>
    <link rel="text/css" href="${pageContext.request.contextPath}/static/css/body.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/finishQuest.css">
</head>
<body>

<div class="finish-container" style="background-image: url('${pageContext.request.contextPath}/static/images/finish.jpg');">
    <h1 class="finish-header">Congratulations!</h1>
    <p class="finish-message">
        You've successfully completed the quest.
    </p>

    <p class="finish-message">
        Your score is: excellent
    </p>
    <button id="finishButton" class="finish-button" onclick="location.href='index.jsp';">Go Start Page</button>
</div>

</body>
</html>
