package com.demo.service.impl;

import com.demo.entity.Department;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.DepartmentRepository;
import com.demo.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository repository){
        this.departmentRepository = repository;
    }

    @Override
    public void createDepartment(Department dept) {
        this.departmentRepository.save(dept);
    }

    @Override
    public Optional<Department> getDepartment(long id) {
        return this.departmentRepository.findById(id);
    }

    @Override
    public List<Department> getDepartments() {
        return this.departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department deleteDepartment(long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not found with id = " + id));
        departmentRepository.delete(department);
        return department;
    }
}
