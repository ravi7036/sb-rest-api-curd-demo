package com.demo.service;

import com.demo.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    void createDepartment(Department dept);
    Optional<Department> getDepartment(long id);

    List<Department> getDepartments();

    Department updateDepartment(Department employee);

    Department deleteDepartment(long id);
}
