package com.pixels.parquediversiones.domain.repository;

import com.pixels.parquediversiones.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> getAll();
    Optional<List<Employee>> getByRole(int roleId);
    Optional<Employee> getEmployee(int employeeId);
    Employee save(Employee employee);
    void delete(int employeeId);
}
