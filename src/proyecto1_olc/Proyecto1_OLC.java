/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_olc;

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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Inicio in=new Inicio();
        in.setVisible(true);
        String cadena="\\\\";
        System.out.println(cadena);
       
        errores=new ListaErrores();
    }
    
}
