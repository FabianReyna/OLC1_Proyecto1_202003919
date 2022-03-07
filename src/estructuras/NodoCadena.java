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
public class NodoCadena {

    public String cadena;
    public NodoCadena sig;

    public NodoCadena(String cadena) {
        this.cadena = cadena;
        this.sig = null;
    }

}
