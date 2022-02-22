/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thompson;

/**
 *
 * @author fabian
 */
public class NodoDoble {

    public String lexema;
    public String tipo;
    public int id;
    public NodoDoble ant;
    public NodoDoble sig;

    public NodoDoble(String lexema, String tipo, int id) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.id = id;
        this.ant = null;
        this.sig = null;
    }
}
