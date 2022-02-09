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
public class ListaSiguientes {

    public NodoSiguientes inicio;
    public NodoSiguientes fin;

    public ListaSiguientes() {
        this.inicio = null;
        this.fin = null;
    }

    public void AgregarTerminales(String t, int i) {
        NodoSiguientes nuevo = new NodoSiguientes(t, i);
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
    }

    public void AgregarSiguientes(int id, int sig) {
        NodoSiguientes aux = this.inicio;
        while (aux != null) {
            if (aux.id_hoja == id) {
                aux.siguientes.AgregarPosiciones(sig);
                break;
            }
            aux = aux.sig;
        }
    }

    public void CrearNodos(NodoArbol root) {
        if (root != null) {

            this.CrearNodos(root.izq);
            this.CrearNodos(root.der);

            if (root.EsHoja()) {
                this.AgregarTerminales(root.lexema, root.id);
            }
        }
    }

    public void AgregandoSiguientes(NodoArbol root) {
        if (root != null) {
            this.AgregandoSiguientes(root.izq);
            this.AgregandoSiguientes(root.der);

            if (!(root.EsHoja())) {
                if (root.tipo.equals("CONCAT1")) {
                    NodoPosicion aux = root.izq.ultimos.inicio;
                    while (aux != null) {
                        NodoPosicion aux2 = root.der.primeros.inicio;
                        while (aux2 != null) {
                            this.AgregarSiguientes(aux.posicion, aux2.posicion);
                            aux2 = aux2.sig;
                        }
                        aux = aux.sig;
                    }

                } else if (root.tipo.equals("KLEENE") || root.tipo.equals("POSITIVE1")) {
                    NodoPosicion aux = root.der.ultimos.inicio;
                    while (aux != null) {
                        NodoPosicion aux2 = root.der.primeros.inicio;
                        while (aux2 != null) {
                            this.AgregarSiguientes(aux.posicion, aux2.posicion);
                            aux2 = aux2.sig;
                        }

                        aux = aux.sig;
                    }
                }
            }
        }
    }

}
