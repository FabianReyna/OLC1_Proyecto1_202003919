/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_olc;

import Metodo_Arbol.MetodoArbol;
import analisis.parser;
import analisis.scanner;
import com.itextpdf.text.DocumentException;
import estructuras.ListaConjuntos;
import estructuras.ListaErrores;
import estructuras.ListaExpRegular;
import estructuras.ListaExpresiones;
import estructuras.NodoError;
import estructuras.NodoExpRegular;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.JSONArray;
import org.json.JSONObject;
import static proyecto1_olc.Proyecto1_OLC.conjuntos;
import static proyecto1_olc.Proyecto1_OLC.errores;
import static proyecto1_olc.Proyecto1_OLC.regularExpression;

/**
 *
 * @author fabian
 */
public class Inicio extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form Inicio
     */
    JMenuBar mb = new JMenuBar();
    JMenu menu = new JMenu("Menu");

    JMenuItem i1 = new JMenuItem("Nuevo Archivo");
    JMenuItem i2 = new JMenuItem("Abrir Archivo");
    JMenuItem i3 = new JMenuItem("Guardar");
    JMenuItem i4 = new JMenuItem("Guardar Como");
    String actual_file = "";

    /*public void cargaListas() {
        String directoryName = System.getProperty("user.dir");
        jList1.clearSelection();
        jList2.clearSelection();
        jList3.clearSelection();
        jList4.clearSelection();

        File arboles = new File(directoryName + "/ARBOLES_202003919");
        if (arboles.exists()) {
            String[] lista_arboles = arboles.list();
            if (lista_arboles != null || lista_arboles.length != 0) {
                DefaultListModel modelo = new DefaultListModel();
                for (int i = 0; i < lista_arboles.length; i++) {
                    modelo.addElement(lista_arboles[i]);
                }
                jList1.setModel(modelo);
            }

        }

        File afds = new File(directoryName + "/AFD_202003919");
        if (afds.exists()) {
            String[] lista_afs = afds.list();
            if (lista_afs != null || lista_afs.length != 0) {
                DefaultListModel modelo2 = new DefaultListModel();
                for (int i = 0; i < lista_afs.length; i++) {
                    modelo2.addElement(lista_afs[i]);
                }
                jList2.setModel(modelo2);
            }
        }

        File sigs = new File(directoryName + "/SIGUIENTES_202003919");
        if (sigs.exists()) {
            String[] lista_sig = sigs.list();
            if (lista_sig != null || lista_sig.length != 0) {
                DefaultListModel modelo3 = new DefaultListModel();
                for (int i = 0; i < lista_sig.length; i++) {
                    modelo3.addElement(lista_sig[i]);
                }
                jList3.setModel(modelo3);

            }
        }

        File trans = new File(directoryName + "/TRANSICIONES_202003919");
        if (trans.exists()) {
            String[] lista_trans = trans.list();
            if (lista_trans != null || lista_trans.length != 0) {
                DefaultListModel modelo4 = new DefaultListModel();
                for (int i = 0; i < lista_trans.length; i++) {
                    modelo4.addElement(lista_trans[i]);
                }
                jList4.setModel(modelo4);
            }
        }

    }*/
    public Inicio() {

        initComponents();
        setLocationRelativeTo(null);

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);

        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.add(i4);

        mb.add(menu);
        this.setJMenuBar(mb);

    }

    public void actionPerformed(ActionEvent e) {
        jTextArea2.setText("");
        if (e.getSource() == i1) {
            actual_file = "";
            jTextArea1.setText("");
            
            System.out.println("nuevo archivo");
        } else if (e.getSource() == i2) {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo EXP", "EXP");
            fc.setFileFilter(filter);
            fc.showOpenDialog(this);
            File file = fc.getSelectedFile();
            {

            }
            if (file != null) {
                try {
                    actual_file = file.getPath();
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String texto = "";
                    while ((texto = br.readLine()) != null) {
                        jTextArea1.append(texto + "\n");
                    }
                    br.close();

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Archivo no encontrado");
                } catch (IOException ex) {
                }
            }
        } else if (e.getSource() == i3) {
            if (!actual_file.equals("")) {
                File f = new File(actual_file);
                try {
                    FileWriter br = new FileWriter(f);
                    BufferedWriter bw = new BufferedWriter(br);
                    PrintWriter pr = new PrintWriter(bw);
                    pr.write(jTextArea1.getText());
                    pr.close();
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JFileChooser fc = new JFileChooser();
                int userSelection = fc.showSaveDialog(this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fc.getSelectedFile();
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                    File f = new File(fileToSave.getAbsolutePath() + ".exp");
                    try {
                        FileWriter br = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(br);
                        PrintWriter pr = new PrintWriter(bw);
                        pr.write(jTextArea1.getText());
                        pr.close();
                        bw.close();
                        actual_file = fileToSave.getAbsolutePath() + ".exp";
                    } catch (IOException ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (e.getSource() == i4) {
            JFileChooser fc = new JFileChooser();
            int userSelection = fc.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fc.getSelectedFile();
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                File f = new File(fileToSave.getAbsolutePath() + ".exp");
                try {
                    FileWriter br = new FileWriter(f);
                    BufferedWriter bw = new BufferedWriter(br);
                    PrintWriter pr = new PrintWriter(bw);
                    pr.write(jTextArea1.getText());
                    pr.close();
                    bw.close();
                    actual_file = fileToSave.getAbsolutePath() + ".exp";
                } catch (IOException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto1");
        setBackground(java.awt.SystemColor.activeCaption);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setText("Archivo de Entrada");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Salida");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("Generar Automatas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Analizar Entrada");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Arboles");
        jButton3.setMaximumSize(new java.awt.Dimension(123, 32));
        jButton3.setMinimumSize(new java.awt.Dimension(123, 32));
        jButton3.setPreferredSize(new java.awt.Dimension(123, 32));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("AFND");
        jButton4.setMaximumSize(new java.awt.Dimension(123, 32));
        jButton4.setMinimumSize(new java.awt.Dimension(123, 32));
        jButton4.setPreferredSize(new java.awt.Dimension(123, 32));

        jButton5.setText("Siguientes");
        jButton5.setMaximumSize(new java.awt.Dimension(123, 32));
        jButton5.setMinimumSize(new java.awt.Dimension(123, 32));
        jButton5.setPreferredSize(new java.awt.Dimension(123, 32));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Transiciones");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("AFN");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Errores");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Salidas");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jButton1)
                                .addGap(40, 40, 40)
                                .addComponent(jButton2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            errores = new ListaErrores();
            regularExpression = new ListaExpRegular();

            conjuntos = new ListaConjuntos();
            String texto = jTextArea1.getText();
            scanner scan = new scanner(new BufferedReader(new StringReader(texto)));

            parser parser = new parser(scan);
            parser.parse();
            JOptionPane.showMessageDialog(this, "Escaneo finalizado");

            ListaExpRegular ler = regularExpression;
            ListaConjuntos cc = conjuntos;

            String directoryName = System.getProperty("user.dir");
            File[] lista = null;

            File directorio = new File(directoryName + "/ERRORES_202003919");
            if (!directorio.exists()) {
                if (!directorio.mkdirs()) {
                    JOptionPane.showMessageDialog(null, "error al crear el directorio");
                }
            } else {
                lista = directorio.listFiles();
            }

            File f;
            if (lista == null) {
                f = new File(directoryName + "/ERRORES_202003919/errores.html");
            } else {
                if (lista.length == 0) {
                    f = new File(directoryName + "/ERRORES_202003919/errores.html");
                } else {
                    f = new File(directoryName + "/ERRORES_202003919/errores" + lista.length + ".html");
                }
            }

            try {
                FileWriter br = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(br);
                PrintWriter pr = new PrintWriter(bw);
                pr.write(errores.ReporteHTML());
                pr.close();
                bw.close();

            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NodoExpRegular ner = regularExpression.inicio;
        JSONArray ja = new JSONArray();
        while (ner != null) {

            MetodoArbol me = new MetodoArbol(ner.le, ner.id, ner.valor);
            try {
                me.Ejecutar();
                if (!(ner.valor.equals(""))) {
                    boolean validar = me.EscaneaCadena();
                    String txt = me.Salida(validar);

                    String txt2 = me.GenerandoJSON(validar);
                    JSONObject ob = new JSONObject(txt2);
                    ja.put(ob);
                    jTextArea2.append(txt + "\n");
                }
            } catch (IOException | DocumentException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }

            ner = ner.sig;
        }

        File[] lista = null;
        int numero = 0;
        String directoryName = System.getProperty("user.dir");

        File directorio = new File(directoryName + "/SALIDAS_202003919");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null, "error al crear el directorio");
            }
        } else {
            lista = directorio.listFiles();
        }

        File f;
        if (lista == null) {
            f = new File(directoryName + "/SALIDAS_202003919/salida.json");

            numero = -1;

        } else {
            if (lista.length == 0) {
                f = new File(directoryName + "/SALIDAS_202003919/salida.json");
                numero = -1;

            } else {
                f = new File(directoryName + "/SALIDAS_202003919/salida" + lista.length + ".json");
                numero = lista.length;
            }
        }
        try {
            FileWriter br = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(br);
            PrintWriter pr = new PrintWriter(bw);
            pr.write(ja.toString());
            pr.close();
            bw.close();

        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String directoryName = System.getProperty("user.dir");
            Desktop.getDesktop().open(new File(directoryName + "/ARBOLES_202003919"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Carpeta no existente");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            String directoryName = System.getProperty("user.dir");
            Desktop.getDesktop().open(new File(directoryName + "/SALIDAS_202003919/"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Carpeta no existente");

        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            String directoryName = System.getProperty("user.dir");
            Desktop.getDesktop().open(new File(directoryName + "/SIGUIENTES_202003919"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Carpeta no existente");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            String directoryName = System.getProperty("user.dir");
            Desktop.getDesktop().open(new File(directoryName + "/TRANSICIONES_202003919"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Carpeta no existente");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            String directoryName = System.getProperty("user.dir");
            Desktop.getDesktop().open(new File(directoryName + "/AFD_202003919"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Carpeta no existente");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            String directoryName = System.getProperty("user.dir");
            Desktop.getDesktop().open(new File(directoryName + "/ERRORES_202003919"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Carpeta no existente");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
