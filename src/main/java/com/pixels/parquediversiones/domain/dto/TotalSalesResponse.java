package com.pixels.parquediversiones.domain.dto;

public class TotalSalesResponse {
    private Object totalSales;

    public TotalSalesResponse() {
    }

    public TotalSalesResponse(Object totalSales) {
        this.totalSales = totalSales;
    }

    public Object getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Object totalSales) {
        this.totalSales = totalSales;
    }
}
