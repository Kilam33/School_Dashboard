package com.school.dashboard.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dashboard_data")
public class DashboardData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "present", column = @Column(name = "staff_present")),
        @AttributeOverride(name = "absent", column = @Column(name = "staff_absent"))
    })
    @Valid
    private AttendanceData staffAttendance;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "present", column = @Column(name = "student_present")),
        @AttributeOverride(name = "absent", column = @Column(name = "student_absent"))
    })
    @Valid
    private AttendanceData studentAttendance;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "middleSchool.positive", column = @Column(name = "ms_positive")),
        @AttributeOverride(name = "middleSchool.negative", column = @Column(name = "ms_negative")),
        @AttributeOverride(name = "elementary.positive", column = @Column(name = "elem_positive")),
        @AttributeOverride(name = "elementary.negative", column = @Column(name = "elem_negative"))
    })
    @Valid
    private BehaviorReport behavior;
    
    @Min(value = 0, message = "School days left cannot be negative")
    @Column(name = "school_days_left")
    private int schoolDaysLeft;
    
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
    
    @Transient
    private List<Event> upcomingEvents;
    
    public DashboardData() {
        this.staffAttendance = new AttendanceData();
        this.studentAttendance = new AttendanceData();
        this.behavior = new BehaviorReport();
        this.lastUpdated = LocalDateTime.now();
    }
    
    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.lastUpdated = LocalDateTime.now();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public AttendanceData getStaffAttendance() {
        return staffAttendance;
    }
    
    public void setStaffAttendance(AttendanceData staffAttendance) {
        this.staffAttendance = staffAttendance;
    }
    
    public AttendanceData getStudentAttendance() {
        return studentAttendance;
    }
    
    public void setStudentAttendance(AttendanceData studentAttendance) {
        this.studentAttendance = studentAttendance;
    }
    
    public BehaviorReport getBehavior() {
        return behavior;
    }
    
    public void setBehavior(BehaviorReport behavior) {
        this.behavior = behavior;
    }
    
    public int getSchoolDaysLeft() {
        return schoolDaysLeft;
    }
    
    public void setSchoolDaysLeft(int schoolDaysLeft) {
        this.schoolDaysLeft = schoolDaysLeft;
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public List<Event> getUpcomingEvents() {
        return upcomingEvents;
    }
    
    public void setUpcomingEvents(List<Event> upcomingEvents) {
        this.upcomingEvents = upcomingEvents;
    }
}