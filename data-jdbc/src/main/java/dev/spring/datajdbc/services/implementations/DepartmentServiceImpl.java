package dev.spring.datajdbc.services.implementations;

import dev.spring.datajdbc.dao.implementations.DepartmentDAO;
import dev.spring.datajdbc.exceptions.ResourceNotFoundException;
import dev.spring.datajdbc.models.entities.Department;
import dev.spring.datajdbc.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDAO departmentDAO;

    @Autowired
    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public List<Department> fetchAll() {
        return departmentDAO.fetchAll();
    }

    @Override
    public Department fetchOne(Long id) {
        return departmentDAO.fetchOne(id).orElseThrow(()-> new ResourceNotFoundException("Department with id {"+id+"} not found"));
    }

    @Override
    @Transactional
    public Department create(Department department) {
       departmentDAO.create(department);
       return departmentDAO.fetchDepartmentByNameAndCode(department.getDepartmentName(), department.getDepartmentCode())
               .orElseThrow(()-> new ResourceNotFoundException(
                       "Department with name {"+department.getDepartmentName()+"} and code {"+department.getDepartmentCode()+"} not found"
               ));

    }

}
