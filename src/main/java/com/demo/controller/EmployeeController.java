package com.demo.controller;

import com.demo.entity.Department;
import com.demo.entity.Employee;
import com.demo.exception.ResourceNotFoundException;
import com.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        final List<Employee> allEmployees = this.employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
        final Employee employee = employeeService.getEmployee(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not found with given id: " + id));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee emp) {
        employeeService.createEmployee(emp);
        return emp;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee emp) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, emp), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {

        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }
}
