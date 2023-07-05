package com.pixels.parquediversiones.domain.repository;

import com.pixels.parquediversiones.domain.Sale;
import com.pixels.parquediversiones.domain.dto.CustomerTicketsResponse;
import com.pixels.parquediversiones.domain.dto.TotalSalesResponse;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    List<Sale> getAll();
    Optional<List<Sale>> getByCustomerId(int customerId);
    Optional<Sale> getSale(int saleId);
    Sale save(Sale sale);
    void delete(int saleId);
    Optional<TotalSalesResponse> getTotalSalesByYear(Integer year);
    Optional<TotalSalesResponse> getTotalSalesByMonth(Integer year, Integer month);
    Optional<TotalSalesResponse> getTotalSalesByDay(Integer year, Integer month, Integer day);
    Optional<List<CustomerTicketsResponse>> getTicketCustomerByYear(Integer year);
    Optional<List<CustomerTicketsResponse>> getTicketCustomerByMonth(Integer year, Integer month);
}
