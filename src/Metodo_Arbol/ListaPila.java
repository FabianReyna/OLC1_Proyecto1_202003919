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

    public void Eliminar(NodoArbol na) {
        if (this.inicio == this.fin) {
            if (this.inicio.nodo == na) {
                this.inicio = this.fin = null;
            }

        } else if (this.inicio.sig == this.fin) {
            if (this.fin.nodo == na) {
                this.inicio.sig = null;
                this.fin = this.inicio;
            } else if (this.inicio.nodo == na) {
                this.inicio = this.fin;
            }

        } else if (this.inicio.nodo == na) {
            this.inicio = this.inicio.sig;
        } else {
            NodoPila aux, aux2;
            aux = this.inicio.sig;
            aux2 = this.inicio;
            while (aux != null) {
                if (aux.nodo == na) {//aux.nodo==np
                    aux2.sig = aux.sig.sig;
                    break;

                }
                aux2 = aux2.sig;
                aux = aux.sig;

            }

        }
        this.ActualizaFin();
        this.size -= 1;
    }

    private void ActualizaFin() {
        NodoPila aux = this.inicio;
        while (aux != null) {
            if (aux.sig == null) {
                this.fin=aux;
                break;
            }
            aux=aux.sig;
        }
    }
}
