/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alexander
 */
public class informacionBoleto {
    private Integer idBoleto;
    private String PaisOrigen;
    private String PaisDestino;
    private Integer BoletosCantidad;
    private String FechaViaje;
    private String FechaCompra;
    private Float totalPagar;

    public informacionBoleto() {
        
    }

    public informacionBoleto(Integer idBoleto, String PaisOrigen, String PaisDestino, Integer BoletosCantidad, String FechaViaje, String FechaCompra, Float totalPagar) {
        this.idBoleto = idBoleto;
        this.PaisOrigen = PaisOrigen;
        this.PaisDestino = PaisDestino;
        this.BoletosCantidad = BoletosCantidad;
        this.FechaViaje = FechaViaje;
        this.FechaCompra = FechaCompra;
        this.totalPagar = totalPagar;
    }

    public Integer getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(Integer idBoleto) {
        this.idBoleto = idBoleto;
    }

    public String getPaisOrigen() {
        return PaisOrigen;
    }

    public void setPaisOrigen(String PaisOrigen) {
        this.PaisOrigen = PaisOrigen;
    }

    public String getPaisDestino() {
        return PaisDestino;
    }

    public void setPaisDestino(String PaisDestino) {
        this.PaisDestino = PaisDestino;
    }

    public Integer getBoletosCantidad() {
        return BoletosCantidad;
    }

    public void setBoletosCantidad(Integer BoletosCantidad) {
        this.BoletosCantidad = BoletosCantidad;
    }

    public String getFechaViaje() {
        return FechaViaje;
    }

    public void setFechaViaje(String FechaViaje) {
        this.FechaViaje = FechaViaje;
    }

    public String getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(String FechaCompra) {
        this.FechaCompra = FechaCompra;
    }

    public Float getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Float totalPagar) {
        this.totalPagar = totalPagar;
    }

    @Override
    public String toString() {
        return "informacionBoleto{" + "idBoleto=" + idBoleto + ", PaisOrigen=" + PaisOrigen + ", PaisDestino=" + PaisDestino + ", BoletosCantidad=" + BoletosCantidad + ", FechaViaje=" + FechaViaje + ", FechaCompra=" + FechaCompra + ", totalPagar=" + totalPagar + '}';
    }
    
}
