package com.demo.controller;

import com.demo.entity.Department;
import com.demo.exception.ResourceNotFoundException;
import com.demo.service.DepartmentService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping
    public ResponseEntity<List<Department>> getAllEmployees() {
        final List<Department> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") long id) {
        Department department = departmentService.getDepartment(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not found with id = " + id));

        return new ResponseEntity<>(department, HttpStatus.OK);
    }


    @PostMapping
    public Department createDepartment(@RequestBody Department dept) {
        departmentService.createDepartment(dept);
        return dept;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department dept) {
        Department department = departmentService.getDepartment(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not found with id = " + id));
        return new ResponseEntity<>(departmentService.updateDepartment(dept), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") long id) {
        return new ResponseEntity<>(departmentService.deleteDepartment(id), HttpStatus.OK);
    }
}
