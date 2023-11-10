package me.empty.service;

import jakarta.servlet.ServletContext;
import me.empty.objects.Quest;
import me.empty.objects.Quest.Decision;
import me.empty.objects.Quest.Option;
import me.empty.repository.QuestRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class QuestService {

    private final Quest quest;

    public QuestService(String questName) {
        var repository = new QuestRepository(questName);
        quest = repository.getQuest();
    }

    public String getStory() {
        return quest.story();
    }

    public String getStory(Option option) {
        return option.story();
    }

    public String getTitle() {
        return quest.title();
    }

    public String getTitle(Option option) {
        return option.title();
    }

    public Decision getDecision(String key) {
        return quest.decisions().get(key);
    }

    public String getPrompt(Decision decision) {
        return decision.prompt();
    }

    public String getPrompt(String decision) {
        return quest.decisions().get(decision).prompt();
    }

    public List<Option> getOptions(String decision) {
        return quest.decisions().get(decision).options();
    }

    public String getNextKey(Option option) {
        return option.next();
    }

    public Optional<String> getNameForBackgroundImage(String decision, ServletContext context) {
        String fileName = decision + ".jpg";
        String imagePathDir = "/static/images/";
        String newBackgroundPath = context.getRealPath(imagePathDir + fileName);

        File file = new File(newBackgroundPath);
        if (file.exists()) {
            return Optional.of(fileName);
        }

        return Optional.empty();
    }
}
