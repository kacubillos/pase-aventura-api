package com.pixels.parquediversiones.domain.service;

import com.pixels.parquediversiones.domain.Employee;
import com.pixels.parquediversiones.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    public Optional<Employee> getById(int employeeId) {
        return employeeRepository.getEmployee(employeeId);
    }

    public Optional<Employee> getByDocumentNum(int documentNum) {
        return employeeRepository.getByDocumentNum(documentNum);
    }

    public Optional<List<Employee>> getByRole(int roleId) {
        return employeeRepository.getByRole(roleId);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean delete(int employeeId) {
        return employeeRepository.getEmployee(employeeId).map(employee -> {
           employeeRepository.delete(employeeId);
           return true;
        }).orElse(false);
    }
}
