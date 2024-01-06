/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.utiles;

import Controlador.TDA.Lista.DinamicList;
import Controlador.TDA.Lista.Exepciones.EmptyList;
import Modelo.Dao.PasajeroDao;
import Modelo.Pasajero;
import java.lang.reflect.Field;
import javax.swing.JComboBox;

/**
 *
 * @author Alexander
 */
public class Utiles {
    
   public static Field getField(Class clazz, String atribute) {
        Field field = null;
        for (Field f : clazz.getSuperclass().getDeclaredFields()) {
            if (f.getName().equalsIgnoreCase(atribute)) {
                field = f;
                break;
            }
        }
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getName().equalsIgnoreCase(atribute)) {
                field = f;
                break;
            }
        }
        return field;
    }
    
    public DinamicList<Pasajero> ordenar(DinamicList<Pasajero> lista, Integer tipo, String field) throws EmptyList, Exception {
        Field attribute = Utiles.getField(Pasajero.class, field);
        Integer n = lista.getLegth();
        Pasajero[] personas = lista.toArray();
        if (attribute != null) {
            for (int i = 0; i < n; i++) {
                int k = i;
                Pasajero t = personas[i];
                for (int j = i + 1; j < n; j++) {
//                    if (personas[j].getApellidos().compareTo(t.getApellidos()) < 0) {
                    if (personas[j].comparar(t, field, tipo)) {
                        t = personas[j];
                        k = j;
                    }
                }
                personas[k] = personas[i];
                personas[i] = t;
            }
        } else {
            throw new Exception("No existe el criterio de busqueda");
        }
        return lista.toList(personas);

    }
    
    public static DinamicList<Pasajero> ShellSort(DinamicList<Pasajero> lista, Integer tipo, String field) {
        int n = lista.getLegth();
        Pasajero[] personas = lista.toArray();

        for (int intervalo = n / 2; intervalo > 0; intervalo /= 2) {
            for (int i = intervalo; i < n; i++) {
                Pasajero ayuda = personas[i];
                int j;
                for (j = i; j >= intervalo && ayuda.comparar(personas[j - intervalo], field, tipo); j -= intervalo) {
                    personas[j] = personas[j - intervalo];
                }
                personas[j] = ayuda;
            }
        }
        return lista.toList(personas);
    }
    
    public static DinamicList<Pasajero> MetodoQuickSort(DinamicList<Pasajero> listaPasajeros, Integer tipo, String Campo) throws EmptyList, NullPointerException {
        if (listaPasajeros == null || listaPasajeros.getLegth()<= 1) {
            return listaPasajeros;
        }
        Recursuvoquicksort(listaPasajeros, 0, listaPasajeros.getLegth()- 1, tipo, Campo);
        return listaPasajeros;
    }

    private static void Recursuvoquicksort(DinamicList<Pasajero> listaPasajeros, int inicio, int fin, Integer tipo, String Campo) throws EmptyList, NullPointerException {
        if (inicio < fin) {
            int indiceParticion = Dividir(listaPasajeros, inicio, fin, tipo, Campo);
            Recursuvoquicksort(listaPasajeros, inicio, indiceParticion - 1, tipo, Campo);
            Recursuvoquicksort(listaPasajeros, indiceParticion + 1, fin, tipo, Campo);
        }
    }

    private static int Dividir(DinamicList<Pasajero> listaPasajeros, int inicio, int fin, Integer tipo, String Campo) throws EmptyList, NullPointerException {
        Pasajero pivote = listaPasajeros.getInfo(fin);
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (pivote.comparar(listaPasajeros.getInfo(j), Campo, tipo)) {
                i++;
                cambio(listaPasajeros, i, j);
            }
        }
        cambio(listaPasajeros, i + 1, fin);
        return i + 1;
    }

    private static void cambio(DinamicList<Pasajero> listaPasajeros, int i, int j) throws EmptyList, NullPointerException {
        Pasajero ayuda = listaPasajeros.getInfo(i);
        listaPasajeros.ModificarInfo(listaPasajeros.getInfo(j), i);
        listaPasajeros.ModificarInfo(ayuda, j);
    }
    
    public static void cargarcomboPasajero(JComboBox cbx) throws EmptyList{
        PasajeroDao rc = new PasajeroDao();
        cbx.removeAllItems();
        
        if(rc.getListaPasajeros().isEmpty()){
            throw new EmptyList("No hay pasajeros que mostrar");
        }
        else{
           for (int i = 0; i < rc.getListaPasajeros().getLegth(); i++) {
            cbx.addItem(rc.getListaPasajeros().getInfo(i));
           }
        }
    }
    
    public static Pasajero obtenerPasajeroControl(JComboBox cbx){
        return (Pasajero) cbx.getSelectedItem();
    }
}
