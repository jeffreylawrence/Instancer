/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.gui;

import com.modern.instancer.data.Workshift;
import com.modern.instancer.data.WorkshiftListProviderIntf;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kevinlawrence
 */
public final class WorkshiftTransformEditorPanel extends javax.swing.JPanel {

//<editor-fold defaultstate="collapsed" desc="Properties">
    private Workshift workshift;
    private Workshift transform;
    private WorkshiftListProviderIntf workshiftListProvider;

    /**
     * @return the workshift
     */
    public Workshift getWorkshift() {
        return workshift;
    }

    /**
     * @param workshift the workshift to set
     */
    public void setWorkshift(Workshift workshift) {
        this.workshift = workshift;
        updateWorkshiftDisplay();
    }

    /**
     * @return the transform
     */
    public Workshift getTransform() {
        return transform;
    }

    /**
     * @param transform the transform to set
     */
    public void setTransform(Workshift transform) {
        this.transform = transform;
        updateTransformDisplay();
    }

    /**
     * @param workshiftListProvider the offsetListProvider to set
     */
    public void setWorkshiftListProvider(WorkshiftListProviderIntf workshiftListProvider) {
        this.workshiftListProvider = workshiftListProvider;
        updateWorkshiftListDisplay();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Data Update Methods">
    public void updateWorkshiftListNavigation() {
        jbtnPreviousWorkshift.setEnabled(jcbxWorkshift.getItemCount() > 0);
        jbtnNextWorkshift.setEnabled(jcbxWorkshift.getItemCount() > 0);

        //TODO: remove later
        jbtnPreviousWorkshift.setVisible(false);
        jbtnNextWorkshift.setVisible(false);
    }

    public void updateWorkshiftListDisplay() {
        //setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbxWorkshift.removeAllItems();

        if (workshiftListProvider != null) {
            workshiftListProvider.getWorkshiftIDsSorted().stream().forEachOrdered(offsetID -> jcbxWorkshift.addItem(offsetID));
        }
        updateWorkshiftListNavigation();
    }

    public void updateWorkshiftDisplay() {
                if (workshift != null) {
//            jtxtWorkshiftBaseName.setText(workshift.getNameBase());
//            jspinWorkshiftInstance.setValue(workshift.getInstance());
//
//            jcbxWorkshift180DegreeRotation.setSelected(workshift.isRotate180Degrees());
//
            jtxtWorkshiftX.setText(String.valueOf(workshift.getX()));
            jtxtWorkshiftY.setText(String.valueOf(workshift.getY()));
            jtxtWorkshiftZ.setText(String.valueOf(workshift.getZ()));
            jtxtWorkshiftA.setText(String.valueOf(workshift.getA()));
            jtxtWorkshiftC.setText(String.valueOf(workshift.getC()));
        }

//        jtxtBaseName.setText("");
//        jcbx180DegreeRotation.setSelected(false);
//        
//        if (workshift != null) {
////            this.s(workshift.getWorkshiftID());
//            jtxtBaseName.setText(workshift.getNameBase());
//            jspinInstance.setValue(workshift.getInstance());
//            jcbx180DegreeRotation.setSelected(workshift.isRotate180Degrees());
//            this.s(workshift.getWorkshiftID());
//            jtxtTransformBaseName.setText(workshift.getNameBase());
//            jspinTransformInstance.setValue(workshift.getInstance());
//            jcbxTransform180DegreeRotation.setSelected(workshift.isRotate180Degrees());
//        }
    }

    public void updateWorkshiftData() {
//        if (workshift != null) {
////            workshift.setNameBase(jtxtWorkshiftBaseName.getText());
//            workshift.setInstance((int) jspinWorkshiftInstance.getValue());
//
//            workshift.setRotate180Degrees(jcbxWorkshift180DegreeRotation.isSelected());
//
//            workshift.setX(Double.valueOf(jtxtWorkshiftX.getText()));
//            workshift.setY(Double.valueOf(jtxtWorkshiftY.getText()));
//            workshift.setZ(Double.valueOf(jtxtWorkshiftZ.getText()));
//            workshift.setA(Double.valueOf(jtxtWorkshiftA.getText()));
//            workshift.setC(Double.valueOf(jtxtWorkshiftC.getText()));
//        }
    }

    private void updateTransformDisplay() {
        if (transform != null) {
            jtxtTransformBaseName.setText(transform.getNameBase());
            jspinTransformInstance.setValue(transform.getInstance());

            jcbxTransform180DegreeRotation.setSelected(transform.isRotate180Degrees());

            jtxtTransformX.setText(String.valueOf(transform.getX()));
            jtxtTransformY.setText(String.valueOf(transform.getY()));
            jtxtTransformZ.setText(String.valueOf(transform.getZ()));
            jtxtTransformA.setText(String.valueOf(transform.getA()));
            jtxtTransformC.setText(String.valueOf(transform.getC()));
        }
    }

    private void updateTransformData() {
        if (transform != null) {
            transform.setNameBase(jtxtTransformBaseName.getText());
            transform.setInstance((int) jspinTransformInstance.getValue());

            transform.setRotate180Degrees(jcbxTransform180DegreeRotation.isSelected());

            transform.setX(Double.valueOf(jtxtTransformX.getText()));
            transform.setY(Double.valueOf(jtxtTransformY.getText()));
            transform.setZ(Double.valueOf(jtxtTransformZ.getText()));
            transform.setA(Double.valueOf(jtxtTransformA.getText()));
            transform.setC(Double.valueOf(jtxtTransformC.getText()));
        }
    }
    
    public void updateData(){
        updateTransformData();
        updateWorkshiftDisplay();  //TODO not sure if will allow update...
    }
//</editor-fold>

    
//<editor-fold defaultstate="collapsed" desc="Factory">
    //<editor-fold defaultstate="collapsed" desc="WorkshiftTransformEditorResult">
    public interface WorkshiftTransformEditorResultIntf {
        public int getDialogResult();
        
        public Workshift getWorkshift();
        public Workshift getTransform();
    }
    
    public static class WorkshiftTransformEditorResult implements WorkshiftTransformEditorResultIntf {
        //<editor-fold defaultstate="collapsed" desc="Constructor">
        public WorkshiftTransformEditorResult(int dialogResult, Workshift workshift, Workshift transform){
            this.dialogResult = dialogResult;
            
            this.workshift = workshift;
            this.transform = transform;
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Properties">
        private int dialogResult;
        private Workshift workshift;
        private Workshift transform;
        
        /**
         * @return the dialogResult
         */
        public int getDialogResult() {
            return dialogResult;
        }
        
        /**
         * @return the workshift
         */
        public Workshift getWorkshift() {
            return workshift;
        }
        
        /**
         * @return the transform
         */
        public Workshift getTransform() {
            return transform;
        }
        //</editor-fold>
    }
    //</editor-fold>
    
    
    
    public static WorkshiftTransformEditorResult showWorkshiftTransformEditor(Workshift workshift, Workshift transform){
//    public static WorkshiftTransformEditorResult showWorkshiftTransformEditor(Workshift workshift, Workshift transform, ImageIcon icon){
        WorkshiftTransformEditorPanel panel = new WorkshiftTransformEditorPanel(workshift, transform);
//        Integer mine = 11;
//        ImageIcon icon = new ImageIcon(Integer.class.getResource("/instancer_icon_mill.png"));
//        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("instancer_icon_250x250.png")));

//        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Integer.class.getClass().getResource("instancer_icon_250x250.png")));
//        ImageIcon icon = new ImageIcon("/instancer_icon_250x250.png");

//        JOptionPane.showMessageDialog(null,
//                panel,
////    "Eggs are not supposed to be green.",
//    "Inane custom dialog",
//    JOptionPane.INFORMATION_MESSAGE,
//    icon);

//        JOptionPane.showMessageDialog(null,
//    "Eggs are not supposed to be green.",
//    "Inane custom dialog",
//    JOptionPane.INFORMATION_MESSAGE,
//    icon);
//        JOptionPane.s
        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Workshift Transform", JOptionPane.OK_CANCEL_OPTION);
//        int result = JOptionPane.showMessageDialog(panel, "Edit Workshift Transform", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            panel.updateData();
        }
        
        return new WorkshiftTransformEditorResult(result, panel.getWorkshift(), panel.getTransform());//panel.getWorkshift(), panel.getTransform());
    }
//</editor-fold>
    
    /**
     * Creates new form InstanceEditorPanel
     * @param workshift
     * @param transform
     */
    public WorkshiftTransformEditorPanel(Workshift workshift, Workshift transform) {
        initComponents();

        setWorkshift(workshift);
        setTransform(transform);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlOriginalOffset = new javax.swing.JPanel();
        lblParentWorkshiftID = new javax.swing.JLabel();
        jcbxWorkshift = new javax.swing.JComboBox<>();
        jbtnPreviousWorkshift = new javax.swing.JButton();
        jbtnNextWorkshift = new javax.swing.JButton();
        jlblX1 = new javax.swing.JLabel();
        jtxtWorkshiftX = new javax.swing.JTextField();
        jlblY1 = new javax.swing.JLabel();
        jtxtWorkshiftY = new javax.swing.JTextField();
        jlblZ1 = new javax.swing.JLabel();
        jtxtWorkshiftZ = new javax.swing.JTextField();
        jlblA1 = new javax.swing.JLabel();
        jtxtWorkshiftA = new javax.swing.JTextField();
        jlblC1 = new javax.swing.JLabel();
        jtxtWorkshiftC = new javax.swing.JTextField();
        jpnlWorkshiftTransform = new javax.swing.JPanel();
        jlblOffsetInstanceID = new javax.swing.JLabel();
        jtxtTransformBaseName = new javax.swing.JTextField();
        jcbxTransform180DegreeRotation = new javax.swing.JCheckBox();
        jspinTransformInstance = new javax.swing.JSpinner();
        jlblX = new javax.swing.JLabel();
        jtxtTransformX = new javax.swing.JTextField();
        jlblY = new javax.swing.JLabel();
        jtxtTransformY = new javax.swing.JTextField();
        jlblZ = new javax.swing.JLabel();
        jtxtTransformZ = new javax.swing.JTextField();
        jlblA = new javax.swing.JLabel();
        jtxtTransformA = new javax.swing.JTextField();
        jlblC = new javax.swing.JLabel();
        jtxtTransformC = new javax.swing.JTextField();

        lblParentWorkshiftID.setText("Workshift ID (Parent)");

        jcbxWorkshift.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnPreviousWorkshift.setText("<");
        jbtnPreviousWorkshift.setToolTipText("Previous Original Offset");

        jbtnNextWorkshift.setText(">");
        jbtnNextWorkshift.setToolTipText("Next Original Offset");

        jlblX1.setText("X");

        jtxtWorkshiftX.setEditable(false);
        jtxtWorkshiftX.setBackground(new java.awt.Color(204, 204, 204));
        jtxtWorkshiftX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtWorkshiftX.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblY1.setText("Y");

        jtxtWorkshiftY.setEditable(false);
        jtxtWorkshiftY.setBackground(new java.awt.Color(204, 204, 204));
        jtxtWorkshiftY.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtWorkshiftY.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblZ1.setText("Z");

        jtxtWorkshiftZ.setEditable(false);
        jtxtWorkshiftZ.setBackground(new java.awt.Color(204, 204, 204));
        jtxtWorkshiftZ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtWorkshiftZ.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblA1.setText("A");

        jtxtWorkshiftA.setEditable(false);
        jtxtWorkshiftA.setBackground(new java.awt.Color(204, 204, 204));
        jtxtWorkshiftA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtWorkshiftA.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblC1.setText("C");

        jtxtWorkshiftC.setEditable(false);
        jtxtWorkshiftC.setBackground(new java.awt.Color(204, 204, 204));
        jtxtWorkshiftC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtWorkshiftC.setPreferredSize(new java.awt.Dimension(100, 28));

        javax.swing.GroupLayout jpnlOriginalOffsetLayout = new javax.swing.GroupLayout(jpnlOriginalOffset);
        jpnlOriginalOffset.setLayout(jpnlOriginalOffsetLayout);
        jpnlOriginalOffsetLayout.setHorizontalGroup(
            jpnlOriginalOffsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlOriginalOffsetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlOriginalOffsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnlOriginalOffsetLayout.createSequentialGroup()
                        .addComponent(jlblX1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtWorkshiftX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblY1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtWorkshiftY, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblZ1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtWorkshiftZ, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblA1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtWorkshiftA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlOriginalOffsetLayout.createSequentialGroup()
                        .addComponent(lblParentWorkshiftID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxWorkshift, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblC1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlOriginalOffsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlOriginalOffsetLayout.createSequentialGroup()
                        .addComponent(jbtnPreviousWorkshift, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnNextWorkshift, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtxtWorkshiftC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnlOriginalOffsetLayout.setVerticalGroup(
            jpnlOriginalOffsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlOriginalOffsetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlOriginalOffsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblParentWorkshiftID)
                    .addComponent(jcbxWorkshift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNextWorkshift)
                    .addComponent(jbtnPreviousWorkshift))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlOriginalOffsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblX1)
                    .addComponent(jtxtWorkshiftX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblY1)
                    .addComponent(jtxtWorkshiftY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblZ1)
                    .addComponent(jtxtWorkshiftZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblA1)
                    .addComponent(jtxtWorkshiftA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblC1)
                    .addComponent(jtxtWorkshiftC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlWorkshiftTransform.setForeground(new java.awt.Color(255, 255, 255));

        jlblOffsetInstanceID.setText("Transform ID");

        jtxtTransformBaseName.setPreferredSize(new java.awt.Dimension(100, 28));

        jcbxTransform180DegreeRotation.setText("180 Rotation?");

        jspinTransformInstance.setModel(new javax.swing.SpinnerNumberModel(1, 0, 300, 1));

        jlblX.setText("X");

        jtxtTransformX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtTransformX.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblY.setText("Y");

        jtxtTransformY.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtTransformY.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblZ.setText("Z");

        jtxtTransformZ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtTransformZ.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblA.setText("A");

        jtxtTransformA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtTransformA.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblC.setText("C");

        jtxtTransformC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtTransformC.setPreferredSize(new java.awt.Dimension(100, 28));

        javax.swing.GroupLayout jpnlWorkshiftTransformLayout = new javax.swing.GroupLayout(jpnlWorkshiftTransform);
        jpnlWorkshiftTransform.setLayout(jpnlWorkshiftTransformLayout);
        jpnlWorkshiftTransformLayout.setHorizontalGroup(
            jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                        .addComponent(jlblX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtTransformX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblY)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtTransformY, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                        .addComponent(jlblOffsetInstanceID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtTransformBaseName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblZ)
                .addGap(7, 7, 7)
                .addGroup(jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jspinTransformInstance)
                    .addComponent(jtxtTransformZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                        .addComponent(jlblA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtTransformA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtTransformC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jcbxTransform180DegreeRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpnlWorkshiftTransformLayout.setVerticalGroup(
            jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                .addGroup(jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblOffsetInstanceID)
                    .addComponent(jtxtTransformBaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxTransform180DegreeRotation)
                    .addComponent(jspinTransformInstance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblX)
                    .addComponent(jtxtTransformX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblY)
                    .addComponent(jtxtTransformY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblZ)
                    .addComponent(jtxtTransformZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblA)
                    .addComponent(jtxtTransformA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblC)
                    .addComponent(jtxtTransformC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlOriginalOffset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlWorkshiftTransform, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnlOriginalOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlWorkshiftTransform, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnNextWorkshift;
    private javax.swing.JButton jbtnPreviousWorkshift;
    private javax.swing.JCheckBox jcbxTransform180DegreeRotation;
    private javax.swing.JComboBox<String> jcbxWorkshift;
    private javax.swing.JLabel jlblA;
    private javax.swing.JLabel jlblA1;
    private javax.swing.JLabel jlblC;
    private javax.swing.JLabel jlblC1;
    private javax.swing.JLabel jlblOffsetInstanceID;
    private javax.swing.JLabel jlblX;
    private javax.swing.JLabel jlblX1;
    private javax.swing.JLabel jlblY;
    private javax.swing.JLabel jlblY1;
    private javax.swing.JLabel jlblZ;
    private javax.swing.JLabel jlblZ1;
    private javax.swing.JPanel jpnlOriginalOffset;
    private javax.swing.JPanel jpnlWorkshiftTransform;
    private javax.swing.JSpinner jspinTransformInstance;
    private javax.swing.JTextField jtxtTransformA;
    private javax.swing.JTextField jtxtTransformBaseName;
    private javax.swing.JTextField jtxtTransformC;
    private javax.swing.JTextField jtxtTransformX;
    private javax.swing.JTextField jtxtTransformY;
    private javax.swing.JTextField jtxtTransformZ;
    private javax.swing.JTextField jtxtWorkshiftA;
    private javax.swing.JTextField jtxtWorkshiftC;
    private javax.swing.JTextField jtxtWorkshiftX;
    private javax.swing.JTextField jtxtWorkshiftY;
    private javax.swing.JTextField jtxtWorkshiftZ;
    private javax.swing.JLabel lblParentWorkshiftID;
    // End of variables declaration//GEN-END:variables

}
