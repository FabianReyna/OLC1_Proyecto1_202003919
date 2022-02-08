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
    public NodoExpresion sig = null;
    public boolean conj;

    public NodoExpresion(String lexema, boolean b) {
        this.lexema = lexema;
        this.conj = b;
        switch (lexema) {
            case "*":
                this.tipo = "KLEENE";
                break;
            case "+":
                this.tipo = "POSITIVE1";
                break;
            case ".":
                this.tipo = "CONCAT1";
                break;
            case "?":
                this.tipo = "OPTIONAL1";
                break;
            case "|":
                this.tipo = "OR1";
                break;
            default:
                this.tipo = "TERMINAL";
                break;
        }
    }


}
