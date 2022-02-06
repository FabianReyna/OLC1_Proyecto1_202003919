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
public class ListaExpresiones {

    public NodoExpresion inicio;
    public NodoExpresion fin;

    public ListaExpresiones() {
        this.inicio = null;
        this.fin = null;
    }

    public void AgregarExpresion(String lex, String tip, String val) {
        NodoExpresion nuevo = new NodoExpresion(lex, tip, val);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
    }

    public void Eliminar() {
        if (this.inicio == this.fin) {
            this.inicio = this.fin = null;
        } else if (this.inicio.sig == this.fin) {
            this.inicio.sig = null;
            this.fin = this.inicio;
        } else {
            NodoExpresion aux, aux2;
            aux = this.inicio.sig;
            aux2 = this.inicio;
            while (aux != null) {
                if (aux == this.fin) {
                    aux2.sig = null;
                    this.fin = aux2;
                    break;
                }
                aux2 = aux2.sig;
                aux = aux.sig;

            }
        }
    }

}
