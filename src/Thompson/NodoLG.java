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
public class NodoLG {

    public int id;
    public Grafo g;
    public NodoLG sig;
    public NodoLG ant;

    public NodoLG(int id, Grafo g) {
        this.id = id;
        this.g = g;
        this.sig = null;
        this.ant = null;
    }

}
