package dev.spring.simpleCrud.service;

import dev.spring.simpleCrud.model.dto.request.NewDepartmentRequest;
import dev.spring.simpleCrud.model.entity.Department;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DepartmentService {

    Department fetchByName(String departmentName);

    Department add(NewDepartmentRequest request);

    Page<Department> fetchAll(Optional<Integer> p, Optional<Integer> s);
}
