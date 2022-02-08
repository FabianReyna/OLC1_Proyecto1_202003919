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
public class ListaPosiciones {

    NodoPosicion inicio;
    NodoPosicion fin;

    public ListaPosiciones() {
        this.inicio = null;
        this.fin = null;
    }

    public void AgregarPosiciones(int pos) {
        NodoPosicion nuevo = new NodoPosicion(pos);
        if (this.VerificaExistencia(pos)) {
            if (this.inicio == null) {
                this.inicio = nuevo;
            }
            if (this.fin != null) {
                this.fin.sig = nuevo;
            }
            this.fin = nuevo;
        }

    }

    public boolean VerificaExistencia(int pos) {
        NodoPosicion aux = this.inicio;
        while (aux != null) {
            if (aux.posicion == pos) {
                return false;
            }
            aux = aux.sig;
        }
        return true;
    }

}
