package com.employee.EmployeeManagementSystem.repository;

import com.employee.EmployeeManagementSystem.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Custom query with @Query annotation
    @Query("SELECT d FROM Department d WHERE d.name = :name")
    Department findDepartmentByName(@Param("name") String name);
}
