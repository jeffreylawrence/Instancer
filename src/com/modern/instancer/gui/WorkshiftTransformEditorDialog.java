/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.gui;

import com.modern.instancer.data.Workshift;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;

/**
 *
 * @author kevinlawrence
 */
public final class WorkshiftTransformEditorDialog extends javax.swing.JDialog {
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    WorkshiftTransformEditorPanel jpnlWorkshiftTransformEditor;
    
    /**
     * @return the workshift
     */
    public Workshift getWorkshift() {
        return jpnlWorkshiftTransformEditor.getWorkshift();
    }
    
    /**
     * @param workshift the workshift to set
     */
    public void setWorkshift(Workshift workshift) {
        jpnlWorkshiftTransformEditor.setWorkshift(workshift);
    }
    
    /**
     * @return the transform
     */
    public Workshift getTransform() {
        return jpnlWorkshiftTransformEditor.getTransform();
    }
    
    /**
     * @param transform the transform to set
     */
    public void setTransform(Workshift transform) {
        jpnlWorkshiftTransformEditor.setTransform(transform);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Constructor & Init">
    /**
     * Creates new form WorkshiftTransformEditorDialog
     * @param parent
     * @param modal
     * @param workshift
     * @param transform
     */
    public WorkshiftTransformEditorDialog(java.awt.Frame parent, boolean modal,
            Workshift workshift, Workshift transform) {
        super(parent, modal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        initComponents();
        initCustomComponents(workshift, transform);
    }
    
    private void initCustomComponents(Workshift workshift, Workshift transform) {
        jpnlEditorHome.setLayout(new BorderLayout());

        jpnlWorkshiftTransformEditor = new WorkshiftTransformEditorPanel(workshift, transform);
        jpnlEditorHome.add(jpnlWorkshiftTransformEditor);
        jpnlWorkshiftTransformEditor.setVisible(true);
    }
    
    private void updateData() {
        if (jpnlWorkshiftTransformEditor != null) {
            jpnlWorkshiftTransformEditor.updateWorkshiftData();
        }
    }
    
    public void close(){
        setVisible(false);
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
//</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlEditorHome = new javax.swing.JPanel();
        jpnlControl = new javax.swing.JPanel();
        jbtnOK = new javax.swing.JButton();
        jbtnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jpnlEditorHomeLayout = new javax.swing.GroupLayout(jpnlEditorHome);
        jpnlEditorHome.setLayout(jpnlEditorHomeLayout);
        jpnlEditorHomeLayout.setHorizontalGroup(
            jpnlEditorHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );
        jpnlEditorHomeLayout.setVerticalGroup(
            jpnlEditorHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );

        jbtnOK.setText("OK");
        jbtnOK.setPreferredSize(new java.awt.Dimension(100, 29));
        jbtnOK.setSize(new java.awt.Dimension(100, 29));
        jbtnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnOKActionPerformed(evt);
            }
        });

        jbtnCancel.setText("Cancel");
        jbtnCancel.setPreferredSize(new java.awt.Dimension(100, 29));
        jbtnCancel.setSize(new java.awt.Dimension(100, 29));
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlControlLayout = new javax.swing.GroupLayout(jpnlControl);
        jpnlControl.setLayout(jpnlControlLayout);
        jpnlControlLayout.setHorizontalGroup(
            jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlControlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpnlControlLayout.setVerticalGroup(
            jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlEditorHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnlEditorHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnOKActionPerformed
        updateData();
        close();
    }//GEN-LAST:event_jbtnOKActionPerformed

    private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
        close();
    }//GEN-LAST:event_jbtnCancelActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(WorkshiftTransformEditorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(WorkshiftTransformEditorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(WorkshiftTransformEditorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(WorkshiftTransformEditorDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                WorkshiftTransformEditorDialog dialog = new WorkshiftTransformEditorDialog(new javax.swing.JFrame(), true, null, null);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnOK;
    private javax.swing.JPanel jpnlControl;
    private javax.swing.JPanel jpnlEditorHome;
    // End of variables declaration//GEN-END:variables



}
