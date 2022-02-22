/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thompson;

/**
 *
 * @author fabian
 */
public class Transiciones {

    public NodoG inicio;
    public NodoG fin;
    public int size;

    public Transiciones() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void AgregarTransicion(int id, String peso) {
        NodoG nuevo = new NodoG(id);
        nuevo.peso = peso;
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
        this.size += 1;
    }

}
