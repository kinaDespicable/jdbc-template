package dev.spring.simpleCrud.controller;

import dev.spring.simpleCrud.model.dto.request.NewEmployeeRequest;
import dev.spring.simpleCrud.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody NewEmployeeRequest request){
        return new ResponseEntity<>(employeeService.add(request), HttpStatus.CREATED);
    }

    @PostMapping("/start/{amount}")
    public ResponseEntity<?> addNEmployee(@PathVariable Integer amount){
        return new ResponseEntity<>(employeeService.addNEmployee(amount), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.fetchById(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getByEmail(@RequestParam("email") String email){
        return new ResponseEntity<>(employeeService.fetchByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/p/{p}/s/{s}")
    public ResponseEntity<?> getAllPaging(@PathVariable("p") int page,
                                          @PathVariable("s") int size){
        return new ResponseEntity<>(employeeService.fetchAll(page,size),HttpStatus.OK);
    }

    @GetMapping("/p/{p}/s/{s}/{field}")
    public ResponseEntity<?> getAllPagingWithSorting(@PathVariable("p") int page,
                                                     @PathVariable("s") int size,
                                                     @PathVariable("field") Optional<String> field){
        return new ResponseEntity<>(employeeService.fetchAll(page,size, field),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody NewEmployeeRequest request){
        return new ResponseEntity<>(employeeService.update(id,request),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.deleteById(id), HttpStatus.OK);
    }
}
