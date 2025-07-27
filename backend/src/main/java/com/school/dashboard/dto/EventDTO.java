package com.school.dashboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EventDTO {
    
    private String id;
    
    @NotBlank(message = "Event title is required")
    @Size(max = 100, message = "Event title cannot exceed 100 characters")
    private String title;
    
    @NotBlank(message = "Event date is required")
    @Size(max = 50, message = "Event date cannot exceed 50 characters")
    private String date;
    
    @NotBlank(message = "Event time is required")
    @Size(max = 20, message = "Event time cannot exceed 20 characters")
    private String time;
    
    public EventDTO() {}
    
    public EventDTO(String id, String title, String date, String time) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
}