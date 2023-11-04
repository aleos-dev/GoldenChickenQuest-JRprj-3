package me.empty.objects;

import java.util.List;
import java.util.Map;

public record Quest(String title,
                    String story,
                    Map<String, Decision> decisions) {

    public record Decision(String prompt,
                           List<Option> options) {
    }

    public record Option(String title,
                         String story,
                         String next) {
    }
}

