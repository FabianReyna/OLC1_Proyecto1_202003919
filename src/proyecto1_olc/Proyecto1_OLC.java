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
    public static ListaExpRegular regularExpression;
    public static ListaConjuntos conjuntos;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Inicio in = new Inicio();
        in.setVisible(true);


        errores = new ListaErrores();
        regularExpression = new ListaExpRegular();
        conjuntos = new ListaConjuntos();
        /*
        
        Arbol a = new Arbol();
        Arbol a2 = new Arbol();*/
        
         
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
        a2.AgregarNodo(".", "CONCAT1");
        a2.AgregarNodo(".", "CONCAT1");
        a2.AgregarNodo(".", "CONCAT1");
        a2.AgregarNodo("a", "TERMINAL"); 
        a2.AgregarNodo("*", "KLEENE");
        a2.AgregarNodo("|", "OR1");
        a2.AgregarNodo("a", "TERMINAL");
        a2.AgregarNodo("b", "TERMINAL");
        a2.AgregarNodo("b", "TERMINAL");
        a2.AgregarNodo("#", "FINCADENA");
        a2.Identifica_Hojas(a2.raiz);
        a2.Anulables(a2.raiz);
        a2.Primeros(a2.raiz);
        a2.Ultimos(a2.raiz);*/
 
        /*
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo("+", "POSITIVE1");
        a.AgregarNodo("|", "OR1");
        a.AgregarNodo("|", "OR1");
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo("c", "TERMINAL");
        a.AgregarNodo("|", "OR1");
        a.AgregarNodo("o", "TERMINAL");
        a.AgregarNodo("e", "TERMINAL");
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo("d", "TERMINAL");
        a.AgregarNodo("m", "TERMINAL");
        a.AgregarNodo(".", "CONCAT1");
        a.AgregarNodo("a", "TERMINAL");
        a.AgregarNodo("|", "OR1");
        a.AgregarNodo("n", "TERMINAL");
        a.AgregarNodo("L", "TERMINAL");
        a.AgregarNodo("#", "FINCADENA");
        a.Identifica_Hojas(a.raiz);
        a.Anulables(a.raiz);*/
        
        System.out.println("");

    }

}
