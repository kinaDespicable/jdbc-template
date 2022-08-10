package dev.spring.datajdbc.services;

import dev.spring.datajdbc.models.entities.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> fetchAll();

    Employee fetchOne(Long id);

    Employee update(Long id, Employee request);

    Boolean delete(Long id);
}
