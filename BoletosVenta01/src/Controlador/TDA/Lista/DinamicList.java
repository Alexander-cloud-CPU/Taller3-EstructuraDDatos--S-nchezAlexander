/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.TDA.Lista;

import Controlador.TDA.Lista.Exepciones.EmptyList;

/**
 *
 * @author Alexander
 * @param <E>
 */
public class DinamicList<E> {

    private Node<E> cabeza;
    private Node<E> ultimo;
    private Integer legth;

    public Node<E> getCabeza() {
        return cabeza;
    }

    public void setCabeza(Node<E> cabeza) {
        this.cabeza = cabeza;
    }

    public Node<E> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Node<E> ultimo) {
        this.ultimo = ultimo;
    }

    public Integer getLegth() {
        return legth;
    }

    public void setLegth(Integer legth) {
        this.legth = legth;
    }

    public DinamicList() {
        cabeza = null;
        ultimo = null;
        legth = 0;
    }

    public Boolean isEmpty() {
        return (cabeza == null || legth == 0);
    }

    public void Agregar(E info) {
        AgregarFinal(info);
    }

    public void AgregarCabeza(E info) {
        Node<E> Ayuda;
        if (isEmpty()) {
            Ayuda = new Node<>(info);
            cabeza = Ayuda;
            ultimo = Ayuda;
            legth++;
        } else {
            Node<E> CabezaAyuda = cabeza;
            Ayuda = new Node<>(info, CabezaAyuda);
            cabeza = Ayuda;
            legth++;
        }
    }

    public void AgregarFinal(E info) {
        Node<E> Ayuda;
        if (isEmpty()) {
            AgregarCabeza(info);
        } else {
            Ayuda = new Node<>(info, null);
            ultimo.setSiguiente(Ayuda);
            ultimo = Ayuda;
            legth++;
        }
    }

    private E getPrimero() throws EmptyList {
        if (isEmpty()) {
            throw new EmptyList("La lista esta vacia");
        }
        return cabeza.getInfo();
    }

    private E getFinal() throws EmptyList {
        if (isEmpty()) {
            throw new EmptyList("La lista esta vacia");
        }
        return ultimo.getInfo();
    }

    public E getInfo(Integer indice) throws EmptyList, IndexOutOfBoundsException {
        return getNodo(indice).getInfo();
    }

    private Node<E> getNodo(Integer indice) throws EmptyList, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new EmptyList("La lista esta vacia");
        } else if (indice < 0 || indice.intValue() == legth) {
            throw new IndexOutOfBoundsException("Fuera de nodo");
        } else if (indice == 0) {
            return cabeza;
        } else if (indice == (legth - 1)) {
            return ultimo;
        } else {
            Node<E> Buscar = cabeza;
            int contador = 0;
            while (contador < indice) {
                contador++;
                Buscar = Buscar.getSiguiente();
            }
            return Buscar;
        }
    }

    public E remove(Integer pos) throws EmptyList, IndexOutOfBoundsException {
        if (!isEmpty()) {
            E dato = null;
            if (pos >= 0 && pos < legth) {
                if (pos == 0) {
                    dato = cabeza.getInfo();
                    cabeza = cabeza.getSiguiente();
                    legth--;
                } else {
                    Node<E> aux = cabeza;

                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }

                    dato = aux.getInfo();
                    Node<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    legth--;
                }
            } else {
                throw new IndexOutOfBoundsException();
            }
            return dato;
        } else {
            throw new EmptyList();
        }
    }

    public E get(Integer pos) throws EmptyList, IndexOutOfBoundsException {

        if (!isEmpty()) {
            E dato = null;
            if (pos >= 0 && pos < legth) {
                if (pos == 0) {
                    dato = cabeza.getInfo();
                } else {
                    Node<E> aux = cabeza;

                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }

                    dato = aux.getInfo();
                }
            } else {
                throw new IndexOutOfBoundsException();
            }

            return dato;
        } else {
            throw new EmptyList();
        }

    }

    public void ModificarInfo(E dato, Integer pos) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            Agregar(dato);
        } else if (pos >= 0 && pos < legth) {
            if (pos == 0) {
                cabeza.setInfo(dato);
            } else {
                Node<E> aux = cabeza;

                for (int i = 0; i < pos; i++) {
                    aux = aux.getSiguiente();
                }

                aux.setInfo(dato);
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void vaciar() {
        this.cabeza = null;
        this.legth = 0;
    }

    @Override
    public String toString() {
        StringBuilder StringB = new StringBuilder("Datos de lista \n");
        try {
            isEmpty();

            Node<E> ayuda = cabeza;

            while (ayuda != null) {
                StringB.append(ayuda.getInfo().toString());
                ayuda = ayuda.getSiguiente();
            }
        } catch (Exception e) {
            StringB.append(e.getMessage());
        }
        return StringB.toString();
    }
    public E[] toArray(){
        Class clazz = null;
        E[] matriz = null;
        if (legth > 0) {
            clazz = cabeza.getInfo().getClass();
            matriz = (E[])java.lang.reflect.Array.newInstance(clazz, legth);
            Node<E> aux = cabeza;
            for (int i = 0; i < legth; i++) {
                matriz[i] = aux.getInfo();
                aux = aux.getSiguiente();
            }
        }
        return matriz;
    }
    public DinamicList<E> toList(E[] m){
        reset();
        for (int i = 0; i < m.length; i++) {
            this.Agregar(m[i]);
        }
        return this;
    }
    
    public void reset(){
        cabeza = null;
        ultimo = null;
        legth = 0;
    }

}
