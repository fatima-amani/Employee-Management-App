package com.fatima.Employee_Management_App.service;

import com.fatima.Employee_Management_App.dto.EmployeeDTO;
import com.fatima.Employee_Management_App.exception.EmployeeNotFoundException;
import com.fatima.Employee_Management_App.model.Employee;
import com.fatima.Employee_Management_App.model.Project;
import com.fatima.Employee_Management_App.model.Skill;
import com.fatima.Employee_Management_App.repository.EmployeeRepository;
import com.fatima.Employee_Management_App.repository.ProjectRepository;
import com.fatima.Employee_Management_App.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Create Employee
    @CachePut(value="employee", key="#employee.id")
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get Employee By ID
    @Cacheable(value="employee", key="#id")
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    // Update Employee By ID
    @CachePut(value="employee", key="#employee.id")
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setRole(updatedEmployee.getRole());

        return employeeRepository.save(existingEmployee);
    }

    // Delete Employee By ID
    @CacheEvict(value="employee", key ="#id")
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
        log.info("Employee with ID {} deleted successfully", id);
    }

    public void addSkill(Long employeeId, Long skillId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        employee.getSkills().add(skill);
        employeeRepository.save(employee);
    }

    public void removeSkill(Long employeeId, Long skillId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        employee.getSkills().remove(skill);
        employeeRepository.save(employee);
    }

    public void assignProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        employee.getProjects().add(project);
        employeeRepository.save(employee);
    }

    public void removeProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        employee.getProjects().remove(project);
        employeeRepository.save(employee);
    }

    public EmployeeDTO getEmployeeProfile(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return new EmployeeDTO(employee);
    }

    public Employee updateEmployeeProfile(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setRole(employeeDTO.getRole());
        employee.setDepartment(employeeDTO.getDepartment());
        return employeeRepository.save(employee);
    }
}
