package org.demo.schedule;

import java.time.LocalTime;

public class Task {
    private String nom_task;
    private LocalTime duration;
    private LocalTime recurrence;
    private LocalTime hour;
    private int numberOfReminder;
    private String type;
    private String childId;
    private String dayOfTheWeek;

    public Task(String nom_task) {
        this.nom_task = nom_task;
    }

    public Task(String nom_task, LocalTime duration, LocalTime recurrence, LocalTime hour, int numberOfReminder, String type, String childId, String dayOfTheWeek) {
        this.nom_task = nom_task;
        this.duration = duration;
        this.recurrence = recurrence;
        this.hour = hour;
        this.numberOfReminder = numberOfReminder;
        this.type = type;
        this.childId = childId;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getNom_task() {
        return nom_task;
    }
}
