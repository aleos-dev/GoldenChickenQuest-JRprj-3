package me.empty;

import me.empty.service.QuestService;

public class Main {

    public static void main(String[] args) {
        QuestService service = new QuestService("GoldenChicken.json");
        System.out.println(service.getStory());
    }
}
