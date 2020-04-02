package org.demo.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private String name_task;
    private String duration;
    private String recurrence;
    private String hour;
    private int numberOfReminder;
    private String reminderInterval;
    private String type;
    private String childId;
    private String dayOfTheWeek;
    private String date;

    public String getName_task() {
        return name_task;
    }

    public String getDuration() {
        return duration;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public String getHour() {
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

    public String getReminderInterval() { return reminderInterval; }

    public void setReminderInterval(String reminderInterval) { this.reminderInterval = reminderInterval; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName_task(String name_task) {
        this.name_task = name_task;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public void setHour(String hour) {
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

    public Task(String nom_task, String duration, String recurrence, String hour, int numberOfReminder, String reminderInterval,String type, String childId, String dayOfTheWeek, String date) {
        this.name_task = nom_task;
        this.duration = duration;
        this.recurrence = recurrence;
        this.hour = hour;
        this.numberOfReminder = numberOfReminder;
        this.reminderInterval = reminderInterval;
        this.type = type;
        this.childId = childId;
        this.dayOfTheWeek = dayOfTheWeek;
        this.date = date;
    }
}
