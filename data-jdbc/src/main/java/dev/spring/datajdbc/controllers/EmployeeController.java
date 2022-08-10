package dev.spring.datajdbc.controllers;

import dev.spring.datajdbc.models.entities.Employee;
import dev.spring.datajdbc.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity
                .ok()
                .body(employeeService.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return ResponseEntity
                .ok()
                .body(employeeService.fetchOne(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee request){
        return new ResponseEntity<>(employeeService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.delete(id),HttpStatus.OK);
    }
}
