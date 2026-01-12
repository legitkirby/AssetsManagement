package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    @Select("SELECT * FROM departments WHERE department_code = #{departmentCode}")
    Department findByCode(String departmentCode);

    @Select("SELECT * FROM departments WHERE manager_id = #{managerId}")
    List<Department> findDepartmentsByManager(Integer managerId);

    @Select("SELECT * FROM departments WHERE status = 'ACTIVE'")
    List<Department> findAllActiveDepartments();
}