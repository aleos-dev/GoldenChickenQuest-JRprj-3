<%--
  Created by IntelliJ IDEA.
  User: aleos
  Date: 11/3/23
  Time: 7:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quest Adventure</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/body.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/content.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/quest-container.css">
</head>
<body>
<div class="content">
    <div class="quest-container">

        <div class="story-section">
            <h2><c:out value="${title}"/></h2>
            <p id="current-quest-story"><c:out value="${story}"/></p>
        </div>

        <div class="options-header">
            <p>${questService.getPrompt(decision)}</p>
            <h5>Make your choice!</h5>
        </div>

        <div class="path-options">

            <c:forEach var="option" items="${questService.getOptions(sessionScope.decision)}" varStatus="status">
                <div class="path" id="${questService.getNextKey(option)}">
                    <h2><c:out value="${questService.getTitle(option)}"/></h2>
                    <p><c:out value="${questService.getStory(option)}"/></p>
                    <form action="questLogic" method="post">
                        <input type="hidden" name="choiceIndex" value="${status.index}">
                        <button type="submit"><c:out value="${questService.getTitle(option)}"/></button>
                    </form>
                </div>
            </c:forEach>

        </div>

        <button class="return-button" onclick="location.href='index.jsp'">Return to Welcome Page
        </button>
    </div>
</div>

</body>
</html>
