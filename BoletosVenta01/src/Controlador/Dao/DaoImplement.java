/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Dao;

import Controlador.TDA.Lista.DinamicList;
import com.thoughtworks.xstream.XStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Alexander
 * @param <T>
 */
public class DaoImplement<T> implements DaoInterface<T> {

    private Class<T> clazz;
    private XStream conection;
    private String URL;

    public DaoImplement(Class<T> clazz) {
        this.clazz = clazz;
        conection = Bridge.getConection();
        URL = Bridge.URL + clazz.getSimpleName() + ".json";
    }

    @Override
    public Boolean Persist(T dato) {
        DinamicList<T> ld = all();
        ld.Agregar(dato);
        try {
            conection.toXML(ld, new FileWriter(URL));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Boolean Merge(T data, Integer index) {
        DinamicList<T> listaActualizada = all();
        try {
            listaActualizada.ModificarInfo(data, index);
            conection.toXML(listaActualizada, new FileWriter(URL));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public DinamicList<T> all() {
        DinamicList<T> dl = new DinamicList<>();
        try {
            dl = (DinamicList<T>) conection.fromXML(new FileReader(URL));
        } catch (Exception e) {
        }
        return dl;
    }

    @Override
    public T get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
