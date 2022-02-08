/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_olc;

import analisis.parser;
import analisis.scanner;
import estructuras.ListaConjuntos;
import estructuras.ListaErrores;
import estructuras.ListaExpRegular;
import estructuras.ListaExpresiones;
import estructuras.NodoError;
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
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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

    public Inicio() {

        initComponents();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto1");
        setAlwaysOnTop(true);
        setBackground(java.awt.SystemColor.activeCaption);
        setResizable(false);

        jLabel1.setText("Archivo de Entrada");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Salida");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("Generar Automatas");

        jButton2.setText("Analizar Entrada");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton1)
                        .addGap(40, 40, 40)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
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
            System.out.println("");
            ListaExpRegular LR=regularExpression;
            ListaConjuntos c=conjuntos;
            System.out.println("");
            

            File[] lista = null;

            File directorio = new File("/ERRORES_202003919");
            if (!directorio.exists()) {
                if (!directorio.mkdirs()) {
                    JOptionPane.showMessageDialog(null, "error al crear el directorio");
                }
            } else {
                lista = directorio.listFiles();
            }

            File f;
            if (lista == null) {
                f = new File("/ERRORES_202003919/errores.html");
            } else {
                if (lista.length == 0) {
                    f = new File("/ERRORES_202003919/errores.html");
                } else {
                    f = new File("/ERRORES_202003919/errores" + lista.length + ".html");
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
