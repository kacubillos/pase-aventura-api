package com.pixels.parquediversiones.web.controller;

import com.pixels.parquediversiones.domain.Sale;
import com.pixels.parquediversiones.domain.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<List<Sale>> getAll() {
        return new ResponseEntity<>(saleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable("id") int saleId) {
        return saleService.getSale(saleId)
                .map(sale -> new ResponseEntity<>(sale, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Sale>> getByCustomer(@PathVariable("id") int customerId) {
        return saleService.getByCustomer(customerId)
                .map(sales -> new ResponseEntity<>(sales, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Sale> save(@RequestBody Sale sale) {
        return new ResponseEntity<>(saleService.save(sale), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Sale> update(@RequestBody Sale sale) {
        return saleService.getSale(sale.getSaleId())
                .map(saleAux -> new ResponseEntity<>(saleService.save(sale), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int saleId) {
        if(saleService.delete(saleId))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
