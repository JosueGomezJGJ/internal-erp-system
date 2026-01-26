package com.josuegomezjgj.employee;

import com.josuegomezjgj.employee.dto.EmployeeRequest;
import com.josuegomezjgj.employee.dto.EmployeeResponse;
import com.josuegomezjgj.employee.dto.EmployeeUpdateRequest;
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
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

        return mapToResponse(employee);
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        validateEmployeeRequest(request);

        Project project = null;
        if (request.getProjectId() != null) {
            project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project", request.getProjectId()));
        }

        Employee employee = new Employee(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                project
        );

        Employee saved = employeeRepository.save(employee);
        return mapToResponse(saved);
    }

    public EmployeeResponse updateEmployee(Integer id, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

        if (request.getFirstName() != null && !request.getFirstName().trim().isEmpty()) {
            employee.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null && !request.getLastName().trim().isEmpty()) {
            employee.setLastName(request.getLastName());
        }

        if (request.getEmail() != null && !request.getEmail().trim().isEmpty()) {
            employee.setEmail(request.getEmail());
        }

        if (request.getStatus() != null) {
            employee.setStatus(request.getStatus());
        }

        if (request.wasProjectIdProvided()) {
            if (request.getProjectId() == null) {
                employee.setProject(null);
            } else {
                Project project = projectRepository.findById(request.getProjectId())
                        .orElseThrow(() -> new ResourceNotFoundException("Project", request.getProjectId()));
                employee.setProject(project);
            }
        }

        Employee updated = employeeRepository.save(employee);
        return mapToResponse(updated);
    }

    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

        employeeRepository.delete(employee);
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        Integer projectId = null;

        if (employee.getProject() != null) {
            projectId = employee.getProject().getId();
        }

        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getStatus(),
                projectId
        );
    }

    private void validateEmployeeRequest(EmployeeRequest request) {
        if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
            throw new BadRequestException("First name is required");
        }
        if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
            throw new BadRequestException("Last name is required");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new BadRequestException("Email is required");
        }
    }
}