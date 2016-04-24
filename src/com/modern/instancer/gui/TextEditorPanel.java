/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.gui;

import com.modern.instancer.common.Library;
import com.modern.instancer.common.InMemoryFile;
import com.modern.instancer.parser.GCodeProgram;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 *
 * @author kevinlawrence
 */
public class TextEditorPanel extends javax.swing.JPanel {

//<editor-fold defaultstate="collapsed" desc="Pop-Up Menu Action Items">
    // ******************************************************
    // Pop-up Menu
    // ******************************************************
    private final String OPEN_FILE_PUM_ACTION = "OPEN_FILE";
    private final String CLOSE_FILE_PUM_ACTION = "CLOSE_FILE";
    private final String INSERT_FILE_PUM_ACTION = "INSERT";

    private final String CREATE_INFO_PUM_ACTION = "CREATE_INFO";

    private final String SAVE_PUM_ACTION = "SAVE";
    private final String SAVE_AS_PUM_ACTION = "SAVE_AS";

    private final String COPY_PUM_ACTION = "COPY";
    private final String CUT_PUM_ACTION = "CUT";
    private final String PASTE_PUM_ACTION = "PASTE";
//    private final String CLEAR_PUM_ACTION = "CLEAR";
    private final String PRINT_PUM_ACTION = "PRINT";

    private final String OPEN_FILE_PUM_LABEL = "Open file...";
    private final String CLOSE_FILE_PUM_LABEL = "Close file";
    private final String INSERT_FILE_PUM_LABEL = "Insert file at cursor...";

    private final String CREATE_INFO_PUM_LABEL = "Create Info File";

    private final String SAVE_PUM_LABEL = "Save";
    private final String SAVE_AS_PUM_LABEL = "Save as...";

    private final String COPY_PUM_LABEL = "Copy";
    private final String CUT_PUM_LABEL = "Cut";
    private final String PASTE_PUM_LABEL = "Paste";
//    private final String CLEAR_PUM_LABEL = "Clear all text";
    private final String PRINT_PUM_LABEL = "Print...";

    private EditorMenuItemListener menuItemListener;
//	private JPopupMenu pumTree;

    private void initializePopupMenu() {
        menuItemListener = new EditorMenuItemListener();

        JMenuItem mi;

        mi = new JMenuItem(OPEN_FILE_PUM_LABEL);
        mi.addActionListener(menuItemListener);
        mi.setActionCommand(OPEN_FILE_PUM_ACTION);
        jpumTextEditor.add(mi);

        mi = new JMenuItem(INSERT_FILE_PUM_LABEL);
        mi.addActionListener(menuItemListener);
        mi.setActionCommand(INSERT_FILE_PUM_ACTION);
        jpumTextEditor.add(mi);

        mi = new JMenuItem(CLOSE_FILE_PUM_LABEL);
        mi.addActionListener(menuItemListener);
        mi.setActionCommand(CLOSE_FILE_PUM_ACTION);
        jpumTextEditor.add(mi);

        jpumTextEditor.add(new JSeparator());

        mi = new JMenuItem(CREATE_INFO_PUM_LABEL);
        mi.addActionListener(menuItemListener);
        mi.setActionCommand(CREATE_INFO_PUM_ACTION);
        jpumTextEditor.add(mi);

        jpumTextEditor.add(new JSeparator());

        mi = new JMenuItem(SAVE_PUM_LABEL);
        mi.addActionListener(menuItemListener);
        mi.setActionCommand(SAVE_PUM_ACTION);
        jpumTextEditor.add(mi);

        mi = new JMenuItem(SAVE_AS_PUM_LABEL);
        mi.addActionListener(menuItemListener);
        mi.setActionCommand(SAVE_AS_PUM_ACTION);
        jpumTextEditor.add(mi);

        jpumTextEditor.add(new JSeparator());

        mi = new JMenuItem(COPY_PUM_LABEL);
        mi.addActionListener(menuItemListener);
        mi.setActionCommand(COPY_PUM_ACTION);
        jpumTextEditor.add(mi);

//        mi = new JMenuItem(CUT_PUM_LABEL);
//        mi.addActionListener(menuItemListener);
//        mi.setActionCommand(CUT_PUM_ACTION);
//        jpumTextEditor.add(mi);
        mi = new JMenuItem(PASTE_PUM_LABEL);
        mi.addActionListener(menuItemListener);
        mi.setActionCommand(PASTE_PUM_ACTION);
        jpumTextEditor.add(mi);

        jpumTextEditor.add(new JSeparator());

        mi = new JMenuItem(PRINT_PUM_LABEL);
        mi.addActionListener(menuItemListener);
        mi.setActionCommand(PRINT_PUM_ACTION);
        jpumTextEditor.add(mi);

        jtxtTextEditor.setComponentPopupMenu(jpumTextEditor);
    }

    class EditorMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {

                case OPEN_FILE_PUM_ACTION:
                    openFile();
                    break;

                case CLOSE_FILE_PUM_ACTION:
                    closeFile();
                    break;

                case INSERT_FILE_PUM_ACTION:
                    appendFile();
                    break;

                case CREATE_INFO_PUM_ACTION:
                    createInfoFile();
                    break;

                case SAVE_PUM_ACTION:
                    save();
                    break;

                case SAVE_AS_PUM_ACTION:
                    saveAs();
                    break;

                case COPY_PUM_ACTION:
                    copy();
                    break;

                case CUT_PUM_ACTION:
                    cut();
                    break;

                case PASTE_PUM_ACTION:
                    paste();
                    break;

                case PRINT_PUM_ACTION:
                    print();
                    break;

