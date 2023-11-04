package me.empty.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.empty.service.QuestService;

import java.io.IOException;
import java.util.Objects;

import static me.empty.objects.Quest.*;
import static me.empty.objects.Quest.Decision;

@WebServlet("/questLogic")
public class QuestLogicServlet extends HttpServlet {

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            HttpSession session = req.getSession();
            QuestService service = (QuestService) session.getAttribute("questService");

            Decision decision = service.getDecision((String) session.getAttribute("decision"));

            int optionIndex = Integer.parseInt(req.getParameter("choiceIndex"));
            Option option = decision.options().get(optionIndex);

            if (Objects.equals(option.next(), "end")) {
                resp.sendRedirect("finishQuest");
                return;
            }

            session.setAttribute("decision", option.next());
            session.setAttribute("title", option.title());
            session.setAttribute("story", option.story());

            req.getRequestDispatcher("/WEB-INF/views/quest.jsp").forward(req, resp);
        }

}
