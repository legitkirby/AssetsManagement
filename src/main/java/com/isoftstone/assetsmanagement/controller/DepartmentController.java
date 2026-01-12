package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.Department;
import com.isoftstone.assetsmanagement.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @GetMapping("/all")
    public List<Department> findAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable Integer id) {
        return departmentService.findDepartmentById(id);
    }

    @GetMapping("/code/{code}")
    public Department findDepartmentByCode(@PathVariable String code) {
        return departmentService.findDepartmentByCode(code);
    }

    @GetMapping("/active")
    public List<Department> findActiveDepartments() {
        return departmentService.findActiveDepartments();
    }

    @GetMapping("/manager/{managerId}")
    public List<Department> findDepartmentsByManager(@PathVariable Integer managerId) {
        return departmentService.findDepartmentsByManager(managerId);
    }

    @PostMapping("/add")
    public int addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @PutMapping("/update")
    public int updateDepartment(@RequestBody Department department) {
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteDepartmentById(@PathVariable Integer id) {
        return departmentService.deleteDepartmentById(id);
    }

    @PutMapping("/status/{id}")
    public int changeDepartmentStatus(@PathVariable Integer id, @RequestParam String status) {
        return departmentService.changeDepartmentStatus(id, status);
    }
}