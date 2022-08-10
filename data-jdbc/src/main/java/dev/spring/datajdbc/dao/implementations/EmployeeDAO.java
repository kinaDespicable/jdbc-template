package dev.spring.datajdbc.dao.implementations;

import dev.spring.datajdbc.dao.DAO;
import dev.spring.datajdbc.models.entities.Employee;
import dev.spring.datajdbc.models.mappers.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDAO implements DAO<Employee> {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> fetchAll() {

        String sql = "SELECT * FROM employees";

        return jdbcTemplate.query(sql, new EmployeeMapper());
    }

    @Override
    public Optional<Employee> fetchOne(Long id) {
        String sql = "SELECT * FROM employees WHERE id=?";
        return jdbcTemplate.query(sql, new EmployeeMapper(), id).stream().findAny();
    }

    @Override
    public int updateById(Long id, Employee requestBody) {
        String sql = "UPDATE employees SET first_name=?, last_name=?, department_id=? WHERE id=?";
        int update = jdbcTemplate.update(sql,
                requestBody.getFirstName(),
                requestBody.getLastName(),
                requestBody.getDepartmentId(),
                id);
        return update;
    }

    @Override
    public Boolean deleteById(Long id) {
        String sql = "DELETE FROM employees WHERE id=?";
        return jdbcTemplate.update(sql, id) == 1 ;
    }
}
