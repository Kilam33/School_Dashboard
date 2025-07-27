package com.school.dashboard.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;

@Embeddable
public class AttendanceData {
    
    @Min(value = 0, message = "Present count cannot be negative")
    private int present;
    
    @Min(value = 0, message = "Absent count cannot be negative")
    private int absent;
    
    public AttendanceData() {}
    
    public AttendanceData(int present, int absent) {
        this.present = present;
        this.absent = absent;
    }
    
    public int getPresent() {
        return present;
    }
    
    public void setPresent(int present) {
        this.present = present;
    }
    
    public int getAbsent() {
        return absent;
    }
    
    public void setAbsent(int absent) {
        this.absent = absent;
    }
    
    public int getTotal() {
        return present + absent;
    }
}