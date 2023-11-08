package me.empty.repository;

import me.empty.objects.Quest;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuestRepositoryTest {

    private static final Path TEST_QUEST_PATH = Path.of("repository/GoldenChicken.json");
    private QuestRepository questRepository;

    @Test
    public void testQuestLoading() {
        questRepository = new QuestRepository(TEST_QUEST_PATH.toString());

        Quest quest = questRepository.getQuest();

        assertNotNull(quest, "Quest should be loaded and not null");
    }
}
