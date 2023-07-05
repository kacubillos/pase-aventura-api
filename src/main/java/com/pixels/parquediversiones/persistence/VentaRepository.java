package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.domain.Sale;
import com.pixels.parquediversiones.domain.dto.CustomerTicketsResponse;
import com.pixels.parquediversiones.domain.dto.TotalSalesResponse;
import com.pixels.parquediversiones.domain.repository.SaleRepository;
import com.pixels.parquediversiones.persistence.crud.VentaCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Venta;
import com.pixels.parquediversiones.persistence.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VentaRepository implements SaleRepository {
    @Autowired
    private VentaCrudRepository ventaCrudRepository;
    @Autowired
    private SaleMapper mapper;

    @Override
    public List<Sale> getAll() {
        List<Venta> ventas = (List<Venta>) ventaCrudRepository.findAll();
        return mapper.toSales(ventas);
    }

    @Override
    public Optional<List<Sale>> getByCustomerId(int customerId) {
        Optional<List<Venta>> ventas = ventaCrudRepository.findByIdComprador(customerId);
        return ventas.map(ventasAux -> mapper.toSales(ventasAux));
    }

    @Override
    public Optional<Sale> getSale(int saleId) {
        return ventaCrudRepository.findById(saleId).map(venta -> mapper.toSale(venta));
    }

    @Override
    public Sale save(Sale sale) {
        Venta venta = mapper.toVenta(sale);
        return mapper.toSale(ventaCrudRepository.save(venta));
    }

    @Override
    public void delete(int saleId) {
        ventaCrudRepository.deleteById(saleId);
    }

    @Override
    public Optional<TotalSalesResponse> getTotalSalesByYear(Integer year) {
        return ventaCrudRepository.getTotalSalesByYear(year);
    }

    @Override
    public Optional<TotalSalesResponse> getTotalSalesByMonth(Integer year, Integer month) {
        return ventaCrudRepository.getTotalSalesByMonth(year, month);
    }

    @Override
    public Optional<TotalSalesResponse> getTotalSalesByDay(Integer year, Integer month, Integer day) {
        return ventaCrudRepository.getTotalSalesByDay(year, month, day);
    }

    @Override
    public Optional<List<CustomerTicketsResponse>> getTicketCustomerByYear(Integer year) {
        return ventaCrudRepository.getTotalTicketsCustomerByYear(year);
    }

    @Override
    public Optional<List<CustomerTicketsResponse>> getTicketCustomerByMonth(Integer year, Integer month) {
        return ventaCrudRepository.getTotalTicketsCustomerByMonth(year, month);
    }
}
