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
    public int contador;
    private int contador2;

    public Arbol() {
        this.raiz = null;
        this.flag = false;
        this.NonTerm = new ListaPila();
        this.contador = 1;
        this.contador2 = 1;

    }

    public void AgregarNodo(String lexema, String tipo) {
        NodoArbol nuevo = new NodoArbol(lexema, tipo);
        nuevo.id_grafica = this.contador;
        this.contador += 1;
        if (!flag) {
            if (this.raiz == null) {
                this.raiz = nuevo;
            } else if (tipo.equals("FINCADENA")) {
                this.raiz.der = nuevo;
            } else if (!tipo.equals("FINCADENA") && this.raiz.izq == null) {
                this.raiz.izq = nuevo;
                if (!nuevo.tipo.equals("TERMINAL")) {
                    this.flag = true;
                    this.NonTerm.Apilar(this.raiz.izq);
                }
            } else {
                this.raiz.izq = this.AgregarNodo2(this.raiz.izq, nuevo);

            }
        } else {
            if (this.NonTerm.fin.nodo.tipo.equals("OR1") || this.NonTerm.fin.nodo.tipo.equals("CONCAT1")) {
                if (this.NonTerm.fin.nodo.izq == null) {
                    this.NonTerm.fin.nodo.izq = this.AgregarNodo2(this.NonTerm.fin.nodo.izq, nuevo);
                } else {
                    this.NonTerm.fin.nodo.der = this.AgregarNodo2(this.NonTerm.fin.nodo.der, nuevo);
                }
            } else {
                this.NonTerm.fin.nodo.der = this.AgregarNodo2(this.NonTerm.fin.nodo.der, nuevo);

            }

            NodoPila np = this.NonTerm.inicio;

            while (np != null) {
                NodoArbol aux = np.nodo;

                while (aux != null) {
                    if (aux.tipo.equals("TERMINAL")) {
                        this.NonTerm.Eliminar(np.nodo);
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
                if (raiz_actual.izq == null) {
                    raiz_actual.izq = this.AgregarNodo2(raiz_actual.izq, nuevo);
                } else {
                    raiz_actual.der = this.AgregarNodo2(raiz_actual.der, nuevo);
                }

            } else if (!raiz_actual.tipo.equals("TERMINAL")) {
                raiz_actual.der = this.AgregarNodo2(raiz_actual.der, nuevo);

            }
        } else {
            raiz_actual = nuevo;
            if (!nuevo.tipo.equals("TERMINAL")) {
                this.flag = true;
                this.NonTerm.Apilar(raiz_actual);
            }
            return raiz_actual;
        }
        return raiz_actual;
    }

    public void Identifica_Hojas(NodoArbol root) {
        if (root != null) {
            this.Identifica_Hojas(root.izq);
            this.Identifica_Hojas(root.der);

            if (root.EsHoja()) {
                root.id = this.contador2;
                this.contador2 += 1;
                //System.out.println("Hoja "+root.lexema);
            }
        }
    }

    public void Anulables(NodoArbol root) {
        if (root != null) {
            this.Anulables(root.izq);
            this.Anulables(root.der);

            if (!(root.EsHoja())) {
                if (root.tipo.equals("OR1")) {
                    if (root.der.anulable && root.izq.anulable) {
                        root.anulable = true;
                    }

                } else if (root.tipo.equals("CONCAT1")) {
                    if (root.der.anulable && root.izq.anulable) {
                        root.anulable = true;
                    }

                } else if (root.tipo.equals("KLEENE")) {
                    root.anulable = true;

                } else if (root.tipo.equals("POSITIVE1")) {
                    if (root.der.anulable) {
                        root.anulable = true;
                    }

                } else if (root.tipo.equals("OPTIONAL1")) {
                    root.anulable = true;
                }

            }
        }
    }

    public void Primeros(NodoArbol root) {
        if (root != null) {
            this.Anulables(root.izq);
            this.Anulables(root.der);

            if (root.EsHoja()) {
                root.AgregarPrimero(root.id);
            } else {
                if (root.tipo.equals("OR1")) {
                    NodoPosicion izquierda = root.izq.primeros.inicio;
                    while (izquierda != null) {
                        root.AgregarPrimero(izquierda.posicion);
                        izquierda = izquierda.sig;
                    }
                    NodoPosicion derecha = root.der.primeros.inicio;
                    while (derecha != null) {
                        root.AgregarPrimero(derecha.posicion);
                        derecha = derecha.sig;
                    }

                } else if (root.tipo.equals("CONCAT1")) {
                    if (root.izq.anulable) {
                        NodoPosicion izquierda = root.izq.primeros.inicio;
                        while (izquierda != null) {
                            root.AgregarPrimero(izquierda.posicion);
                            izquierda = izquierda.sig;
                        }
                        NodoPosicion derecha = root.der.primeros.inicio;
                        while (derecha != null) {
                            root.AgregarPrimero(derecha.posicion);
                            derecha = derecha.sig;
                        }
                    } else {
                        NodoPosicion izquierda = root.izq.primeros.inicio;
                        while (izquierda != null) {
                            root.AgregarPrimero(izquierda.posicion);
                            izquierda = izquierda.sig;
                        }
                    }

                } else if (root.tipo.equals("KLEENE")) {
                    NodoPosicion derecha = root.der.primeros.inicio;
                    while (derecha != null) {
                        root.AgregarPrimero(derecha.posicion);
                        derecha = derecha.sig;
                    }

                } else if (root.tipo.equals("POSITIVE1")) {
                    NodoPosicion derecha = root.der.primeros.inicio;
                    while (derecha != null) {
                        root.AgregarPrimero(derecha.posicion);
                        derecha = derecha.sig;
                    }

                } else if (root.tipo.equals("OPTIONAL1")) {
                    NodoPosicion derecha = root.der.primeros.inicio;
                    while (derecha != null) {
                        root.AgregarPrimero(derecha.posicion);
                        derecha = derecha.sig;
                    }
                }
            }
        }
    }

    public void Ultimos(NodoArbol root) {
        
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
