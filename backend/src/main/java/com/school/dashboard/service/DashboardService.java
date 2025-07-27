package com.school.dashboard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.dashboard.dto.EventDTO;
import com.school.dashboard.dto.UpdateRequest;
import com.school.dashboard.model.*;
import com.school.dashboard.repository.DashboardDataRepository;
import com.school.dashboard.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class DashboardService {
    
    @Autowired
    private DashboardDataRepository dashboardRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    public DashboardData getDashboardData() {
        DashboardData data = dashboardRepository.findFirstByOrderByLastUpdatedDesc();
        if (data == null) {
            data = createDefaultDashboardData();
            data = dashboardRepository.save(data);
        }
        
        // Load events
        List<Event> events = eventRepository.findAllOrderedByDisplayOrder();
        data.setUpcomingEvents(events);
        
        return data;
    }
    
    public DashboardData updateDashboardData(UpdateRequest request) {
        DashboardData data = dashboardRepository.findFirstByOrderByLastUpdatedDesc();
        if (data == null) {
            data = createDefaultDashboardData();
        }
        
        switch (request.getType().toLowerCase()) {
            case "staff":
                updateStaffAttendance(data, request.getData());
                break;
            case "student":
                updateStudentAttendance(data, request.getData());
                break;
            case "behavior":
                updateBehaviorData(data, request.getData());
                break;
            case "events":
                updateEvents(request.getData());
                break;
            case "daysleft":
                updateSchoolDaysLeft(data, request.getData());
                break;
            default:
                throw new IllegalArgumentException("Invalid update type: " + request.getType());
        }
        
        data = dashboardRepository.save(data);
        
        // Load events for response
        List<Event> events = eventRepository.findAllOrderedByDisplayOrder();
        data.setUpcomingEvents(events);
        
        return data;
    }
    
    private void updateStaffAttendance(DashboardData data, Object attendanceData) {
        try {
            Map<String, Object> attendanceMap = objectMapper.convertValue(attendanceData, Map.class);
            int present = ((Number) attendanceMap.get("present")).intValue();
            int absent = ((Number) attendanceMap.get("absent")).intValue();
            data.setStaffAttendance(new AttendanceData(present, absent));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid staff attendance data format", e);
        }
    }
    
    private void updateStudentAttendance(DashboardData data, Object attendanceData) {
        try {
            Map<String, Object> attendanceMap = objectMapper.convertValue(attendanceData, Map.class);
            int present = ((Number) attendanceMap.get("present")).intValue();
            int absent = ((Number) attendanceMap.get("absent")).intValue();
            data.setStudentAttendance(new AttendanceData(present, absent));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid student attendance data format", e);
        }
    }
    
    private void updateBehaviorData(DashboardData data, Object behaviorData) {
        try {
            Map<String, Object> behaviorMap = objectMapper.convertValue(behaviorData, Map.class);
            
            Map<String, Object> msMap = (Map<String, Object>) behaviorMap.get("middleSchool");
            int msPositive = ((Number) msMap.get("positive")).intValue();
            int msNegative = ((Number) msMap.get("negative")).intValue();
            
            Map<String, Object> elemMap = (Map<String, Object>) behaviorMap.get("elementary");
            int elemPositive = ((Number) elemMap.get("positive")).intValue();
            int elemNegative = ((Number) elemMap.get("negative")).intValue();
            
            BehaviorData middleSchool = new BehaviorData(msPositive, msNegative);
            BehaviorData elementary = new BehaviorData(elemPositive, elemNegative);
            
            data.setBehavior(new BehaviorReport(middleSchool, elementary));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid behavior data format", e);
        }
    }
    
    private void updateEvents(Object eventsData) {
        try {
            List<Map<String, Object>> eventsList = objectMapper.convertValue(eventsData, List.class);
            
            // Clear existing events
            eventRepository.deleteAll();
            
            // Add new events
            for (int i = 0; i < eventsList.size(); i++) {
                Map<String, Object> eventMap = eventsList.get(i);
                Event event = new Event();
                event.setTitle((String) eventMap.get("title"));
                event.setDate((String) eventMap.get("date"));
                event.setTime((String) eventMap.get("time"));
                event.setDisplayOrder(i);
                eventRepository.save(event);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid events data format", e);
        }
    }
    
    private void updateSchoolDaysLeft(DashboardData data, Object daysLeftData) {
        try {
            int daysLeft = ((Number) daysLeftData).intValue();
            data.setSchoolDaysLeft(daysLeft);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid school days left data format", e);
        }
    }
    
    private DashboardData createDefaultDashboardData() {
        DashboardData data = new DashboardData();
        data.setStaffAttendance(new AttendanceData(42, 3));
        data.setStudentAttendance(new AttendanceData(387, 28));
        
        BehaviorData middleSchool = new BehaviorData(15, 3);
        BehaviorData elementary = new BehaviorData(22, 1);
        data.setBehavior(new BehaviorReport(middleSchool, elementary));
        
        data.setSchoolDaysLeft(127);
        
        // Create default events
        createDefaultEvents();
        
        return data;
    }
    
    private void createDefaultEvents() {
        if (eventRepository.count() == 0) {
            Event event1 = new Event("Science Fair", "Friday", "10:00 AM");
            event1.setDisplayOrder(0);
            eventRepository.save(event1);
            
            Event event2 = new Event("Parent-Teacher Conference", "Monday", "3:00 PM");
            event2.setDisplayOrder(1);
            eventRepository.save(event2);
            
            Event event3 = new Event("Spring Break", "March 15", "All Day");
            event3.setDisplayOrder(2);
            eventRepository.save(event3);
        }
    }
}