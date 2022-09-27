package dev.spring.simpleCrud.service.implmentation;

import com.github.javafaker.Faker;
import dev.spring.simpleCrud.exception.exceptions.ResourceAlreadyExistException;
import dev.spring.simpleCrud.exception.exceptions.ResourceNotFoundException;
import dev.spring.simpleCrud.model.dto.request.NewEmployeeRequest;
import dev.spring.simpleCrud.model.dto.response.DeleteResponse;
import dev.spring.simpleCrud.model.entity.Employee;
import dev.spring.simpleCrud.repository.EmployeeRepository;
import dev.spring.simpleCrud.service.DepartmentService;
import dev.spring.simpleCrud.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

import static dev.spring.simpleCrud.model.enums.Deleted.DELETED;
import static dev.spring.simpleCrud.model.enums.Deleted.NOT_DELETED;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Override
    @Transactional
    public Employee add(NewEmployeeRequest request) {
        if(employeeRepository.existsByEmail(request.getEmail()))
            throw new ResourceAlreadyExistException("Email already exist.");

        var department = departmentService.fetchByName(request.getDepartmentName());
        var e = Employee.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .department(department)
                .build();
        return employeeRepository.save(e);
    }

    @Override
    public Employee fetchById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id: "+id+" not found."));
    }

    @Override
    public Page<Employee> fetchAll(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Employee> fetchAll(int page, int size, Optional<String> field) {
        var f = field.orElse("id");
        return employeeRepository.findAll(PageRequest.of(page,size, Sort.Direction.ASC,f));
    }

    @Override
    public Employee fetchByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with email: "+email+" not found."));
    }

    @Override
    public DeleteResponse deleteById(Long id) {
        if(!employeeRepository.existsById(id))
            return DeleteResponse.builder()
                    .timestamp(Instant.now())
                    .deleted(false)
                    .deletedPhrase(NOT_DELETED.getPhrase())
                    .deletedObject(null)
                    .build();

        var employee = fetchById(id);
        employeeRepository.deleteById(id);
        return DeleteResponse.builder()
                .timestamp(Instant.now())
                .deleted(true)
                .deletedPhrase(DELETED.getPhrase())
                .deletedObject(employee)
                .build();
    }

    @Override
    public Object addNEmployee(Integer amount) {
        Faker faker = new Faker();
        var department = departmentService.fetchByName("Administration");
        for (int i = 0; i < amount; i++) {
            var employee = Employee.builder()
                    .email(faker.internet().emailAddress())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .department(department)
                    .build();
            employeeRepository.save(employee);
        }
        return true;
    }

    @Override
    @Transactional
    public Employee update(Long id, NewEmployeeRequest request) {
        var e = fetchById(id);
        if(Objects.nonNull(request.getEmail()) && !request.getEmail().isBlank()){
            e.setEmail(request.getEmail());
        }
        if(Objects.nonNull(request.getFirstName()) && !request.getFirstName().isBlank()){
            e.setFirstName(request.getFirstName());
        }
        if(Objects.nonNull(request.getLastName()) && !request.getLastName().isBlank()){
            e.setLastName(request.getLastName());
        }
        if(Objects.nonNull(request.getDepartmentName()) && !request.getDepartmentName().isBlank()){
            var d = departmentService.fetchByName(request.getDepartmentName());
            e.setDepartment(d);
        }
        return employeeRepository.save(e);
    }
}
