package org.demo.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private String name_task;
    private LocalTime duration;
    private LocalTime recurrence;
    private LocalTime hour;
    private int numberOfReminder;
    private LocalTime reminderInterval;
    private String type;
    private String childId;
    private String dayOfTheWeek;
    private LocalDate date;

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

    public LocalTime getReminderInterval() { return reminderInterval; }

    public void setReminderInterval(LocalTime reminderInterval) { this.reminderInterval = reminderInterval; }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName_task(String name_task) {
        this.name_task = name_task;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public void setRecurrence(LocalTime recurrence) {
        this.recurrence = recurrence;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public void setNumberOfReminder(int numberOfReminder) {
        this.numberOfReminder = numberOfReminder;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Task(String nom_task) {
        this.name_task = nom_task;
    }

    public Task(String nom_task, LocalTime duration, LocalTime recurrence, LocalTime hour, int numberOfReminder, LocalTime reminderInterval,String type, String childId, String dayOfTheWeek) {
        this.name_task = nom_task;
        this.duration = duration;
        this.recurrence = recurrence;
        this.hour = hour;
        this.numberOfReminder = numberOfReminder;
        this.reminderInterval = reminderInterval;
        this.type = type;
        this.childId = childId;
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
