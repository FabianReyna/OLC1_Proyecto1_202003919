/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo_Arbol;

import estructuras.ListaExpresiones;
import estructuras.NodoExpresion;

/**
 *
 * @author fabian
 */
public class MetodoArbol {

    public ListaExpresiones expresiones;
    public Arbol a;
    public ListaSiguientes siguientes;

    public MetodoArbol(ListaExpresiones expresiones) {
        this.expresiones = expresiones;
        this.a=new Arbol();
        this.CargarArbol();
        this.siguientes=new ListaSiguientes();
    }

    public void CargarArbol() {
        NodoExpresion aux = this.expresiones.inicio;
        this.a.AgregarNodo(".", "CONCAT1");
        while (aux != null) {
            this.a.AgregarNodo(aux.lexema, aux.tipo);
            aux=aux.sig;
        }
        this.a.AgregarNodo("#", "FINCADENA");
    }
    
    public void Ejecutar(){
    this.a.Identifica_Hojas(this.a.raiz);
    this.a.Anulables(this.a.raiz);
    this.a.Primeros(this.a.raiz);
    this.a.Ultimos(this.a.raiz);
    this.siguientes.CrearNodos(this.a.raiz);
    this.siguientes.AgregandoSiguientes(this.a.raiz);
    }

}
