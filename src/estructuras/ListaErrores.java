/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author fabian
 */
public class ListaErrores {

    NodoError inicio;
    NodoError fin;

    public ListaErrores() {
        this.inicio = null;
        this.fin = null;
    }

    public void NewError(String tipo, String desc, int linea, int col) {
        NodoError nuevo = new NodoError(tipo, desc, linea, col);
        if (this.inicio == null) {
            this.inicio = nuevo;
        } else {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;

    }

}
