package me.empty.service;

import me.empty.objects.Quest.Decision;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static me.empty.objects.Quest.Option;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestServiceTest {

    private static final Path TEST_QUEST_PATH = Path.of("repository/GoldenChicken.json");

    QuestService service = new QuestService(TEST_QUEST_PATH.toString());

    @Test
    void getStory_ShouldReturnRightStory() {
        String story = "The village is in disarray; the prized Chicken Cup Trophy has gone missing on the eve of the Great Fowl Fair! As the local detective, you've been summoned to uncover this peculiar poultry predicament.";

        String result = service.getStory();

        assertEquals(story, result);
    }

    @Test
    void getTitle_ShouldReturnRightTitle() {
        String title = "The Great Trophy Quest";

        String result = service.getTitle();

        assertEquals(title, result);
    }


    @Test
    void getDecision_ShouldReturnRightDecision() {
        String decisionPrompt = "Where will you start your investigation?";
        String optionTitle = "The Blacksmith's Forge";

        Decision decision = service.getDecision("root");
        Option option = decision.options().get(1);

        assertEquals(decisionPrompt, decision.prompt());
        assertEquals(optionTitle, option.title());
    }
}