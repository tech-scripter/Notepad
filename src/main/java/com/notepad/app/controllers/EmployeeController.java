package com.notepad.app.controllers;

import com.notepad.app.models.Employee;
import com.notepad.app.services.crud.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees(
             @RequestParam(defaultValue = "0") Integer pageNo,
             @RequestParam(defaultValue = "10") Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {

        List<Employee> list =  employeeService.getAllEmployees(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
