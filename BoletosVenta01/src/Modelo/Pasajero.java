/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alexander
 */
public class Pasajero {

    private Integer idPersona;
    private String numeroCedula;
    private String nombrePersona;
    private String apellidoPersona;
    private String generoPersona;
    private String direccionPersona;
    private informacionBoleto boletoPasajero;

    public Pasajero() {
    }

    public Pasajero(Integer idPersona, String numeroCedula, String NombrePersona, String ApelldoPersona, String generoPersona, String DireccionPersona, informacionBoleto boletoPasajero) {
        this.idPersona = idPersona;
        this.numeroCedula = numeroCedula;
        this.nombrePersona = NombrePersona;
        this.apellidoPersona = ApelldoPersona;
        this.generoPersona = generoPersona;
        this.direccionPersona = DireccionPersona;
        this.boletoPasajero = boletoPasajero;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNumeroCedula() {
        return numeroCedula;
    }

    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String NombrePersona) {
        this.nombrePersona = NombrePersona;
    }

    public String getApelldoPersona() {
        return apellidoPersona;
    }

    public void setApelldoPersona(String ApelldoPersona) {
        this.apellidoPersona = ApelldoPersona;
    }

    public String getGeneroPersona() {
        return generoPersona;
    }

    public void setGeneroPersona(String generoPersona) {
        this.generoPersona = generoPersona;
    }

    public String getDireccionPersona() {
        return direccionPersona;
    }

    public void setDireccionPersona(String DireccionPersona) {
        this.direccionPersona = DireccionPersona;
    }

    public informacionBoleto getBoletoPasajero() {
        return boletoPasajero;
    }

    public void setBoletoPasajero(informacionBoleto boletoPasajero) {
        this.boletoPasajero = boletoPasajero;
    }
    
    public Boolean comparar(Pasajero p, String campo, Integer tipo) {
        switch (tipo) {
            case 0:
                return compararCampo(p, campo) < 0;
            case 1:
                return compararCampo(p, campo) > 0;
            default:
                throw new IllegalArgumentException("Tipo no válido");
        }
    }
    
    public int compararCampo(Pasajero p, String campo) {
        switch (campo.toLowerCase()) {
            
            case "nombre":
                return nombrePersona.compareTo(p.getNombrePersona());
            case "numero de cedula":
                return numeroCedula.compareTo(p.getNumeroCedula());
            case "genero":
                return generoPersona.compareTo(p.getGeneroPersona());
            case "apellido":
                return apellidoPersona.compareTo(p.getApelldoPersona());
            default:
                throw new IllegalArgumentException("Campo no válido");
        }
    }
    
    
    @Override
    public String toString() {
        return "Pasajero{" + "idPersona=" + idPersona + ", numeroCedula=" + numeroCedula + ", NombrePersona=" + nombrePersona + ", ApelldoPersona=" + apellidoPersona + ", generoPersona=" + generoPersona + ", DireccionPersona=" + direccionPersona + ", boletoPasajero=" + boletoPasajero + '}';
    }

    
}
