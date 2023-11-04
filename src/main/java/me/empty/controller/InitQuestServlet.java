package me.empty.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.empty.service.QuestService;

import java.io.IOException;

@WebServlet("/initQuest")
public class InitQuestServlet extends HttpServlet {

    private static final String GOLDEN_CHIKEN = "GoldenChicken.json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        initQuest(req);

        req.getRequestDispatcher("/WEB-INF/views/quest.jsp").forward(req, resp);
    }

    private void initQuest(HttpServletRequest req) {
        HttpSession session = req.getSession();
        QuestService service = new QuestService(GOLDEN_CHIKEN);

        session.setAttribute("questService", service);
        session.setAttribute("decision", "root");
        session.setAttribute("title", service.getTitle());
        session.setAttribute("story", service.getStory());
    }
}
