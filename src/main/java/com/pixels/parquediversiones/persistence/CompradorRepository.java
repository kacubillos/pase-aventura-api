package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.domain.Customer;
import com.pixels.parquediversiones.domain.repository.CustomerRepository;
import com.pixels.parquediversiones.persistence.crud.CompradorCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Comprador;
import com.pixels.parquediversiones.persistence.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompradorRepository implements CustomerRepository {

    @Autowired
    private CompradorCrudRepository compradorCrudRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> getAll() {
        List<Comprador> compradores =(List<Comprador>) compradorCrudRepository.findAll();
        return customerMapper.toCustomers(compradores);
    }

    @Override
    public Optional<Customer> getCustomer(int customerId) {
        return compradorCrudRepository.findById(customerId).map(comprador -> customerMapper.toCustomer(comprador));
    }

    @Override
    public Customer save(Customer customer) {
        Comprador comprador = customerMapper.toComprador(customer);
        return customerMapper.toCustomer(compradorCrudRepository.save(comprador));
    }

    @Override
    public void delete(int customerId) {
        compradorCrudRepository.deleteById(customerId);
    }


}
