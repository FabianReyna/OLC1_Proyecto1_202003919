/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo_Arbol;

import com.itextpdf.text.DocumentException;
import estructuras.ListaExpresiones;
import estructuras.NodoExpresion;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author fabian
 */
public class MetodoArbol {

    public ListaExpresiones expresiones;
    public Arbol a;
    public ListaSiguientes siguientes;
    public ListaTransiciones transiciones;

    public MetodoArbol(ListaExpresiones expresiones) {
        this.expresiones = expresiones;
        this.a = new Arbol();
        this.CargarArbol();
        this.siguientes = new ListaSiguientes();
        this.transiciones = new ListaTransiciones();
    }

    public void CargarArbol() {
        NodoExpresion aux = this.expresiones.inicio;
        this.a.AgregarNodo(".", "CONCAT1");
        while (aux != null) {

            this.a.AgregarNodo(aux.lexema, aux.tipo);
            aux = aux.sig;
        }
        this.a.AgregarNodo("#", "FINCADENA");
    }

    public void Ejecutar() throws IOException, FileNotFoundException, DocumentException {
        this.a.Identifica_Hojas(this.a.raiz);
        this.a.Anulables(this.a.raiz);
        this.a.Primeros(this.a.raiz);
        this.a.Ultimos(this.a.raiz);
        this.siguientes.CrearNodos(this.a.raiz);
        this.siguientes.AgregandoSiguientes(this.a.raiz);
        this.a.GenerarReporteGraphviz();
        this.siguientes.GraficaPDF();
        this.transiciones.CargaSiguientes(this.siguientes, this.a.raiz);
        this.transiciones.ReporteTransiciones();

    }

}
