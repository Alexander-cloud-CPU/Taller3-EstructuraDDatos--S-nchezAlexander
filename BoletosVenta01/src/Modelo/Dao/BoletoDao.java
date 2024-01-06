package Modelo.Dao;

import Controlador.Dao.DaoImplement;
import Controlador.TDA.Lista.DinamicList;
import Modelo.informacionBoleto;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Alexander
 */
public class BoletoDao extends DaoImplement<informacionBoleto> {

    private DinamicList<informacionBoleto> ListaBoletos = new DinamicList<>();
    private informacionBoleto boletos;

    public BoletoDao() {
        super(informacionBoleto.class);
    }

    public DinamicList<informacionBoleto> getListaBoletos() {
        return ListaBoletos;
    }

    public void setListaBoletos(DinamicList<informacionBoleto> ListaBoletos) {
        this.ListaBoletos = ListaBoletos;
    }

    public informacionBoleto getBoletos() {
        if (boletos == null) {
            boletos = new informacionBoleto();
        }
        return boletos;
    }

    public void setBoletos(informacionBoleto boletos) {
        this.boletos = boletos;
    }

    public Boolean Persist() {
        boletos.setIdBoleto(all().getLegth() + 1);
        return Persist(boletos);
    }
}
