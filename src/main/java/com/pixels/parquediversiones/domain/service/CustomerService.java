package com.pixels.parquediversiones.domain.service;

import com.pixels.parquediversiones.domain.Customer;
import com.pixels.parquediversiones.domain.repository.CustomerRepository;
import com.pixels.parquediversiones.persistence.entity.Comprador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAll() {
        return customerRepository.getAll();
    }


    public Optional<Customer> getCustomer(int customerId) {
        return customerRepository.getCustomer(customerId);
    }


    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean delete(int customerId) {
        if(getCustomer(customerId).isPresent()){
            customerRepository.delete(customerId);
            return true;
        }else{
            return false;
        }
    }

}
