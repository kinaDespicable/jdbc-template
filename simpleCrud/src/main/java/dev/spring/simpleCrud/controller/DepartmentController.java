package dev.spring.simpleCrud.controller;

import dev.spring.simpleCrud.model.dto.request.NewDepartmentRequest;
import dev.spring.simpleCrud.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/new")
    public ResponseEntity<?> add(@RequestBody @Valid NewDepartmentRequest request){
        return new ResponseEntity<>(departmentService.add(request), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> all(@RequestParam("page") Optional<Integer> p,
                                 @RequestParam("size") Optional<Integer> s){
        return new ResponseEntity<>(departmentService.fetchAll(p,s), HttpStatus.OK);
    }
}
