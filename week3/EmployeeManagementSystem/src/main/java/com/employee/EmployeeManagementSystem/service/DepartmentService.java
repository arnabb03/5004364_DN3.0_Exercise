package com.employee.EmployeeManagementSystem.service;

import com.employee.EmployeeManagementSystem.Department;
import com.employee.EmployeeManagementSystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Page<Department> getDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }
}
