package com.josuegomezjgj.employee.dto;

import com.josuegomezjgj.employee.EmployeeStatus;

public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Integer projectId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getProjectId() {
        return projectId;
    }
}
