package dev.spring.datajdbc.dao.implementations;

import dev.spring.datajdbc.dao.DAO;
import dev.spring.datajdbc.models.entities.Department;
import dev.spring.datajdbc.models.mappers.DepartmentMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentDAO implements DAO<Department> {

    private final JdbcTemplate jdbcTemplate;
    private final DepartmentMapper departmentMapper;

    public DepartmentDAO(JdbcTemplate jdbcTemplate, DepartmentMapper departmentMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.departmentMapper = departmentMapper;
    }


    @Override
    public List<Department> fetchAll() {
        String sql = "SELECT * FROM departments";
        return jdbcTemplate.query(sql, departmentMapper);
    }

    @Override
    public Optional<Department> fetchOne(Long id) {
        String sql = "SELECT * FROM departments WHERE id=?";
        return jdbcTemplate.query(sql,departmentMapper,id).stream().findAny();
    }

    @Override
    public int updateById(Long id, Department requestBody) {
        String sql = "UPDATE departments SET department_name = ?, department_code = ? WHERE id=?";
        return jdbcTemplate.update(sql,requestBody.getDepartmentName(),requestBody.getDepartmentCode(), id);
    }

    @Override
    public int deleteById(Long id) {
        String sql = "DELETE FROM departments WHERE id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int create(Department requestBody) {
        String sql = "INSERT INTO departments(department_name, department_code) VALUES (?, ?)";
        return jdbcTemplate.update(sql,requestBody.getDepartmentName(),requestBody.getDepartmentCode());
    }

    public Optional<Department> fetchDepartmentByNameAndCode(String departmentName, String departmentCode){
        String sql = "SELECT * FROM departments WHERE department_name = ? AND department_code = ?";
        return jdbcTemplate.query(sql,departmentMapper, departmentName,departmentCode).stream().findAny();
    }
}
