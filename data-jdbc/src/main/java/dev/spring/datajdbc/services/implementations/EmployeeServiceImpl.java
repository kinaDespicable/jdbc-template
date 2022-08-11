package dev.spring.datajdbc.services.implementations;

import dev.spring.datajdbc.dao.DAO;
import dev.spring.datajdbc.dao.implementations.EmployeeDAO;
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

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO dao) {
        this.employeeDAO = dao;
    }

    @Override
    public List<Employee> fetchAll() {
        return employeeDAO.fetchAll();
    }

    @Override
    public Employee fetchOne(Long id) {
        return employeeDAO.fetchOne(id)
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

        if(employeeDAO.updateById(id,fetchedEmployee) != 1){
            throw new ResourceUpdateFailedException("Update with PATCH request has been failed. ID: {"+id+"}");
        }
        return fetchOne(id);
    }

    @Override
    public Boolean delete(Long id) {
        return employeeDAO.deleteById(id) == 1;
    }

    @Override
    public Employee create(Employee requestBody) {
        employeeDAO.create(requestBody);
        return employeeDAO.fetchByFirstNameAndLastName(requestBody.getFirstName(), requestBody.getLastName())
                .orElseThrow(()-> new ResourceNotFoundException("Employee creation has been failed"));
    }
}
