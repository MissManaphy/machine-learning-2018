/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

/**
 *
 * @author sophiaanderson
 */
public class PerceptronFrame extends javax.swing.JFrame {

    /**
     * Creates new form PerceptronFrame
     */
    
    public final PerceptronPanel panel = new PerceptronPanel();
    public PerceptronFrame() {
        initComponents();
        //add(new PerceptronPanel());
        add(panel);
        setTitle("Perceptron");
        setBounds(55,55,600,600);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        menuBar = new javax.swing.JMenuBar();
        dataSet = new javax.swing.JMenu();
        pi = new javax.swing.JMenuItem();
        w = new javax.swing.JMenuItem();
        z = new javax.swing.JMenuItem();
        options = new javax.swing.JMenu();
        reset = new javax.swing.JMenuItem();
        verbose = new javax.swing.JCheckBoxMenuItem();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dataSet.setText("Data Set");

        pi.setText("Pi");
        pi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piActionPerformed(evt);
            }
        });
        dataSet.add(pi);

        w.setText("W");
        w.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wActionPerformed(evt);
            }
        });
        dataSet.add(w);

        z.setText("Z");
        z.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zActionPerformed(evt);
            }
        });
        dataSet.add(z);

        menuBar.add(dataSet);

        options.setText("Options");

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        options.add(reset);

        verbose.setSelected(true);
        verbose.setText("Verbose");
        verbose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verboseActionPerformed(evt);
            }
        });
        options.add(verbose);

        menuBar.add(options);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        panel.reset();
    }//GEN-LAST:event_resetActionPerformed

    private void zActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zActionPerformed
        panel.setZ();
    }//GEN-LAST:event_zActionPerformed

    private void piActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piActionPerformed
        panel.setPi();
    }//GEN-LAST:event_piActionPerformed

    private void wActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wActionPerformed
        panel.setW();
    }//GEN-LAST:event_wActionPerformed

    private void verboseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verboseActionPerformed
        panel.setVerbose();
    }//GEN-LAST:event_verboseActionPerformed

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
            java.util.logging.Logger.getLogger(PerceptronFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerceptronFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerceptronFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerceptronFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PerceptronFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu dataSet;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu options;
    private javax.swing.JMenuItem pi;
    private javax.swing.JMenuItem reset;
    private javax.swing.JCheckBoxMenuItem verbose;
    private javax.swing.JMenuItem w;
    private javax.swing.JMenuItem z;
    // End of variables declaration//GEN-END:variables
}
