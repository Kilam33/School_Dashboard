package com.school.dashboard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "events")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Event title is required")
    @Size(max = 100, message = "Event title cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String title;
    
    @NotBlank(message = "Event date is required")
    @Size(max = 50, message = "Event date cannot exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String date;
    
    @NotBlank(message = "Event time is required")
    @Size(max = 20, message = "Event time cannot exceed 20 characters")
    @Column(nullable = false, length = 20)
    private String time;
    
    @Column(name = "display_order")
    private Integer displayOrder;
    
    public Event() {}
    
    public Event(String title, String date, String time) {
        this.title = title;
        this.date = date;
        this.time = time;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
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
    
    public Integer getDisplayOrder() {
        return displayOrder;
    }
    
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}