package dev.spring.datajdbc.services.implementations;

import dev.spring.datajdbc.dao.DAO;
import dev.spring.datajdbc.exceptions.ResourceNotFoundException;
import dev.spring.datajdbc.exceptions.ResourceUpdateFailedException;
import dev.spring.datajdbc.models.entities.Employee;
import dev.spring.datajdbc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final DAO<Employee> dao;

    @Autowired
    public EmployeeServiceImpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Employee> fetchAll() {
        return dao.fetchAll();
    }

    @Override
    public Employee fetchOne(Long id) {
        return dao.fetchOne(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Employee with id: {"+id+"} not found.")
                );
    }

    @Override
    public Employee update(Long id, Employee request) {

        Employee fetchedEmployee = fetchOne(id);

        if(Objects.nonNull(request.getFirstName()) && !"".equalsIgnoreCase(request.getFirstName())){
            fetchedEmployee.setFirstName(request.getFirstName());
        }
        if(Objects.nonNull(request.getLastName()) && !"".equalsIgnoreCase(request.getLastName())){
            fetchedEmployee.setLastName(request.getLastName());
        }
        if(Objects.nonNull(request.getDepartmentId()) ){
            fetchedEmployee.setDepartmentId(request.getDepartmentId());
        }

        if(dao.updateById(id,fetchedEmployee) != 1){
            throw new ResourceUpdateFailedException("Update with PATCH request has been failed. ID: {"+id+"}");
        }
        return fetchOne(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dao.deleteById(id);
    }
}
