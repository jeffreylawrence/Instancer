/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancer;

import com.modern.instancer.data.Offset;
import com.modern.instancer.gui.InstancerMain;
import com.modern.instancer.gui.OffsetEditorDialog;
import com.modern.instancer.gui.OffsetInstanceEditorFrame;
import com.modern.instancer.gui.OffsetUpdateHandlerIntf;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author kevinlawrence
 */
public class Instancer { //implements { OffsetUpdateHandlerIntf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InstancerMain instancerMain = new InstancerMain();
        instancerMain.setVisible(true);
        
        Offset offset = new Offset(Offset.G54P1_NAME_BASE, 12, null, false);
//        OffsetInstanceEditorFrame instanceEditor = new OffsetInstanceEditorFrame(offset);
//        instanceEditor.setAlwaysOnTop(true);
//        instanceEditor.setVisible(true);
        
        
//        JOptionPane.showInputDialog(instancerMain, offset, "", 0);
//        JOptionPane.show

//        OffsetEditorDialog editor = new OffsetEditorDialog(null, true, offset, this);
        OffsetEditorDialog editor = new OffsetEditorDialog(null, true, offset, (Offset anOffset) -> {System.out.println("Offset = " + offset.getOffsetID());});
        editor.setVisible(true);
        
    }

//<editor-fold defaultstate="collapsed" desc="OffsetUpdateHandlerIntf">
//    @Override
//    public void onUpdate(Offset offset) {
//        System.out.println("Offset = " + offset.getOffsetID());
//    }
//</editor-fold>
    
}
