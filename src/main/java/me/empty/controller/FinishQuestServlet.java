package me.empty.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/finishQuest")
public class FinishQuestServlet extends HttpServlet {

    public static final String QUEST_FINISH_JSP_REQUEST_PATH = "/WEB-INF/views/finishQuest.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(QUEST_FINISH_JSP_REQUEST_PATH).forward(req, resp);
    }

}
