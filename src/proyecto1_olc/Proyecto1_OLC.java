/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_olc;

import Metodo_Arbol.*;
import analisis.parser;
import analisis.scanner;
import estructuras.*;
import java.io.BufferedReader;
import java.io.StringReader;

/**
 *
 * @author fabian
 */
public class Proyecto1_OLC {

    public static ListaErrores errores;
    public static ListaExpresiones regularExpression;
    public static ListaConjuntos conjuntos;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Inicio in = new Inicio();
        in.setVisible(true);
        String cadena = "\\\\";
        System.out.println(cadena);

        errores = new ListaErrores();
        regularExpression = new ListaExpresiones();
        conjuntos = new ListaConjuntos();
        /*
        Arbol a = new Arbol();
        Arbol b = new Arbol();
         */
 /*
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo("digito", "TERMINAL");
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo(".", "TERMINAL");
        a.AgregarNodo("+", "POSITIVE1");
        a.AgregarNodo("digito", "TERMINAL");
        a.AgregarNodo("#", "FINCADENA");
         */
 /*
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo("l", "TERMINAL"); 
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo("*", "KLEENE");
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo("?", "OPTIONAL1");
        a.AgregarNodo("g", "TERMINAL");
        a.AgregarNodo("l", "TERMINAL");
        a.AgregarNodo("d", "TERMINAL");
        a.AgregarNodo("#", "FINCADENA");*/

    }

}
