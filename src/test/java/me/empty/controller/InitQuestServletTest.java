package me.empty.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.empty.service.QuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InitQuestServletTest {

    @Mock private HttpServletRequest request;

    @Mock private HttpServletResponse response;

    @Mock private RequestDispatcher requestDispatcher;

    @Mock private HttpSession session;
    
    @Mock private ServletContext context;


    private InitQuestServlet servlet;

    @BeforeEach
    public void setUp() {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/views/quest.jsp")).thenReturn(requestDispatcher);

        servlet = new InitQuestServlet() {
            @Override
            protected void defineBackgroundImage(HttpSession session, QuestService service, ServletContext context) {
            }

            @Override
            public ServletContext getServletContext() {
                return context;
            }
        };
    }

    @Test
    void doGet_ShouldGetSession() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(request, times(1)).getSession();
    }

    @Test
    void doGet_ShouldSetAttributes_WhenInitServletCall() throws ServletException, IOException {
        servlet.doGet(request, response);

        verifyAttributeSettings();
    }

    @Test
    void doGet_ShouldDispatchRequest() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
    }

    private void verifyAttributeSettings() {
        verify(session).setAttribute(eq("questService"), any(QuestService.class));
        verify(session).setAttribute(eq("decision"), eq("root"));
        verify(session).setAttribute(eq("title"), anyString());
        verify(session).setAttribute(eq("story"), anyString());
    }
}
