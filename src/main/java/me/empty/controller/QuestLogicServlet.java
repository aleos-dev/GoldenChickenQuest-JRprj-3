package me.empty.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.empty.service.QuestService;

import java.io.IOException;

import static me.empty.objects.Quest.*;
import static me.empty.objects.Quest.Decision;

@WebServlet("/questLogic")
public class QuestLogicServlet extends HttpServlet {

    public static final String QUEST_JSP_REQUEST_PATH = "/WEB-INF/views/quest.jsp";
    public static final String QUEST_END_REDIRECT_PATH = "finishQuest";

    public static final String QUEST_SERVICE_ATTRIBUTE_NAME = "questService";
    public static final String DECISION_ATTRIBUTE_NAME = "decision";
    public static final String OPTION_CHOICE_ATTRIBUTE_NAME = "choiceIndex";
    public static final String TITLE_ATTRIBUTE_NAME = "title";
    public static final String STORY_ATTRIBUTE_NAME = "story";
    public static final String QUEST_END_ATTRIBUTE_NAME = "end";


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        QuestService service = getQuestServiceFromSession(session);
        Decision decision = getCurrentDecision(session, service);
        Option option = getSelectedOption(req, decision);

        processOption(option, session, resp, req);
    }

    private QuestService getQuestServiceFromSession(HttpSession session) {
        return (QuestService) session.getAttribute(QUEST_SERVICE_ATTRIBUTE_NAME);
    }

    private Decision getCurrentDecision(HttpSession session, QuestService service) {
        return service.getDecision((String) session.getAttribute(DECISION_ATTRIBUTE_NAME));
    }

    private Option getSelectedOption(HttpServletRequest req, Decision decision) {
        int choice = Integer.parseInt(req.getParameter(OPTION_CHOICE_ATTRIBUTE_NAME));
        return decision.options().get(choice);
    }

    private void processOption(Option option, HttpSession session, HttpServletResponse resp, HttpServletRequest req) throws IOException, ServletException {
        if (isQuestEnd(option)) {
            resp.sendRedirect(QUEST_END_REDIRECT_PATH);
        } else {
            updateSessionForNextStep(option, session);
            forwardToQuestPage(req, resp);
        }
    }

    private boolean isQuestEnd(Option option) {
        return QUEST_END_ATTRIBUTE_NAME.equals(option.next());
    }

    private void updateSessionForNextStep(Option option, HttpSession session) {
        session.setAttribute(DECISION_ATTRIBUTE_NAME, option.next());
        session.setAttribute(TITLE_ATTRIBUTE_NAME, option.title());
        session.setAttribute(STORY_ATTRIBUTE_NAME, option.story());
    }

    private void forwardToQuestPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(QUEST_JSP_REQUEST_PATH);
        dispatcher.forward(req, resp);
    }
}
