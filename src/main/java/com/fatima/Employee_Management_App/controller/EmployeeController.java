package com.fatima.Employee_Management_App.controller;

import com.fatima.Employee_Management_App.dto.EmployeeDTO;
import com.fatima.Employee_Management_App.model.Employee;
import com.fatima.Employee_Management_App.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        log.info("Creating Employee: " + employee);
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Fetching all employees");
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        log.info("Fetching employee with id: " + id);
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        log.info("Updating employee with id: " + id);
        return ResponseEntity.ok(employeeService.updateEmployee(id, updatedEmployee));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
    }

    @PostMapping("/{employeeId}/skills/{skillId}")
    public ResponseEntity<String> addSkillToEmployee(@PathVariable Long employeeId, @PathVariable Long skillId) {
        log.info("Adding skill with ID: {} to employee with ID: {}", skillId, employeeId);
        employeeService.addSkill(employeeId, skillId);
        return ResponseEntity.ok("Skill added to employee successfully");
    }

    @DeleteMapping("/{employeeId}/skills/{skillId}")
    public ResponseEntity<String> removeSkillFromEmployee(@PathVariable Long employeeId, @PathVariable Long skillId) {
        employeeService.removeSkill(employeeId, skillId);
        return ResponseEntity.ok("Skill removed from employee successfully");
    }

    @PostMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<String> assignProjectToEmployee(@PathVariable Long employeeId, @PathVariable Long projectId) {
        log.info("Assigning project with ID: {} to employee with ID: {}", projectId, employeeId);
        employeeService.assignProject(employeeId, projectId);
        return ResponseEntity.ok("Employee assigned to project successfully");
    }

    @DeleteMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<String> removeEmployeeFromProject(@PathVariable Long employeeId, @PathVariable Long projectId) {
        log.info("Removing employee with ID: {} from project with ID: {}", employeeId, projectId);
        employeeService.removeProject(employeeId, projectId);
        return ResponseEntity.ok("Employee removed from project successfully");
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<EmployeeDTO> getEmployeeProfile(@PathVariable Long id) {
        log.info("Fetching profile for employee with ID: {}", id);
        return ResponseEntity.ok(employeeService.getEmployeeProfile(id));
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<Employee> updateEmployeeProfile(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        log.info("Updating profile for employee with ID: {}", id);
        return ResponseEntity.ok(employeeService.updateEmployeeProfile(id, employeeDTO));
    }






}
