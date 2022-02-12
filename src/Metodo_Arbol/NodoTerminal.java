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
public class NodoTerminal {

    public String terminal;
    public int estado;
    public NodoTerminal sig;
    public ListaPosiciones listapos;

    public NodoTerminal(String terminal) {
        this.terminal = terminal;
        this.sig = null;
        this.listapos = new ListaPosiciones();
        this.estado = -1;
    }

}
