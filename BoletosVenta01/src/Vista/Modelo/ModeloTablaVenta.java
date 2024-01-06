/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Modelo;

import Controlador.TDA.Lista.Exepciones.EmptyList;
import Controlador.TDA.Lista.DinamicList;
import Modelo.Pasajero;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alexander
 */
public class ModeloTablaVenta extends AbstractTableModel {

    private DinamicList<Pasajero> pasajerosTabla;

    public DinamicList<Pasajero> getPasajerosTabla() {
        return pasajerosTabla;
    }

    public void setPasajerosTabla(DinamicList<Pasajero> pasajerosTabla) {
        this.pasajerosTabla = pasajerosTabla;
    }

    @Override
    public int getRowCount() {
        return pasajerosTabla.getLegth();
    }

    @Override
    public int getColumnCount() {
        return 12;
    }

    @Override
    public Object getValueAt(int Fila, int Columna) {

        try {
            Pasajero p = pasajerosTabla.getInfo(Fila);

            switch (Columna) {
                case 0:
                    return (p != null) ? p.getIdPersona() : "";
                case 1:
                    return (p != null) ? p.getNumeroCedula() : "";
                case 2:
                    return (p != null) ? p.getNombrePersona() : "";
                case 3:
                    return (p != null) ? p.getApelldoPersona() : "";
                case 4:
                    return (p != null) ? p.getGeneroPersona() : "";
                case 5:
                    return (p != null) ? p.getDireccionPersona() : "";
                case 6:
                    return (p != null) ? p.getBoletoPasajero().getPaisOrigen() : "";
                case 7:
                    return (p != null) ? p.getBoletoPasajero().getPaisDestino() : "";
                case 8:
                    return (p != null) ? p.getBoletoPasajero().getBoletosCantidad() : "";
                case 9:
                    return (p != null) ? p.getBoletoPasajero().getFechaViaje() : "";
                case 10:
                    return (p != null) ? p.getBoletoPasajero().getFechaCompra() : "";
                case 11:
                    return (p != null) ? p.getBoletoPasajero().getTotalPagar() : "";

                default:
                    return null;
            }
        } catch (EmptyList ex) {
        } catch (IndexOutOfBoundsException ex) {
        }
        return pasajerosTabla;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id venta";
            case 1:
                return "Numero cedula";
            case 2:
                return "Nombre";
            case 3:
                return "Apellido";
            case 4:
                return "Genero";
            case 5:
                return "Direccion";
            case 6:
                return "Pais origen";
            case 7:
                return "Pais destino";
            case 8:
                return "Cantidad voletos";
            case 9:
                return "Fecha viaje";
            case 10:
                return "Fecha compra";
            case 11:
                return "Total";

            default:
                return null;
        }
    }
    
    public double calcularTotalVentas(int columna) {
        double Total = 0.0;
        for (int fila = 0; fila < getRowCount(); fila++) {
            try {
                Object valor = getValueAt(fila, columna);
                if (valor instanceof Number) {
                    Total += ((Number) valor).doubleValue();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Total;
    }
}
