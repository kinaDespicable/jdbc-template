package dev.spring.simpleCrud.service;

import dev.spring.simpleCrud.model.dto.request.NewEmployeeRequest;
import dev.spring.simpleCrud.model.dto.response.DeleteResponse;
import dev.spring.simpleCrud.model.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EmployeeService {
    Employee add(NewEmployeeRequest request);

    Employee fetchById(Long id);

    Page fetchAll(int page, int size);
    Page fetchAll(int page, int size, Optional<String> field);

    Employee fetchByEmail(String email);

    DeleteResponse deleteById(Long id);

    Object addNEmployee(Integer amount);

    Employee update(Long id, NewEmployeeRequest request);
}
