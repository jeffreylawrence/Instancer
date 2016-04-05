/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancer;

import com.modern.instancer.data.Workshift;
import com.modern.instancer.gui.InstancerMain;
import com.modern.instancer.gui.WorkshiftEditorDialog;
import com.modern.instancer.gui.WorkshiftEditorFrame;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.modern.instancer.gui.WorkshiftUpdateHandlerIntf;

/**
 *
 * @author kevinlawrence
 */
public class Instancer { //implements { WorkshiftUpdateHandlerIntf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InstancerMain instancerMain = new InstancerMain();
        instancerMain.setVisible(true);
        
        Workshift offset = new Workshift(Workshift.G54P1_NAME_BASE, 12, null, false);
//        WorkshiftEditorFrame instanceEditor = new WorkshiftEditorFrame(offset);
//        instanceEditor.setAlwaysOnTop(true);
//        instanceEditor.setVisible(true);
        
        
//        JOptionPane.showInputDialog(instancerMain, offset, "", 0);
//        JOptionPane.show

//        WorkshiftEditorDialog editor = new WorkshiftEditorDialog(null, true, offset, (Workshift anOffset) -> {System.out.println("Workshift = " + offset.getWorkshiftID());});
//        editor.setVisible(true);
    }

//<editor-fold defaultstate="collapsed" desc="WorkshiftUpdateHandlerIntf">
//    @Override
//    public void onUpdate(Workshift offset) {
//        System.out.println("Workshift = " + offset.getWorkshiftID());
//    }
//</editor-fold>
    
}
