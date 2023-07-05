package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.domain.dto.CustomerTicketsResponse;
import com.pixels.parquediversiones.domain.dto.TotalSalesResponse;
import com.pixels.parquediversiones.persistence.entity.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VentaCrudRepository extends CrudRepository<Venta, Integer> {
    Optional<List<Venta>> findByIdComprador(int idComprador);

    @Query("SELECT new com.pixels.parquediversiones.domain.dto.TotalSalesResponse(SUM(v.total)) " +
            "FROM Venta v " +
            "WHERE EXTRACT(YEAR FROM v.fecha) = :year ")
    Optional<TotalSalesResponse> getTotalSalesByYear(@Param("year") Integer year);

    @Query("SELECT new com.pixels.parquediversiones.domain.dto.TotalSalesResponse(SUM(v.total)) " +
            "FROM Venta v " +
            "WHERE EXTRACT(YEAR FROM v.fecha) = :year AND EXTRACT(MONTH FROM v.fecha) = :month ")
    Optional<TotalSalesResponse> getTotalSalesByMonth(@Param("year") Integer year, @Param("month") Integer month);

    @Query("SELECT new com.pixels.parquediversiones.domain.dto.TotalSalesResponse(SUM(v.total)) " +
            "FROM Venta v " +
            "WHERE EXTRACT(YEAR FROM v.fecha) = :year " +
            "AND EXTRACT(MONTH FROM v.fecha) = :month " +
            "AND EXTRACT(DAY FROM v.fecha) = :day")
    Optional<TotalSalesResponse> getTotalSalesByDay(@Param("year") Integer year, @Param("month") Integer month, @Param("day") Integer day);

    @Query("SELECT new com.pixels.parquediversiones.domain.dto.CustomerTicketsResponse(c.idComprador, c.nombre, c.apellido, COUNT(e.idVenta) as entradas) " +
            "FROM Venta v " +
            "INNER JOIN v.entradas e " +
            "INNER JOIN v.comprador c " +
            "WHERE EXTRACT(YEAR FROM v.fecha) = :year  " +
            "GROUP BY c.idComprador " +
            "ORDER BY entradas DESC")
    Optional<List<CustomerTicketsResponse>> getTotalTicketsCustomerByYear(@Param("year") Integer year);

    @Query("SELECT new com.pixels.parquediversiones.domain.dto.CustomerTicketsResponse(c.idComprador, c.nombre, c.apellido, COUNT(e.idVenta) as entradas) " +
            "FROM Venta v " +
            "INNER JOIN v.entradas e " +
            "INNER JOIN v.comprador c " +
            "WHERE EXTRACT(YEAR FROM v.fecha) = :year " +
            "AND EXTRACT(MONTH FROM v.fecha) = :month " +
            "GROUP BY c.idComprador " +
            "ORDER BY entradas DESC")
    Optional<List<CustomerTicketsResponse>> getTotalTicketsCustomerByMonth(@Param("year") Integer year, @Param("month") Integer month);
}
