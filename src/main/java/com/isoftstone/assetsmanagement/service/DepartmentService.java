package com.isoftstone.assetsmanagement.service;

import com.isoftstone.assetsmanagement.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllDepartments();
    Department findDepartmentById(Integer id);
    Department findDepartmentByCode(String departmentCode);
    List<Department> findActiveDepartments();
    List<Department> findDepartmentsByManager(Integer managerId);
    int addDepartment(Department department);
    int updateDepartment(Department department);
    int deleteDepartmentById(Integer id);
    int changeDepartmentStatus(Integer id, String status);
}