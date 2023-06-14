package com.pixels.parquediversiones.domain.repository;

import com.pixels.parquediversiones.domain.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    List<Sale> getAll();
    Optional<List<Sale>> getByCustomerId(int customerId);
    Optional<Sale> getSale(int saleId);
    Sale save(Sale sale);
    void delete(int saleId);
}
