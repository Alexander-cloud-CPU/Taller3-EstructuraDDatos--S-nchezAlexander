/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alexander
 */
public class RegistroVentas {
    private Integer idVenta;
    private String formaPago;
    private Pasajero datosPasajero;
    private informacionBoleto ventaBoleto;

    public RegistroVentas() {
    }

    public RegistroVentas(Integer idVenta, String formaPago, Pasajero datosPasajero, informacionBoleto ventaBoleto) {
        this.idVenta = idVenta;
        this.formaPago = formaPago;
        this.datosPasajero = datosPasajero;
        this.ventaBoleto = ventaBoleto;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Pasajero getDatosPasajero() {
        return datosPasajero;
    }

    public void setDatosPasajero(Pasajero datosPasajero) {
        this.datosPasajero = datosPasajero;
    }

    public informacionBoleto getVentaBoleto() {
        return ventaBoleto;
    }

    public void setVentaBoleto(informacionBoleto ventaBoleto) {
        this.ventaBoleto = ventaBoleto;
    }

    @Override
    public String toString() {
        return "RegistroVentas{" + "idVenta=" + idVenta + ", formaPago=" + formaPago + ", datosPasajero=" + datosPasajero + ", ventaBoleto=" + ventaBoleto + '}';
    }
    
    
}
