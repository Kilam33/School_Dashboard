package com.school.dashboard.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;

@Embeddable
public class BehaviorReport {
    
    @Embedded
    @Valid
    private BehaviorData middleSchool;
    
    @Embedded
    @Valid
    private BehaviorData elementary;
    
    public BehaviorReport() {
        this.middleSchool = new BehaviorData();
        this.elementary = new BehaviorData();
    }
    
    public BehaviorReport(BehaviorData middleSchool, BehaviorData elementary) {
        this.middleSchool = middleSchool;
        this.elementary = elementary;
    }
    
    public BehaviorData getMiddleSchool() {
        return middleSchool;
    }
    
    public void setMiddleSchool(BehaviorData middleSchool) {
        this.middleSchool = middleSchool;
    }
    
    public BehaviorData getElementary() {
        return elementary;
    }
    
    public void setElementary(BehaviorData elementary) {
        this.elementary = elementary;
    }
}