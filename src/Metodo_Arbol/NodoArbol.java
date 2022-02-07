/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo_Arbol;

/**
 *
 * @author fabian
 */
public class NodoArbol {

    public String lexema;
    public String tipo;
    public int id;
    public boolean anulable;
    //otros atributos
    public NodoArbol izq = null;
    public NodoArbol der = null;

    public NodoArbol(String lexema, String tipo) {
        this.lexema = lexema;
        this.tipo = tipo;
        
    }

    public boolean EsHoja() {
        if (this.izq == null && this.der == null) {
            return true;
        }
        return false;
    }

}
