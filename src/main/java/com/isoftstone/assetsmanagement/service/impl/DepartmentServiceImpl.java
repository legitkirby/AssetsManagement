package com.isoftstone.assetsmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoftstone.assetsmanagement.entity.Department;
import com.isoftstone.assetsmanagement.mapper.DepartmentMapper;
import com.isoftstone.assetsmanagement.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> findAllDepartments() {
        return departmentMapper.selectList(null);
    }

    @Override
    public Department findDepartmentById(Integer id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public Department findDepartmentByCode(String departmentCode) {
        return departmentMapper.findByCode(departmentCode);
    }

    @Override
    public List<Department> findActiveDepartments() {
        return departmentMapper.findAllActiveDepartments();
    }

    @Override
    public List<Department> findDepartmentsByManager(Integer managerId) {
        return departmentMapper.findDepartmentsByManager(managerId);
    }

    @Override
    public int addDepartment(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentMapper.updateById(department);
    }

    @Override
    public int deleteDepartmentById(Integer id) {
        return departmentMapper.deleteById(id);
    }

    @Override
    public int changeDepartmentStatus(Integer id, String status) {
        Department department = departmentMapper.selectById(id);
        if (department != null) {
            department.setStatus(status);
            return departmentMapper.updateById(department);
        }
        return 0;
    }
}