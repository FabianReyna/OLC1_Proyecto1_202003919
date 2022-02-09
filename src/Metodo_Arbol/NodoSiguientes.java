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
public class NodoSiguientes {

    public String Terminal;
    public int id_hoja;
    public ListaPosiciones siguientes;
    public NodoSiguientes sig;

    public NodoSiguientes(String t, int ih) {
        this.Terminal = t;
        this.id_hoja = ih;
        this.siguientes = new ListaPosiciones();
        this.sig = null;
    }

}
