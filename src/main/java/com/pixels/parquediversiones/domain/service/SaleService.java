package com.pixels.parquediversiones.domain.service;

import com.pixels.parquediversiones.domain.Sale;
import com.pixels.parquediversiones.domain.dto.CustomerTicketsResponse;
import com.pixels.parquediversiones.domain.dto.TotalSalesResponse;
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

    public Optional<TotalSalesResponse> getTotalSalesByYear(Integer year) {
        return saleRepository.getTotalSalesByYear(year);
    }

    public Optional<TotalSalesResponse> getTotalSalesByMonth(Integer year, Integer month) {
        return saleRepository.getTotalSalesByMonth(year, month);
    }

    public Optional<TotalSalesResponse> getTotalSalesByDay(Integer year, Integer month, Integer day) {
        return saleRepository.getTotalSalesByDay(year, month, day);
    }

    public Optional<List<CustomerTicketsResponse>> getTotalTicketsCustomerByYear(Integer year) {
        return saleRepository.getTicketCustomerByYear(year);
    }

    public Optional<List<CustomerTicketsResponse>> getTotalTicketsCustomerByMonth(Integer year, Integer month) {
        return saleRepository.getTicketCustomerByMonth(year, month);
    }
}
