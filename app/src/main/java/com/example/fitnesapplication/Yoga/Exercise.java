package com.example.fitnesapplication.Yoga;

public class Exercise {
    private final String name;
    private final String description;
    private final int durationInSeconds;
    private final String gifImageUrl;

    public Exercise(String name, String description, int durationInSeconds, String gifImageUrl) {
        this.name = name;
        this.description = description;
        this.durationInSeconds = durationInSeconds;
        this.gifImageUrl = gifImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public String getGifImageUrl() {
        return gifImageUrl;
    }
}