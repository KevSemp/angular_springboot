package com.example.simpleapi.models;

import javax.persistence.*;

@Entity
@Table(name = "tipo_cambio")
public class CambioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int peticion_id;
    private String fecha;
    private Double compra;
    private Double venta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPeticion_id() {
        return peticion_id;
    }

    public void setPeticion_id(int peticion_id) {
        this.peticion_id = peticion_id;
    }

    public Double getCompra() {
        return compra;
    }

    public void setCompra(Double compra) {
        this.compra = compra;
    }

    public Double getVenta() {
        return venta;
    }

    public void setVenta(Double venta) {
        this.venta = venta;
    }
}
