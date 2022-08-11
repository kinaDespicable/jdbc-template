package dev.spring.datajdbc.controllers;

import dev.spring.datajdbc.models.entities.Department;
import dev.spring.datajdbc.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.create(department), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(departmentService.fetchAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.fetchOne(id),HttpStatus.OK);
    }

}
