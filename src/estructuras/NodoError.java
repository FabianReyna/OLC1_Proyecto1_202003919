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
public class NodoError {
    public String tipo;
    public String desc;
    public int linea;
    public int col;
    public NodoError sig=null;
    

    public NodoError(String tipo, String desc, int linea, int col) {
        this.tipo = tipo;
        this.desc = desc;
        this.linea = linea;
        this.col = col;
    }
    
    
}
