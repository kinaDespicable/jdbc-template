package dev.spring.datajdbc.models.mappers;

import dev.spring.datajdbc.models.entities.Department;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setId(rs.getLong("id"));
        department.setDepartmentName(rs.getString("department_name"));
        department.setDepartmentCode(rs.getString("department_code"));
        return department;
    }
}
