/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao;

import Controlador.Dao.DaoImplement;
import Controlador.TDA.Lista.DinamicList;
import Controlador.TDA.Lista.Exepciones.EmptyList;
import Modelo.Pasajero;
import java.lang.reflect.Field;

/**
 *
 * @author Alexander
 */
public class PasajeroDao extends DaoImplement<Pasajero> {

    private DinamicList<Pasajero> ListaPasajeros = new DinamicList<>();
    private Pasajero pasajero;

    public PasajeroDao() {
        super(Pasajero.class);
    }

    public DinamicList<Pasajero> getListaPasajeros() {
        ListaPasajeros = all();
        return ListaPasajeros;
    }

    public void setListaPasajeros(DinamicList<Pasajero> ListaPasajeros) {
        this.ListaPasajeros = ListaPasajeros;
    }

    public Pasajero getPasajero() {
        if (pasajero == null) {
            pasajero = new Pasajero();
        }
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Boolean Persist() {
        pasajero.setIdPersona(all().getLegth() + 1);
        return Persist(pasajero);
    }
     
}
