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
public class ListaErrores {

    public NodoError inicio;
    public NodoError fin;

    public ListaErrores() {
        this.inicio = null;
        this.fin = null;
    }

    public void NewError(String tipo, String desc, int linea, int col) {
        NodoError nuevo = new NodoError(tipo, desc, linea, col);
        if (this.inicio == null) {
            this.inicio = nuevo;
        } else {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
    }

    public String ReporteHTML() {
        String reporte;
        reporte = "<!DOCTYPE html>\n<html>\n<head>\n<title>Reporte de errores</title>\n";
        reporte += "<style type='text/css'>body{background-color:skyblue;}"
                + "th{background: Teal;color: white;} .dis{ background: LightSeaGreen;color: white;} "
                + ".dis2{background: MediumAquamarine;}</style>\n</head>\n<body>\n<center></br>";
        if (this.inicio != null) {
            reporte += "<table border='2'>\n<th>#</th>\n<th>Tipo de error</th>\n";
            reporte += "<th>Descripcion</th>\n<th>Linea</th>\n<th>Columna</th>\n";
            NodoError aux = this.inicio;
            int contador = 1;
            while (aux != null) {
                reporte += "<tr>\n<td class='dis2'>" + contador + "</td>\n<td class='dis2'>" + aux.tipo + "</td>\n";
                reporte += "<td class='dis2'>" + aux.desc + "</td>\n<td class='dis2'>" + aux.linea + "</td>\n";
                reporte += "<td class='dis2'>" + aux.col + "</td>\n</tr>";
                contador += 1;
                aux = aux.sig;
            }
            reporte += "</table>\n</center></body>\n</html>";
            return reporte;
        }
        reporte +="<h1>No se encontraron errores en el archivo de entrada :)</h1>\n";
        reporte += "</center></body>\n</html>";
        return reporte;

    }

}
