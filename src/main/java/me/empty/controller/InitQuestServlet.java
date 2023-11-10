package me.empty.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.empty.service.QuestService;

import java.io.IOException;

@WebServlet("/initQuest")
public class InitQuestServlet extends BaseQuestServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initQuest(req);

        req.getRequestDispatcher(QUEST_JSP_REQUEST_PATH).forward(req, resp);
    }

    private void initQuest(HttpServletRequest req) {
        HttpSession session = req.getSession();
        QuestService service = new QuestService(DEFAULT_QUEST_FILE);

        session.setAttribute(QUEST_SERVICE_ATTRIBUTE_NAME, service);
        session.setAttribute(DECISION_ATTRIBUTE_NAME, DEFAULT_DECISION);
        session.setAttribute(TITLE_ATTRIBUTE_NAME, service.getTitle());
        session.setAttribute(STORY_ATTRIBUTE_NAME, service.getStory());

        defineBackgroundImage(session, service, getServletContext());
    }
}
