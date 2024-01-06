/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.TDA.Lista;

/**
 *
 * @author Alexander
 * @param <E>
 */
public class Node<E> {

    private E info;
    private Node<E> Siguiente;

    public Node(E info) {
        this.info = info;
        Siguiente = null;

    }

    public Node(E info, Node<E> siguiente) {
        this.info = info;
        this.Siguiente = siguiente;

    }

    public Node() {
        Siguiente = null;
        info = null;

    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public Node<E> getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(Node<E> siguiente) {
        this.Siguiente = siguiente;
    }

    @Override
    public String toString() {
        if (info != null) {
            return info.toString();
        } else {
            return null;
        }
    }
}
