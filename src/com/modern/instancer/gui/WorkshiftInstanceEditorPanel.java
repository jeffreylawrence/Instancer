/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.gui;

import com.modern.instancer.data.Workshift;
import com.modern.instancer.data.WorkshiftListProviderIntf;
import javax.swing.JOptionPane;

/**
 *
 * @author kevinlawrence
 */
public final class WorkshiftInstanceEditorPanel extends javax.swing.JPanel {

//<editor-fold defaultstate="collapsed" desc="Properties">
    private Workshift workshift;
    private Workshift instance;
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
     * @return the instance
     */
    public Workshift getInstance() {
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public void setInstance(Workshift instance) {
        this.instance = instance;
        updateInstanceDisplay();
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

    private void updateInstanceDisplay() {
        if (instance != null) {
            jtxtInstanceBaseName.setText(instance.getNameBase());
            jspinInstanceID.setValue(instance.getInstance());

            jcbxInstance180DegreeRotation.setSelected(instance.isRotate180Degrees());

            jtxtInstanceX.setText(String.valueOf(instance.getX()));
            jtxtInstanceY.setText(String.valueOf(instance.getY()));
            jtxtInstanceZ.setText(String.valueOf(instance.getZ()));
            jtxtInstanceA.setText(String.valueOf(instance.getA()));
            jtxtInstanceC.setText(String.valueOf(instance.getC()));
        }
    }

    private void updateInstanceData() {
        if (instance != null) {
            instance.setNameBase(jtxtInstanceBaseName.getText());
            instance.setInstance((int) jspinInstanceID.getValue());

            instance.setRotate180Degrees(jcbxInstance180DegreeRotation.isSelected());

            instance.setX(Double.valueOf(jtxtInstanceX.getText()));
            instance.setY(Double.valueOf(jtxtInstanceY.getText()));
            instance.setZ(Double.valueOf(jtxtInstanceZ.getText()));
            instance.setA(Double.valueOf(jtxtInstanceA.getText()));
            instance.setC(Double.valueOf(jtxtInstanceC.getText()));
        }
    }
    
    public void updateData(){
        updateInstanceData();
        updateWorkshiftDisplay();  //TODO not sure if will allow update...
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Factory">
    //<editor-fold defaultstate="collapsed" desc="WorkshiftInstanceEditorResult">
    public interface WorkshiftInstanceEditorResultIntf {
        public int getDialogResult();
        
        public Workshift getWorkshift();
        public Workshift getInstance();
    }
    
    public static class WorkshiftInstanceEditorResult implements WorkshiftInstanceEditorResultIntf {
        //<editor-fold defaultstate="collapsed" desc="Constructor">
        public WorkshiftInstanceEditorResult(int dialogResult, Workshift workshift, Workshift instance){
            this.dialogResult = dialogResult;
            
            this.workshift = workshift;
            this.instance = instance;
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Properties">
        private int dialogResult;
        private Workshift workshift;
        private Workshift instance;
        
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
         * @return the instance
         */
        public Workshift getInstance() {
            return instance;
        }
        //</editor-fold>
    }
    //</editor-fold>
    
    
    
    public static WorkshiftInstanceEditorResult showWorkshiftTransformEditor(Workshift workshift, Workshift transform){
//    public static WorkshiftInstanceEditorResult showWorkshiftTransformEditor(Workshift workshift, Workshift instance, ImageIcon icon){
        WorkshiftInstanceEditorPanel panel = new WorkshiftInstanceEditorPanel(workshift, transform);
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
        
        return new WorkshiftInstanceEditorResult(result, panel.getWorkshift(), panel.getInstance());//panel.getWorkshift(), panel.getInstance());
    }
//</editor-fold>
    
    /**
     * Creates new form InstanceEditorPanel
     * @param workshift
     * @param transform
     */
    public WorkshiftInstanceEditorPanel(Workshift workshift, Workshift transform) {
        initComponents();

        setWorkshift(workshift);
        setInstance(transform);
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
        jlblWorkshiftInstanceID = new javax.swing.JLabel();
        jtxtInstanceBaseName = new javax.swing.JTextField();
        jcbxInstance180DegreeRotation = new javax.swing.JCheckBox();
        jspinInstanceID = new javax.swing.JSpinner();
        jlblX = new javax.swing.JLabel();
        jtxtInstanceX = new javax.swing.JTextField();
        jlblY = new javax.swing.JLabel();
        jtxtInstanceY = new javax.swing.JTextField();
        jlblZ = new javax.swing.JLabel();
        jtxtInstanceZ = new javax.swing.JTextField();
        jlblA = new javax.swing.JLabel();
        jtxtInstanceA = new javax.swing.JTextField();
        jlblC = new javax.swing.JLabel();
        jtxtInstanceC = new javax.swing.JTextField();

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

        jlblWorkshiftInstanceID.setText("Instance ID");

        jtxtInstanceBaseName.setPreferredSize(new java.awt.Dimension(100, 28));

        jcbxInstance180DegreeRotation.setText("180 Rotation?");

        jspinInstanceID.setModel(new javax.swing.SpinnerNumberModel(1, 0, 300, 1));

        jlblX.setText("X");

        jtxtInstanceX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtInstanceX.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblY.setText("Y");

        jtxtInstanceY.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtInstanceY.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblZ.setText("Z");

        jtxtInstanceZ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtInstanceZ.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblA.setText("A");

        jtxtInstanceA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtInstanceA.setPreferredSize(new java.awt.Dimension(100, 28));

        jlblC.setText("C");

        jtxtInstanceC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtInstanceC.setPreferredSize(new java.awt.Dimension(100, 28));

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
                        .addComponent(jtxtInstanceX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblY)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtInstanceY, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                        .addComponent(jlblWorkshiftInstanceID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtInstanceBaseName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblZ)
                .addGap(7, 7, 7)
                .addGroup(jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jspinInstanceID)
                    .addComponent(jtxtInstanceZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                        .addComponent(jlblA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtInstanceA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtInstanceC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jcbxInstance180DegreeRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpnlWorkshiftTransformLayout.setVerticalGroup(
            jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlWorkshiftTransformLayout.createSequentialGroup()
                .addGroup(jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblWorkshiftInstanceID)
                    .addComponent(jtxtInstanceBaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxInstance180DegreeRotation)
                    .addComponent(jspinInstanceID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlWorkshiftTransformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblX)
                    .addComponent(jtxtInstanceX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblY)
                    .addComponent(jtxtInstanceY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblZ)
                    .addComponent(jtxtInstanceZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblA)
                    .addComponent(jtxtInstanceA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblC)
                    .addComponent(jtxtInstanceC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JCheckBox jcbxInstance180DegreeRotation;
    private javax.swing.JComboBox<String> jcbxWorkshift;
    private javax.swing.JLabel jlblA;
    private javax.swing.JLabel jlblA1;
    private javax.swing.JLabel jlblC;
    private javax.swing.JLabel jlblC1;
    private javax.swing.JLabel jlblWorkshiftInstanceID;
    private javax.swing.JLabel jlblX;
    private javax.swing.JLabel jlblX1;
    private javax.swing.JLabel jlblY;
    private javax.swing.JLabel jlblY1;
    private javax.swing.JLabel jlblZ;
    private javax.swing.JLabel jlblZ1;
    private javax.swing.JPanel jpnlOriginalOffset;
    private javax.swing.JPanel jpnlWorkshiftTransform;
    private javax.swing.JSpinner jspinInstanceID;
    private javax.swing.JTextField jtxtInstanceA;
    private javax.swing.JTextField jtxtInstanceBaseName;
    private javax.swing.JTextField jtxtInstanceC;
    private javax.swing.JTextField jtxtInstanceX;
    private javax.swing.JTextField jtxtInstanceY;
    private javax.swing.JTextField jtxtInstanceZ;
    private javax.swing.JTextField jtxtWorkshiftA;
    private javax.swing.JTextField jtxtWorkshiftC;
    private javax.swing.JTextField jtxtWorkshiftX;
    private javax.swing.JTextField jtxtWorkshiftY;
    private javax.swing.JTextField jtxtWorkshiftZ;
    private javax.swing.JLabel lblParentWorkshiftID;
    // End of variables declaration//GEN-END:variables

}
