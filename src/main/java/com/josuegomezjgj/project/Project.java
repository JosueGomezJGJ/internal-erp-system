package com.josuegomezjgj.project;

import com.josuegomezjgj.employee.EmployeeStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Project() {}

    public Project(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = ProjectStatus.PLANNED;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(code, project.code) && status == project.status && Objects.equals(startDate, project.startDate) && Objects.equals(endDate, project.endDate) && Objects.equals(createdAt, project.createdAt) && Objects.equals(updatedAt, project.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, status, startDate, endDate, createdAt, updatedAt);
    }
}
