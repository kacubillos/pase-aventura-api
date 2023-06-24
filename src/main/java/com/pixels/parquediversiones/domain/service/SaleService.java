package com.pixels.parquediversiones.domain.service;

import com.pixels.parquediversiones.domain.Sale;
import com.pixels.parquediversiones.domain.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAll() {
        return saleRepository.getAll();
    }

    public Optional<List<Sale>> getByCustomer(int customerId) {
        return saleRepository.getByCustomerId(customerId);
    }

    public Optional<Sale> getSale(int saleId) {
        return saleRepository.getSale(saleId);
    }

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    public boolean delete(int saleId) {
        return saleRepository.getSale(saleId).map(sale -> {
            saleRepository.delete(saleId);
            return true;
        }).orElse(false);
    }
}
