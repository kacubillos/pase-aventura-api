package com.pixels.parquediversiones.web.controller;

import com.pixels.parquediversiones.domain.Employee;
import com.pixels.parquediversiones.domain.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") int employeeId) {
        return employeeService.getById(employeeId)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<List<Employee>> getByRole(@PathVariable("id") int roleId) {
        return employeeService.getByRole(roleId)
                .map(employees -> new ResponseEntity<>(employees, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        if(employeeService.getByDocumentNum(employee.getDocumentNum()).isEmpty())
            return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
        else
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        return employeeService.getById(employee.getEmployeeId())
                .map(emp -> new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int employeeId) {
        if(employeeService.delete(employeeId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