                default:
                    notYetImplemented(e.getActionCommand());
                    break;
            }
        }

    }

    private static final String NOT_YET_IMPLEMENTED_MESSAGE = "'%s' has not yet been implemented.";
    private static final String NOT_YET_IMPLEMENTED_TITLE = "Function Not Yet Implemented";

    public void notYetImplemented(String action) {
        JOptionPane.showMessageDialog(this, String.format(NOT_YET_IMPLEMENTED_MESSAGE, action), NOT_YET_IMPLEMENTED_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public void openFile() {
        closeFile();
        appendFile();
    }

    public void closeFile() {
        this.jtxtTextEditor.setText("");
        jlblFilePathName.setText(" ");
    }

    public void appendFile() {
        file = new InMemoryFile();
        file.openFile();

        if (file != null) {
            setFile(file);
        }

//<editor-fold defaultstate="collapsed" desc="olde code">
//        File file = Library.openFile("Open D-Code Source File");
//
//        if (file != null) {
////            System.out.println("Selected file: " + file.getAbsolutePath());
//            jlblFilePathName.setText(file.getAbsolutePath());
//
//            BufferedReader br;
//            try {
//                br = new BufferedReader(new FileReader(file.getAbsolutePath()));
//                try {
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        jtxtTextEditor.append(line + "\n");
//                    }
//
//                } catch (IOException e) {
////                e.printStackTrace();
//                }
//            } catch (FileNotFoundException e) {
//                System.out.println(e);
////            e.printStackTrace();
//            }
//            jtxtTextEditor.setCaretPosition(0);
//        }
//</editor-fold>
    }

    public void setFile(InMemoryFile file) {
        jlblFilePathName.setText(file.getAbsolutePath());

        file.getLines().stream().forEachOrdered((line) -> {
            jtxtTextEditor.append(line);
        });

        jtxtTextEditor.setCaretPosition(0);
    }

    InMemoryFile file;
    private TextEditorEventListenerIntf eventListener;

    /**
     * @param eventListener the eventListener to set
     */
    public void setEventListener(TextEditorEventListenerIntf eventListener) {
        this.eventListener = eventListener;
    }

    private void createInfoFile() {
        if (file != null) {
            if (eventListener != null) {
//                InMemoryFile infoFile = new InMemoryFile(GCodeProgram.getParsedGCode(file.getLines()));
                InMemoryFile infoFile = GCodeProgram.getParsedGCode(file.getLines());
                eventListener.handleProcessedFile(infoFile);
            }
//            GCodeProgram.getParsedGCode(file.getLines()).stream().forEachOrdered((line) ->{
//                
//            });
        }
    }

    public void save() {
        notYetImplemented(SAVE_PUM_LABEL);
    }

    public void saveAs() {
        notYetImplemented(SAVE_AS_PUM_LABEL);
    }

    public void cut() {
        notYetImplemented(CUT_PUM_LABEL);
    }

    public void copy() {
        String selectedText = jtxtTextEditor.getSelectedText();

        if (selectedText != null) {
            StringSelection stringSelection = new StringSelection(selectedText);

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }

    public void paste() {
//        notYetImplemented(PASTE_PUM_LABEL);

        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
//            Object text = systemClipboard.getData(DataFlavor.stringFlavor);
            String text = (String) systemClipboard.getData(DataFlavor.stringFlavor);
            jtxtTextEditor.insert(text, jtxtTextEditor.getCaretPosition());

        } catch (UnsupportedFlavorException | IOException ex) {
            Logger.getLogger(TextEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void print() {
        try {
            jtxtTextEditor.print();
        } catch (PrinterException ex) {
            Logger.getLogger(TextEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Print Error");
        }
    }

//    public void loadText(ArrayList<String> text) {
//    }
//
//    public void appendText(ArrayList<String> text) {
//    }
//</editor-fold>
    /**
     * Creates new form TextEditor
     */
    public TextEditorPanel() {
        initComponents();
        initializePopupMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpumTextEditor = new javax.swing.JPopupMenu();
        jpnlFilePathName = new javax.swing.JPanel();
        jlblFilePathName = new javax.swing.JLabel();
        jpnlTextEditor = new javax.swing.JPanel();
        jscrollpnlTextEditor = new javax.swing.JScrollPane();
        jtxtTextEditor = new javax.swing.JTextArea();

        jlblFilePathName.setText(" ");

        javax.swing.GroupLayout jpnlFilePathNameLayout = new javax.swing.GroupLayout(jpnlFilePathName);
        jpnlFilePathName.setLayout(jpnlFilePathNameLayout);
        jpnlFilePathNameLayout.setHorizontalGroup(
            jpnlFilePathNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlFilePathNameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblFilePathName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnlFilePathNameLayout.setVerticalGroup(
            jpnlFilePathNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlFilePathNameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblFilePathName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtxtTextEditor.setColumns(20);
        jtxtTextEditor.setRows(5);
        jscrollpnlTextEditor.setViewportView(jtxtTextEditor);

        javax.swing.GroupLayout jpnlTextEditorLayout = new javax.swing.GroupLayout(jpnlTextEditor);
        jpnlTextEditor.setLayout(jpnlTextEditorLayout);
        jpnlTextEditorLayout.setHorizontalGroup(
            jpnlTextEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jscrollpnlTextEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
        );
        jpnlTextEditorLayout.setVerticalGroup(
            jpnlTextEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jscrollpnlTextEditor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlFilePathName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlTextEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jpnlFilePathName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlTextEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlblFilePathName;
    private javax.swing.JPanel jpnlFilePathName;
    private javax.swing.JPanel jpnlTextEditor;
    private javax.swing.JPopupMenu jpumTextEditor;
    private javax.swing.JScrollPane jscrollpnlTextEditor;
    private javax.swing.JTextArea jtxtTextEditor;
    // End of variables declaration//GEN-END:variables
}
