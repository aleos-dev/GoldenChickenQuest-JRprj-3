package me.empty.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static me.empty.controller.FinishQuestServlet.QUEST_FINISH_JSP_REQUEST_PATH;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinishQuestServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private HttpSession session;

    private FinishQuestServlet servlet = new FinishQuestServlet();

    @Test
    void doGet_ShouldDispatchRequestToFinishJSP() throws ServletException, IOException {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(request).getRequestDispatcher(QUEST_FINISH_JSP_REQUEST_PATH);
    }
}