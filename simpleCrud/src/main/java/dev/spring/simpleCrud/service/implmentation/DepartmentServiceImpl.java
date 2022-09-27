package dev.spring.simpleCrud.service.implmentation;

import dev.spring.simpleCrud.exception.exceptions.ResourceAlreadyExistException;
import dev.spring.simpleCrud.exception.exceptions.ResourceNotFoundException;
import dev.spring.simpleCrud.model.dto.request.NewDepartmentRequest;
import dev.spring.simpleCrud.model.entity.Department;
import dev.spring.simpleCrud.repository.DepartmentRepository;
import dev.spring.simpleCrud.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department fetchByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(()-> new ResourceNotFoundException("Department with name "+departmentName+" not found."));
    }

    @Override
    public Department add(@Valid NewDepartmentRequest request) {
        if(departmentRepository.existsByDepartmentName(request.getDepartmentName()))
            throw new ResourceAlreadyExistException("Department with name "+request.getDepartmentName()+" already exist.");

        var department = Department.builder()
                .name(request.getDepartmentName())
                .code(request.getDepartmentCode().toUpperCase())
                .build() ;
        return departmentRepository.save(department);
    }

    @Override
    public Page<Department> fetchAll(Optional<Integer> p, Optional<Integer> s) {
        var page = p.orElse(0);
        var size = s.orElse(0);
        return departmentRepository.findAll(PageRequest.of(page, size));
    }

}
