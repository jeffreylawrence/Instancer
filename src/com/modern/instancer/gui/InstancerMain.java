/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.gui;

import com.modern.instancer.common.Library;
import com.modern.instancer.common.InMemoryFile;
import com.modern.instancer.parser.GCodeInstanceParser;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author kevinlawrence
 */
public final class InstancerMain extends javax.swing.JFrame implements ActionListener {

    //<editor-fold defaultstate="collapsed" desc="Key Listener">
    private class SketchPadKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
//            System.out.println("Key Typed");
        }

        @Override
        public void keyPressed(KeyEvent e) {

            System.out.println("Key Pressed");
            switch (e.getKeyCode()) {
                case KeyEvent.VK_1:
                    break;
            }

            if (e.isControlDown()) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_G:
                        break;

                    case KeyEvent.VK_O:
//                        SketchIO.openFile();
                        break;

                    case KeyEvent.VK_S:
//                        SketchIO.saveToFile(sketchPad.sketchObjects);
                        break;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("Key Released");
        }
    }
//</editor-fold>

    private class TextEditorListener implements TextEditorEventListenerIntf {

        public TextEditorListener() {
        }

        ;
        
        @Override
        public void handleProcessedFile(InMemoryFile file) {
            handleInformationFile(file);
        }
    }

    TextEditorListener gCodeEditorListener;

//<editor-fold defaultstate="collapsed" desc="ActionListener">
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action Peformed : " + e.toString());

        switch (e.getActionCommand()) {
            case MENU_ACT_MULTI_INSTANCE_FILE:
                createMultipartFile();
                break;

            case MENU_ACT_OPEN_SOURCE_FILE:
                openSourceFile();
                break;
        }
    }

    /*
        public InMemoryFile createMultiPartFile() {
        InstanceConfigurationEditor.InstanceConfigurationResultIntf config = InstanceConfigurationEditor.showInstanceConfigurationEditor();
        
        if (config.getDialogResult() == JOptionPane.OK_OPTION) {
            return GCodeInstanceParser.createInstanceFile(file, config.getInstanceCount(), config.getRetraction());
        } else {
            return null;
        }

     */
    public void createMultipartFile() {
        InstanceConfigurationEditor.InstanceConfigurationResultIntf config = InstanceConfigurationEditor.showInstanceConfigurationEditor();
        if (config.getDialogResult() == JOptionPane.OK_OPTION) {
            right.setFile(GCodeInstanceParser.createInstanceFile(left.getFile(), config.getInstanceCount(), config.getRetraction()));
            right.setFileName("Multipart File");
        }
    }

    private void openSourceFile() {
        left.openFile();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Menu Items">
    public static final String MENU_LABEL_MULTI_INSTANCE_FILE = "Create Multi-instance File";
    public static final String MENU_LABEL_OPEN_SOURCE_FILE = "Open Source File...";

    public static final String MENU_ACT_MULTI_INSTANCE_FILE = "CREATE MULTI-INSTANCE FILE";
    public static final String MENU_ACT_OPEN_SOURCE_FILE = "OPEN SOURCE FILE";

    private void addMenuItems() {
        // add menu items
        JMenuItem menuItem;

        menuItem = new JMenuItem(MENU_LABEL_OPEN_SOURCE_FILE, KeyEvent.VK_O);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menuItem.setActionCommand(MENU_ACT_OPEN_SOURCE_FILE);
        menuItem.getAccessibleContext().setAccessibleDescription("");
        jmenuFile.add(menuItem);

        menuItem = new JMenuItem(MENU_LABEL_MULTI_INSTANCE_FILE, KeyEvent.VK_M);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menuItem.setActionCommand(MENU_ACT_MULTI_INSTANCE_FILE);
        menuItem.getAccessibleContext().setAccessibleDescription("");
        jmenuFile.add(menuItem);
    }

//</editor-fold>
    
    
    private void internalInit() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("instancer_icon_250x250.png")));

        left = new TextEditorPanel();
        gCodeEditorListener = new TextEditorListener();
        left.setEventListener(gCodeEditorListener);

        right = new TextEditorPanel();

        jsplitpnlTextEditor.setLeftComponent(left);
        jsplitpnlTextEditor.setRightComponent(right);

        jsplitpnlTextEditor.setDividerLocation(0.5);

        addMenuItems();
    }

    private void handleInformationFile(InMemoryFile infoFile) {
        right.setFile(infoFile);
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    TextEditorPanel left, right;

//</editor-fold>
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        jsplitpnlTextEditor.setDividerLocation(0.5);
        repaint();
    }

    /**
     * Creates new form InstancerMain
     */
    public InstancerMain(int versionMajor, int versionMinor) {
        initComponents();
        internalInit();

        setTitle(String.format("Instancer v%d.%d", versionMajor, versionMinor));
        setIconImage(Library.loadImageFromResource("com/modern/instancer/gui/instancer_icon_250x250.jpg"));
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsplitpnlTextEditor = new javax.swing.JSplitPane();
        jmbMain = new javax.swing.JMenuBar();
        jmenuFile = new javax.swing.JMenu();
        jmenuEdit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jsplitpnlTextEditor.setDividerLocation(350);

        jmenuFile.setText("File");
        jmbMain.add(jmenuFile);

        jmenuEdit.setText("Edit");
        jmbMain.add(jmenuEdit);

        setJMenuBar(jmbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsplitpnlTextEditor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsplitpnlTextEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
//            java.util.logging.Logger.getLogger(InstancerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(InstancerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(InstancerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(InstancerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new InstancerMain().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jmbMain;
    private javax.swing.JMenu jmenuEdit;
    private javax.swing.JMenu jmenuFile;
    private javax.swing.JSplitPane jsplitpnlTextEditor;
    // End of variables declaration//GEN-END:variables
}
