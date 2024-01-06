/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.TDA.Lista.Exepciones;

/**
 *
 * @author Alexander
 */
public class EmptyList extends Exception{
    
    public EmptyList(){
        
    }
    
    public EmptyList(String msg) {
        super(msg);
    }
}
