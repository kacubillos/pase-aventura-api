package com.pixels.parquediversiones.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entradas")
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada")
    private Integer idEntrada;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "fecha_vigencia")
    private LocalDateTime fechaVigencia;

    private Boolean activo;

    @Column(name = "id_juego")
    private Integer idJuego;

    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "id_comprador")
    private Integer idComprador;

    @ManyToOne
    @JoinColumn(name = "id_comprador", insertable = false, updatable = false)
    private Comprador comprador;

    @ManyToOne
    @JoinColumn(name = "id_venta", insertable = false, updatable = false)
    private Venta venta;

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public LocalDateTime getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(LocalDateTime fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Integer idJuego) {
        this.idJuego = idJuego;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Integer idComprador) {
        this.idComprador = idComprador;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
