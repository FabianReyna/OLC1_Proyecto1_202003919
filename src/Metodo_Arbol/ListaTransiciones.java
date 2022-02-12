/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo_Arbol;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author fabian
 */
public class ListaTransiciones {

    public NodoTransicion inicio;
    public NodoTransicion fin;
    public int size;

    public ListaTransiciones() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void AgregarEstado(ListaPosiciones ls, ListaTerminales lt) {
        NodoTransicion nuevo = new NodoTransicion(this.size);
        nuevo.terminales = lt;
        nuevo.pos = ls;
        if (this.inicio == null) {
            this.inicio = nuevo;
        }
        if (this.fin != null) {
            this.fin.sig = nuevo;
        }
        this.fin = nuevo;
        this.size += 1;
    }

    public boolean VerificaEstado(ListaPosiciones nt) {
        NodoTransicion aux = this.inicio;
        while (aux != null) {
            if (aux.pos.SonIguales(nt)) {
                return false;
            }
            aux = aux.sig;
        }
        return true;
    }

    public int ObtenerEstado(ListaPosiciones pos) {
        NodoTransicion aux = this.inicio;
        while (aux != null) {
            if (aux.pos.SonIguales(pos)) {
                return aux.estado;
            }
            aux = aux.sig;
        }
        return -1;
    }

    public void CargaSiguientes(ListaSiguientes siguientes, NodoArbol raiz) {
        ListaTerminales lt = new ListaTerminales();
        NodoSiguientes aux = siguientes.inicio;

        while (aux != null) {
            lt.AgregaTerminal(aux.Terminal);
            aux = aux.sig;
        }

        this.AgregarEstado(raiz.primeros, lt);

        NodoTransicion aux2 = this.inicio;
        while (aux2 != null) {
            System.out.println("" + aux2.estado);

            NodoPosicion position = aux2.pos.inicio;
            while (position != null) {
                String ayuda = siguientes.BuscaTerminal(position.posicion);
                NodoTerminal termina = aux2.terminales.BuscaTerminal(ayuda);

                ListaPosiciones variable = siguientes.BuscaSiguientes(position.posicion);
                if (variable != null) {
                    NodoPosicion auxVar = variable.inicio;

                    while (auxVar != null) {
                        termina.listapos.AgregarPosiciones(auxVar.posicion);
                        auxVar = auxVar.sig;
                    }
                }
                position = position.sig;
            }

            NodoTerminal auxilio = aux2.terminales.inicio;
            while (auxilio != null) {
                if (auxilio.listapos.inicio != null) {
                    if (this.VerificaEstado(auxilio.listapos)) {
                        lt = new ListaTerminales();
                        aux = siguientes.inicio;
                        while (aux != null) {
                            lt.AgregaTerminal(aux.Terminal);
                            aux = aux.sig;
                        }
                        this.AgregarEstado(auxilio.listapos, lt);
                    }
                }

                auxilio = auxilio.sig;
            }
            aux2 = aux2.sig;
        }

        aux2 = this.inicio;
        while (aux2 != null) {
            NodoTerminal aux3 = aux2.terminales.inicio;
            while (aux3 != null) {
                aux3.estado = this.ObtenerEstado(aux3.listapos);
                aux3 = aux3.sig;
            }
            aux2 = aux2.sig;
        }
    }

    public void ReporteTransiciones() throws FileNotFoundException, DocumentException {
        File[] lista = null;
        int numero = 0;
        String directoryName = System.getProperty("user.dir");

        File directorio = new File(directoryName + "/TRANSICIONES_202003919");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null, "error al crear el directorio");

            }
        } else {
            lista = directorio.listFiles();
        }

        if (lista == null) {
            numero = -1;
        } else {
            if (lista.length == 0) {
                numero = -1;
            } else {
                numero = lista.length;
            }
        }

        Document documento = new Document();
        FileOutputStream ficheroPdf;
        if (numero != -1) {
            ficheroPdf = new FileOutputStream(directoryName + "/TRANSICIONES_202003919/Transiciones.pdf");
        } else {
            ficheroPdf = new FileOutputStream(directoryName + "/TRANSICIONES_202003919/Transiciones" + numero + ".pdf");
        }

        PdfWriter.getInstance(documento, ficheroPdf);
        documento.open();
        int sizeTable = 1 + this.inicio.terminales.size;
        PdfPTable tabla = new PdfPTable(sizeTable);
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);

        Phrase estad = new Phrase("Estado", boldFont);
        tabla.addCell(estad);

        NodoTerminal aux = this.inicio.terminales.inicio;
        while (aux != null) {
            Phrase termina = new Phrase(aux.terminal, boldFont);
            tabla.addCell(termina);
            aux = aux.sig;
        }

        Font boldNormal = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        NodoTransicion aux2 = this.inicio;
        while (aux2 != null) {
            Phrase es = new Phrase("S" + aux2.estado+"{"+aux2.pos.CadenaListada()+"}", boldNormal);
            tabla.addCell(es);
            aux = aux2.terminales.inicio;
            while (aux != null) {
                Phrase te;
                if (aux.estado == -1) {
                    te = new Phrase("-", boldNormal);
                    tabla.addCell(te);
                } else {
                    te = new Phrase("S" + aux.estado, boldNormal);
                    tabla.addCell(te);
                }
                aux = aux.sig;
            }
            aux2 = aux2.sig;
        }
        documento.add(tabla);
        documento.close();
    }
}
