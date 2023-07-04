package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.domain.Employee;
import com.pixels.parquediversiones.domain.repository.EmployeeRepository;
import com.pixels.parquediversiones.persistence.crud.EmpleadoCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Empleado;
import com.pixels.parquediversiones.persistence.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmpleadoRepository implements EmployeeRepository {
    @Autowired
    private EmpleadoCrudRepository empleadoCrudRepository;
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public List<Employee> getAll() {
        List<Empleado> empleados = (List<Empleado>) empleadoCrudRepository.findAll();
        return mapper.toEmployees(empleados);
    }

    @Override
    public Optional<List<Employee>> getByRole(int roleId) {
        List<Empleado> empleados = empleadoCrudRepository.findByIdRol(roleId);
        return Optional.of(mapper.toEmployees(empleados));
    }

    @Override
    public Optional<Employee> getByDocumentNum(int documentNum) {
        return empleadoCrudRepository.findByNumeroDocumento(documentNum).map(empleado -> mapper.toEmployee(empleado));
    }

    @Override
    public Optional<Employee> getEmployee(int employeeId) {
        return empleadoCrudRepository.findById(employeeId).map(empleado -> mapper.toEmployee(empleado));
    }

    @Override
    public Employee save(Employee employee) {
        Empleado empleado = mapper.toEmpleado(employee);
        return mapper.toEmployee(empleadoCrudRepository.save(empleado));
    }

    @Override
    public void delete(int employeeId) {
        empleadoCrudRepository.deleteById(employeeId);
    }
}
