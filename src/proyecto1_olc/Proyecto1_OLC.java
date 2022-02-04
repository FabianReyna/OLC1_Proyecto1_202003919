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
        /*Inicio in=new Inicio();
        in.setVisible(true);
        String cadena="\\\\";
        System.out.println(cadena);
        NodoError nuevo;*/
        try {
            String texto="{CONJ:letra->a~z;\n ExpReg1->.{letra}*|\"_\"{letra};\n\n%%\nExpReg1:\"primerLexema\";}";
            System.out.println("Inicia el analisis...\n");
            scanner scan = new scanner(new BufferedReader( new StringReader(texto)));
            System.out.println("");
            
            
            parser parser = new parser(scan);
            parser.parse();
           
            
            System.out.println("");
            System.out.println("Finaliza el analisis...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
