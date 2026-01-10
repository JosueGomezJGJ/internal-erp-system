package com.josuegomezjgj.employee;

import com.josuegomezjgj.employee.dto.EmployeeRequest;
import com.josuegomezjgj.employee.dto.EmployeeResponse;
import com.josuegomezjgj.exception.BadRequestException;
import com.josuegomezjgj.exception.ResourceNotFoundException;
import com.josuegomezjgj.project.Project;
import com.josuegomezjgj.project.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> new EmployeeResponse(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getStatus(),
                        employee.getProject().getId(),
                        employee.getProject().getName()
                )).collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(Integer id) {
        Employee employee =  employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getStatus(),
                employee.getProject().getId(),
                employee.getProject().getName()
        );
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {

        if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
            throw new BadRequestException("First name is required.");
        }

        if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
            throw new BadRequestException("Last name is required.");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new BadRequestException("Email is required.");
        }

        if (request.getProjectId() == null) {
            throw new BadRequestException("Project ID is required.");
        }

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project", request.getProjectId()));

        Employee employee = new Employee(null, request.getFirstName(), request.getLastName(), project, request.getEmail());

        Employee saved = employeeRepository.save(employee);

        return new EmployeeResponse(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getEmail(),
                saved.getStatus(),
                project.getId(),
                project.getName()
        );
    }
}
