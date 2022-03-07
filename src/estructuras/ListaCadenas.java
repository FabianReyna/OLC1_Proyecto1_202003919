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
public class ListaCadenas {

    public NodoCadena inicio;
    public NodoCadena fin;

    public ListaCadenas() {
        this.inicio = null;
        this.fin = null;
    }

    public void AgregarCadena(String cadena) {
        NodoCadena nuevo = new NodoCadena(cadena);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
    }
}
