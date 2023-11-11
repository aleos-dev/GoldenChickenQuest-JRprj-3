package me.empty.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import me.empty.service.QuestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static me.empty.controller.BaseQuestServlet.DECISION_ATTRIBUTE_NAME;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BaseQuestServletTest {

    @Mock
    private HttpSession session;
    @Mock
    private QuestService service;
    @Mock
    private ServletContext context;
    private final BaseQuestServlet baseQuestServletImpl = new BaseQuestServlet() {};
    @Test
    void defineBackgroundImage_ifDecisionContextIsPresent() {
        String newDecision = "newDecision";
        String decisionContext = "decisionContext";
        when(session.getAttribute(DECISION_ATTRIBUTE_NAME)).thenReturn(newDecision);
        when(service.getNameForBackgroundImage(newDecision, context))
                .thenReturn(Optional.of(decisionContext));

        baseQuestServletImpl.defineBackgroundImage(session, service, context);

        verify(session).setAttribute(anyString(), eq("decisionContext"));
    }

    @Test
    void defineBackgroundImage_ifDecisionContextIsEmpty() {
        when(session.getAttribute(DECISION_ATTRIBUTE_NAME)).thenReturn("decisionKey");
        when(service.getNameForBackgroundImage("decisionKey", context))
                .thenReturn(Optional.empty());

        baseQuestServletImpl.defineBackgroundImage(session, service, context);

        verify(session, never()).setAttribute(anyString(), any());
    }

    @Test
    void defineBackgroundImage_whenDecisionKeyIsNull() {
        when(session.getAttribute(DECISION_ATTRIBUTE_NAME)).thenReturn(null);

        baseQuestServletImpl.defineBackgroundImage(session, service, context);

        verify(service, never()).getNameForBackgroundImage(anyString(), eq(context));
    }
}
