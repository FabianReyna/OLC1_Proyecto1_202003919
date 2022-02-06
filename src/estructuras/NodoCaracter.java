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
public class NodoCaracter {

    public String caracter;
    public NodoCaracter sig = null;

    public NodoCaracter(String caracter) {
        this.caracter = caracter;
    }

    public int ascii() {
        char b = this.caracter.charAt(0);
        int c = (int) b;
        return c;
    }

}
