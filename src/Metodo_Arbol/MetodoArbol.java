/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo_Arbol;

import com.itextpdf.text.DocumentException;
import estructuras.ListaExpresiones;
import estructuras.NodoConjunto;
import estructuras.NodoExpresion;
import java.io.FileNotFoundException;
import java.io.IOException;
import static proyecto1_olc.Proyecto1_OLC.conjuntos;

/**
 *
 * @author fabian
 */
public class MetodoArbol {

    public String identificador;
    public ListaExpresiones expresiones;
    public Arbol a;
    public ListaSiguientes siguientes;
    public ListaTransiciones transiciones;
    private String cadena;

    public MetodoArbol(ListaExpresiones expresiones, String id, String cadena) {
        this.identificador = id;
        this.expresiones = expresiones;
        this.a = new Arbol(id);
        this.CargarArbol();
        this.siguientes = new ListaSiguientes(id);
        this.transiciones = new ListaTransiciones(id);
        this.cadena = cadena;
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

    public boolean EscaneaCadena(String cadena) {
        int estadoActual = 0;
        String buffer = "";

        for (int i = 1; i < cadena.length(); i++) {
            if (estadoActual == -1) {
                return false;
            }
            char c = cadena.charAt(i);
            NodoTransicion estado = this.transiciones.ObtenerEstadoNum(estadoActual);
            NodoTerminal aux2 = estado.terminales.inicio;
            buffer += c;
            while (aux2 != null) {
                if (buffer.length() > 1) {
                    if (aux2.terminal.equals(buffer)) {
                        estadoActual = aux2.estado;
                        buffer = "";
                        break;
                    }
                } else {
                    if (conjuntos.VerificaConjunto(aux2.terminal)) {
                        NodoConjunto conj = conjuntos.ObtenerConjunto(aux2.terminal);
                        if (conjuntos.CumpleRegla(conj, c)) {
                            estadoActual = aux2.estado;
                            buffer = "";
                            break;
                        }
                    } else {

                        if (aux2.terminal.equals(String.valueOf(c))) {
                            estadoActual = aux2.estado;
                            buffer = "";
                            break;
                        }

                    }
                }

                aux2 = aux2.sig;
            }
        }

        return this.transiciones.EstadoAceptacion(estadoActual);
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
        this.transiciones.AFD_Graphviz();
        System.out.println("" + this.EscaneaCadena(this.cadena));
    }

}
