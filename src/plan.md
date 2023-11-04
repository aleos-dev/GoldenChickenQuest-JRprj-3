Given your task, let's break it down step-by-step and provide a basic plan:

**1. Storyline & Game Flow:**
Before you start with the technical parts, decide on a storyline for your text-based quest. You could create a simple quest like "Escape from a Haunted Mansion" or "Find the Lost Treasure". Create a flowchart or a decision tree that outlines the different paths the player can take based on their answers.

**2. Project Structure:**
- Set up a Maven project.
- Add necessary dependencies: Servlet API, JSP, JSTL, and JUnit for testing.
  
**3. Welcome Page:**
- Use a JSP page to show a welcome message, possibly explaining the backstory of your quest.
  
**4. Servlets:**
- Have one main servlet to control the game flow. This servlet will process the player's answers and determine the next step/question.
- Use `HttpSession` to store information about the player, such as their name, the number of games played, or current progress.

**5. Game Logic:**
- Each decision point can be a separate JSP page. For example, after an introduction, the player might have to choose between "Enter the mansion" or "Leave". Each choice redirects to a different JSP page.
- Use JSTL tags in your JSP pages to display dynamic content and make decisions.

**6. Ending the Game:**
- Have distinct pages or messages for both winning and losing outcomes.
- Provide a link or button to restart the game.

**7. Testing:**
- Use JUnit to write unit tests. You might want to test the game flow logic, session attributes setting/getting, and other critical parts of your application.

**8. Deployment:**
- Make sure everything is working on Tomcat 9.

**9. Optional Enhancements:**
- Use CSS to style your JSP pages and make the game visually appealing.
- Add more features like scoring based on decisions, time taken, etc.

**10. Documentation:**
- Write clear documentation on how to set up and play the game, along with any other pertinent information.

**Project Timeline:**
- **Day 1:** Set up Maven project, decide on a storyline, and sketch out the game flow.
- **Day 2:** Implement the servlets and basic JSP pages, focus on game flow.
- **Day 3:** Add session handling and advanced game features.
- **Day 4:** Implement the welcome page and style your game using CSS.
- **Day 5:** Write JUnit tests.
- **Day 6:** Test on Tomcat 9, debug any issues, and finalize the project.
- **Day 7:** Review, finalize documentation, and submit.

This is a rough plan, and you might need to adjust based on your pace and any challenges you face. Good luck with your project!

Of course! JSTL (JavaServer Pages Standard Tag Library) provides a collection of tags that simplify the JSP development process. These tags can be used to control the flow of processing a JSP page, iterate over collections, format and parse data, handle exceptions, and more.

Here are some basic examples of using JSTL tags to display dynamic content and make decisions:

1. **Setting up your project to use JSTL:**
   First, you need to add the JSTL dependency to your Maven `pom.xml`:
   ```xml
   <dependency>
       <groupId>javax.servlet.jsp.jstl</groupId>
       <artifactId>javax.servlet.jsp.jstl-api</artifactId>
       <version>1.2.1</version>
   </dependency>
   ```

2. **Importing JSTL Core Library in JSP:**
   ```jsp
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   ```

3. **Display Dynamic Content:** Use the `${...}` expression language (EL) syntax to bind object attributes to UI components.
   ```jsp
   Welcome, <c:out value="${playerName}" />!
   ```

4. **Conditionally Display Content:**
   ```jsp
   <c:choose>
       <c:when test="${score >= 100}">
           Congratulations, you've achieved a high score!
       </c:when>
       <c:otherwise>
           Keep trying to get a high score.
       </c:otherwise>
   </c:choose>
   ```

5. **Iterate Over Collections:** If you want to display a list of items, for example:
   ```jsp
   <c:forEach items="${gameHistory}" var="game">
       Game played on: <c:out value="${game.date}" />
       Result: <c:out value="${game.result}" />
   </c:forEach>
   ```

6. **Use URL Parameters and Conditions:**
   Let's say you want to handle a decision in your text-based game based on a URL parameter:
   ```jsp
   <c:if test="${param.choice == 'enterMansion'}">
       You've chosen to enter the haunted mansion! Brave soul.
   </c:if>
   <c:if test="${param.choice == 'leave'}">
       You've chosen to leave the mansion. Perhaps it's for the best.
   </c:if>
   ```

These are just some basic examples of how you can use JSTL tags in your JSP pages to handle dynamic content and decision-making. The real power comes when you combine JSTL with the backend logic of your application.