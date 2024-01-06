/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.TDA.Lista.DinamicList;
import Modelo.Dao.PasajeroDao;
import Modelo.Pasajero;
import Modelo.RegistroVentas;
import java.lang.reflect.Field;

public class UsuarioControl {

    private DinamicList<Pasajero> ListaPasajeros;
    private Pasajero pasajeroControl;

    public UsuarioControl() {
    }

    public UsuarioControl(Integer tamano) {
        this.ListaPasajeros = new DinamicList<>();
    }

    public Boolean Guardar() {
        Integer pos = VerificarPosicion();
        if (pos > -1) {
            pasajeroControl.setIdPersona(pos + 1);
            ListaPasajeros.getCabeza();
            return true;
        } else {
            return false;
        }
    }

    public Integer VerificarPosicion() {
        Integer band = -1;
        for (int i = 0; i < this.ListaPasajeros.getLegth(); i++) {
            if (this.ListaPasajeros.getLegth() == null) {
                band = i;
                break;
            } else {
                band = 1;
            }
        }
        return band;
    }

    public void Imprimir() {
        for (int i = 0; i > this.getListaPasajeros().getLegth(); i++) {
            System.out.println(getListaPasajeros().getLegth());
        }
    }

    public DinamicList<Pasajero> getListaPasajeros() {
        return ListaPasajeros;
    }

    public void setListaPasajeros(DinamicList<Pasajero> ListaPasajeros) {
        this.ListaPasajeros = ListaPasajeros;
    }

    public Pasajero getPasajeroControl() {
        if (pasajeroControl == null) {
            pasajeroControl = new Pasajero();
        }
        return pasajeroControl;
    }

    public void setPasajeroControl(Pasajero pasajeroControl) {
        this.pasajeroControl = pasajeroControl;
    }

    public DinamicList<Pasajero> ordenar(DinamicList<Pasajero> lista, Integer tipo, String field) throws Exception {

        Integer n = lista.getLegth();
        Pasajero[] ventas = lista.toArray();

        for (int i = 0; i < n; i++) {
            int k = i;
            Pasajero t = ventas[i];
            for (int j = i + 1; j < n; j++) {
//                    if (personas[j].getApellidos().compareTo(t.getApellidos()) < 0) {
                if (ventas[j].comparar(t, field, tipo)) {
                    t = ventas[j];
                    k = j;
                }
            }
            ventas[k] = ventas[i];
            ventas[i] = t;
        }

        return lista.toList(ventas);
    }

    public static void main(String[] args) throws Exception {
        PasajeroDao uc = new PasajeroDao();
        DinamicList<Pasajero> lista = uc.all();
        System.out.println(lista.toString());
        UsuarioControl us = new UsuarioControl();
        System.out.println(us.ordenar(lista, 0, "boletoFechaC").toString());
    }
}
