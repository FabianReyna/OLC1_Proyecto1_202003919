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
public class NodoPila {
    public NodoArbol nodo;
    public NodoPila sig;

    public NodoPila(NodoArbol nodo) {
        this.nodo = nodo;
        this.sig = null;
    }
    
    
}
