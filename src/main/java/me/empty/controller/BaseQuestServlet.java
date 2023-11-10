package me.empty.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import me.empty.service.QuestService;

import java.util.Optional;

public abstract class BaseQuestServlet extends HttpServlet {

    protected static final String QUEST_JSP_REQUEST_PATH = "/WEB-INF/views/quest.jsp";
    protected static final String QUEST_FINISH_JSP_REQUEST_PATH = "/WEB-INF/views/finishQuest.jsp";
    protected static final String FINISH_QUEST_END_POINT = "finishQuest";

    protected static final String QUEST_SERVICE_ATTRIBUTE_NAME = "questService";
    protected static final String DECISION_ATTRIBUTE_NAME = "decision";
    protected static final String OPTION_CHOICE_ATTRIBUTE_NAME = "choiceIndex";
    protected static final String TITLE_ATTRIBUTE_NAME = "title";
    protected static final String STORY_ATTRIBUTE_NAME = "story";
    protected static final String QUEST_END_ATTRIBUTE_NAME = "end";
    protected static final String QUEST_CONTAINER_BACKGROUND_IMAGE_NAME = "questContainerBackground";

    protected static final String DEFAULT_QUEST_FILE = "GoldenChicken.json";
    protected static final String DEFAULT_DECISION = "root";



    protected void defineBackgroundImage(HttpSession session, QuestService service, ServletContext context) {
        String decisionKey = (String) session.getAttribute(DECISION_ATTRIBUTE_NAME);
        Optional<String> name = service.getNameForBackgroundImage(decisionKey, context);

        name.ifPresent(backgroundName -> session.setAttribute(QUEST_CONTAINER_BACKGROUND_IMAGE_NAME, backgroundName));
    }
}
