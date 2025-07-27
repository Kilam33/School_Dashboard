package com.school.dashboard.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;

@Embeddable
public class BehaviorData {
    
    @Min(value = 0, message = "Positive count cannot be negative")
    private int positive;
    
    @Min(value = 0, message = "Negative count cannot be negative")
    private int negative;
    
    public BehaviorData() {}
    
    public BehaviorData(int positive, int negative) {
        this.positive = positive;
        this.negative = negative;
    }
    
    public int getPositive() {
        return positive;
    }
    
    public void setPositive(int positive) {
        this.positive = positive;
    }
    
    public int getNegative() {
        return negative;
    }
    
    public void setNegative(int negative) {
        this.negative = negative;
    }
}