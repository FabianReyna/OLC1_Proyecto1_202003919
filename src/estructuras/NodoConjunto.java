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
    public ListaCaracteres regla=new ListaCaracteres();
    NodoConjunto sig = null;

    public NodoConjunto(String nombre) {
        this.nombre = nombre;
    } 

    public void setRango(boolean rango) {
        this.rango = rango;
    }
    

    public void AgregaReglas(String caracter) {
        this.regla.AgregarCaracter(caracter);
    }

}
