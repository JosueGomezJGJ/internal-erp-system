package com.josuegomezjgj.employee.dto;

import com.josuegomezjgj.employee.EmployeeStatus;

public class EmployeeResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private EmployeeStatus status;
    private Integer projectId;

    public EmployeeResponse(
            Integer id,
            String firstName,
            String lastName,
            String email,
            EmployeeStatus status,
            Integer projectId
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.projectId = projectId;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public EmployeeStatus getStatus() { return status; }

    public Integer getProjectId() { return projectId; }
}
