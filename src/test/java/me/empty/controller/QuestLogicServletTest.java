package me.empty.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.empty.objects.Quest.Decision;
import me.empty.objects.Quest.Option;
import me.empty.service.QuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestLogicServletTest {

    public static final String QUEST_JSP_REQUEST_PATH = "/WEB-INF/views/quest.jsp";
    public static final String QUEST_END_REDIRECT_PATH = "finishQuest";

    public static final String QUEST_SERVICE_ATTRIBUTE_NAME = "questService";
    public static final String DECISION_ATTRIBUTE_NAME = "decision";
    public static final String OPTION_CHOISE_ATTRIBUTE_NAME = "choiceIndex";
    public static final String CHOICE_CONTEXT_PARAMETER_NAME = "choiceContext";
    public static final String TITLE_ATTRIBUTE_NAME = "title";
    public static final String STORY_ATTRIBUTE_NAME = "story";
    public static final String QUEST_END_ATTRIBUTE_NAME = "end";

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    HttpSession session;
    @Mock
    QuestService service;
    @Mock
    ServletContext context;

    @InjectMocks
    private QuestLogicServlet servlet;

    @Mock
    private Decision decision;

    private Option option;

    @BeforeEach
    void setUp() {
        servlet = new QuestLogicServlet() {
            @Override
            protected void defineBackgroundImage(HttpSession session, QuestService service, ServletContext context) {
            }

            @Override
            public ServletContext getServletContext() {
                return context;
            }
        };
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(OPTION_CHOISE_ATTRIBUTE_NAME)).thenReturn("0");
        when(request.getParameter(CHOICE_CONTEXT_PARAMETER_NAME)).thenReturn(null);
        when(session.getAttribute(QUEST_SERVICE_ATTRIBUTE_NAME)).thenReturn(service);
        when(service.getDecision(anyString())).thenReturn(decision);
    }

    @Test
    void doPost_ShouldRedirectToEnd_WhenQuestIsFinished() throws ServletException, IOException {
        option = new Option("TestTitle", "TestStory", QUEST_END_ATTRIBUTE_NAME);
        when(decision.options()).thenReturn(List.of(option));

        servlet.doPost(request, response);

        verify(response).sendRedirect(QUEST_END_REDIRECT_PATH);
    }

    @Test
    void doPost_ShouldContinueQuest_WhenDecisionIsAvailable() throws ServletException, IOException {
        option = new Option("TestTitle", "TestStory", "No " + QUEST_END_ATTRIBUTE_NAME);
        when(decision.options()).thenReturn(List.of(option));
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).getRequestDispatcher(QUEST_JSP_REQUEST_PATH);
    }

    @Test
    void doPost_ShouldSetAttributes_WhenNextDecisionIsAvailable() throws ServletException, IOException {
        option = new Option("TestTitle", "TestStory", "No " + QUEST_END_ATTRIBUTE_NAME);
        when(decision.options()).thenReturn(List.of(option));
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute(TITLE_ATTRIBUTE_NAME, "TestTitle");
        verify(session).setAttribute(STORY_ATTRIBUTE_NAME, "TestStory");
        verify(session).setAttribute(DECISION_ATTRIBUTE_NAME, "No " + QUEST_END_ATTRIBUTE_NAME);
    }
}
