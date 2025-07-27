package com.school.dashboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateRequest {
    
    @NotBlank(message = "Update type is required")
    private String type;
    
    @NotNull(message = "Update data is required")
    private Object data;
    
    public UpdateRequest() {}
    
    public UpdateRequest(String type, Object data) {
        this.type = type;
        this.data = data;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
}