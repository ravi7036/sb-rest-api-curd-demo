package com.demo.service.impl;

import com.demo.entity.Employee;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.EmployeeRepository;
import com.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.employeeRepository = repo;
    }

    @Override
    public void createEmployee(Employee emp) {
        employeeRepository.save(emp);
    }

    @Override
    public Optional<Employee> getEmployee(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Resource not found with id: " + id));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Resource not found with id: " + id));
        employeeRepository.delete(employee);
        return employee;
    }
}
