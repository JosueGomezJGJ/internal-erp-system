package com.josuegomezjgj.employee.dto;

import com.josuegomezjgj.employee.EmployeeStatus;

public class EmployeeUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private EmployeeStatus status;
    private Integer projectId;

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public EmployeeStatus getStatus() { return status; }

    public Integer getProjectId() { return projectId; }
}
