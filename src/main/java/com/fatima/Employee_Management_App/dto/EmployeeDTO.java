package com.fatima.Employee_Management_App.dto;


import com.fatima.Employee_Management_App.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String name;
    private String email;
    private String role;
    private String department;

    public EmployeeDTO(Employee employee) {
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.role = employee.getRole();
        this.department = employee.getDepartment();
    }
}

