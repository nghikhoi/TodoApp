package com.example.todoapp.database;

import java.util.Date;
import java.util.UUID;

public class TodoTask {

    private UUID id;
    private String title;
    private Date startTime;
    private Date  endTime;
    private String description;

    public TodoTask(UUID id, String title, Date startTime, Date endTime, String description) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }
    public TodoTask(String title, Date startTime, Date endTime, String description) {
        this(UUID.randomUUID(), title, startTime, endTime, description);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
