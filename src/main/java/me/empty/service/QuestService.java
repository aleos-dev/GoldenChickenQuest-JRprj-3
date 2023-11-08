package me.empty.service;

import me.empty.objects.Quest;
import me.empty.objects.Quest.Decision;
import me.empty.objects.Quest.Option;
import me.empty.repository.QuestRepository;

import java.util.List;

public class QuestService {

    private final Quest quest;

    public QuestService(String questName) {
        var repository = new QuestRepository(questName);
        quest = repository.getQuest();
    }

    public String getStory() {
        return quest.story();
    }

    public String getTitle() {
        return quest.title();
    }

    public Decision getDecision(String key) {
        return quest.decisions().get(key);
    }

}
