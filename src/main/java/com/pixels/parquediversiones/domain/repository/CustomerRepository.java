package com.pixels.parquediversiones.domain.repository;

import com.pixels.parquediversiones.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> getAll();
    Optional<Customer> getCustomer(int customerId);
    Customer save(Customer customer);
    void delete(int customerId);

}
