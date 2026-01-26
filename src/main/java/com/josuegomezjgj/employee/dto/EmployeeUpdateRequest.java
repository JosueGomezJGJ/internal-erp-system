package com.josuegomezjgj.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.josuegomezjgj.employee.EmployeeStatus;

public class EmployeeUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private EmployeeStatus status;
    private Integer projectId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean projectIdWasProvided = false;

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public EmployeeStatus getStatus() { return status; }

    public Integer getProjectId() { return projectId; }

    public boolean wasProjectIdProvided() { return projectIdWasProvided; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setEmail(String email) { this.email = email; }

    public void setStatus(EmployeeStatus status) { this.status = status; }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
        this.projectIdWasProvided = true;
    }
}