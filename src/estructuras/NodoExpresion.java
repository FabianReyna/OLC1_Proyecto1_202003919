/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author fabian
 */
public class NodoExpresion {

    public String lexema;
    public String tipo;
    public String valor;
    public NodoExpresion sig = null;

    public NodoExpresion(String lexema, String tipo, String valor) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    
}
