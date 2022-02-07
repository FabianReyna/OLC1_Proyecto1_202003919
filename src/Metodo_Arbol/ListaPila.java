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
public class ListaPila {

    public NodoPila inicio;
    public NodoPila fin;
    public int size;

    public ListaPila() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void Apilar(NodoArbol nb) {
        NodoPila nuevo = new NodoPila(nb);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
        this.size += 1;
    }

    public void Eliminar() {
        if (this.inicio == this.fin) {
            this.inicio = this.fin = null;

        } else if (this.inicio.sig == this.fin) {
            this.inicio.sig = null;
            this.fin = this.inicio;

        } else {
            NodoPila aux, aux2;
            aux = this.inicio.sig;
            aux2 = this.inicio;
            while (aux != null) {
                if (aux == this.fin) {
                    aux2.sig = null;
                    this.fin = aux2;

                }
                aux2 = aux2.sig;
                aux = aux.sig;

            }
        }
        this.size -= 1;
    }
}
