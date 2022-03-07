/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;
import estructuras.*;
/**
 *
 * @author fabian
 */
public class NodoExpRegular {

    public String id;
    public ListaExpresiones le;
    public NodoExpRegular sig;
    public ListaCadenas valor;

    public NodoExpRegular(String id,ListaExpresiones le) {
        this.id = id;
        this.le = le;
        this.valor=new ListaCadenas();
        this.sig = null;
    }
}
