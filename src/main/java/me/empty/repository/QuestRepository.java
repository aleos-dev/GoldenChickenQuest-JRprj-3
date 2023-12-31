package me.empty.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.SneakyThrows;
import me.empty.objects.Quest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

@Getter
public class QuestRepository {

    private final Quest quest;

    public QuestRepository(String pathToQuest) {
        this.quest = loadQuestData(pathToQuest);
    }

    @SneakyThrows
    private Quest loadQuestData(String path) {
        ClassLoader loader = getClass().getClassLoader();

        try (InputStream inputStream = loader.getResourceAsStream(path)) {
            assert inputStream != null;
            try (Reader reader = new InputStreamReader(inputStream)) {

                Gson gson = new Gson();
                Type questMapType = new TypeToken<Quest>() {
                }.getType();

                return gson.fromJson(reader, questMapType);
            }
        }
    }
}
