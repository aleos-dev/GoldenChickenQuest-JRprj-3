package me.empty.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.empty.service.QuestService;

import java.io.IOException;

import static me.empty.objects.Quest.*;

@WebServlet("/questLogic")
public class QuestLogicServlet extends BaseQuestServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        QuestService service = getQuestServiceFromSession(session);
        Decision decision = getCurrentDecisionAndDefineBackground(session, service);

        Option option = getSelectedOption(req, decision);

        processOption(option, session, service, resp, req);
    }


    private QuestService getQuestServiceFromSession(HttpSession session) {
        return (QuestService) session.getAttribute(QUEST_SERVICE_ATTRIBUTE_NAME);
    }

    private Decision getCurrentDecisionAndDefineBackground(HttpSession session, QuestService service) {
        String decisionKey = (String) session.getAttribute(DECISION_ATTRIBUTE_NAME);

        return service.getDecision(decisionKey);
    }

    private Option getSelectedOption(HttpServletRequest req, Decision decision) {
        int choice = Integer.parseInt(req.getParameter(OPTION_CHOICE_ATTRIBUTE_NAME));
        return decision.options().get(choice);
    }

    private void processOption(Option option, HttpSession session, QuestService service, HttpServletResponse resp, HttpServletRequest req) throws IOException, ServletException {
        if (isQuestEnd(option)) {
            resp.sendRedirect(FINISH_QUEST_END_POINT);
        } else {
            updateSessionForNextStep(option, session);
            defineBackgroundImage(session, service, getServletContext());
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
