package org.demo.schedule;

import java.time.LocalTime;

public class Task {
    private String name_task;
    private LocalTime duration;
    private LocalTime recurrence;
    private LocalTime hour;
    private int numberOfReminder;
    private String type;
    private String childId;
    private String dayOfTheWeek;

    public String getName_task() {
        return name_task;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public LocalTime getRecurrence() {
        return recurrence;
    }

    public LocalTime getHour() {
        return hour;
    }

    public int getNumberOfReminder() {
        return numberOfReminder;
    }

    public String getType() {
        return type;
    }

    public String getChildId() {
        return childId;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public Task(String nom_task) {
        this.name_task = nom_task;
    }

    public Task(String nom_task, LocalTime duration, LocalTime recurrence, LocalTime hour, int numberOfReminder, String type, String childId, String dayOfTheWeek) {
        this.name_task = nom_task;
        this.duration = duration;
        this.recurrence = recurrence;
        this.hour = hour;
        this.numberOfReminder = numberOfReminder;
        this.type = type;
        this.childId = childId;
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
