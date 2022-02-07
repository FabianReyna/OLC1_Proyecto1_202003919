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
public class Arbol {

    public NodoArbol raiz;
    public boolean flag;
    public ListaPila NonTerm;

    public Arbol() {
        this.raiz = null;
        this.flag = false;
        this.NonTerm = new ListaPila();

    }

    public void AgregarNodo(String lexema, String tipo) {
        NodoArbol nuevo = new NodoArbol(lexema, tipo);
        if (!flag) {
            if (this.raiz == null) {
                this.raiz = nuevo;
            } else if (tipo.equals("FINCADENA")) {
                this.raiz.der = nuevo;
            } else if (!tipo.equals("FINCADENA") && this.raiz.izq == null) {
                this.raiz.izq = nuevo;
            } else {
                this.raiz.izq = this.AgregarNodo2(this.raiz.izq, nuevo);
            }
        } else {
            this.NonTerm.fin.nodo.der = this.AgregarNodo2(this.NonTerm.fin.nodo.der, nuevo);

            NodoPila np = this.NonTerm.inicio;

            while (np != null) {
                NodoArbol aux = np.nodo;
                while (aux != null) {
                    if (aux.tipo.equals("TERMINAL")) {
                        this.NonTerm.Eliminar();
                        break;
                    }
                    aux = aux.der;
                }
                np = np.sig;
            }

            if (this.NonTerm.size == 0) {
                this.flag = false;
            }
        }

    }

    public NodoArbol AgregarNodo2(NodoArbol raiz_actual, NodoArbol nuevo) {

        if (raiz_actual != null) {

            if (raiz_actual.tipo.equals("CONCAT1") || raiz_actual.tipo.equals("OR1")) {

                if (!(nuevo.tipo.equals("CONCAT1") || nuevo.tipo.equals("OR11"))) { 

                    if (raiz_actual.izq == null) {
                        raiz_actual.izq = this.AgregarNodo2(raiz_actual.izq, nuevo);
                    } else {
                        raiz_actual.der = this.AgregarNodo2(raiz_actual.der, nuevo);
                    }

                } else {
                    raiz_actual.der = this.AgregarNodo2(raiz_actual.der, nuevo);
                }

            } else if (!raiz_actual.tipo.equals("TERMINAL")) {
                raiz_actual.der = this.AgregarNodo2(raiz_actual.der, nuevo);

            }
        } else {
            raiz_actual = nuevo;
            if (nuevo.tipo.equals("KLEENE") || nuevo.tipo.equals("POSITIVE1") || nuevo.tipo.equals("OPTIONAL1")) {
                this.flag = true;
                this.NonTerm.Apilar(raiz_actual);
            }
            return raiz_actual;
        }
        return raiz_actual;
    }

}
/*
public NodoArbol AgregarNodo2(NodoArbol raiz_actual, NodoArbol nuevo) {
        if (raiz_actual != null) {
            if (raiz_actual.tipo.equals("CONCAT1") || raiz_actual.tipo.equals("OR1")) {
                if (!nuevo.tipo.equals("CONCAT1") || !nuevo.tipo.equals("OR11")) {
                    if (raiz_actual.izq == null) {
                        raiz_actual.izq = this.AgregarNodo2(raiz_actual.izq, nuevo);
                    } else {
                        raiz_actual.der = this.AgregarNodo2(raiz_actual.der, nuevo);
                    }
                } else {
                    raiz_actual.der = this.AgregarNodo2(raiz_actual.der, nuevo);
                }

            } else if (!raiz_actual.tipo.equals("TERMINAL")) {
                raiz_actual.izq = this.AgregarNodo2(raiz_actual.izq, nuevo);
            }
        } else {
            raiz_actual = nuevo;
            return raiz_actual;
        }
        return raiz_actual;
    }
 */
