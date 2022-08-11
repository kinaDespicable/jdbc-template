package dev.spring.datajdbc.services;

import dev.spring.datajdbc.models.entities.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> fetchAll();

    Department fetchOne(Long id);

    Department create(Department department);
}
