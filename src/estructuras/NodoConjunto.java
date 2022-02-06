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
public class NodoConjunto {

    public String nombre;
    public boolean rango;
    public ListaCaracteres regla;
    NodoConjunto sig = null;

    public NodoConjunto(String nombre, boolean rango, ListaCaracteres regla) {
        this.nombre = nombre;
        this.rango = rango;
        this.regla = new ListaCaracteres();
    }

    public void AgregaReglas(String caracter) {
        this.regla.AgregarCaracter(caracter);
    }

}
