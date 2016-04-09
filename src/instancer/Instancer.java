/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancer;

import com.modern.instancer.data.Workshift;
import com.modern.instancer.gui.InstancerMain;
import com.modern.instancer.gui.WorkshiftTransformEditorPanel;
import javax.swing.JOptionPane;

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
        
//        Workshift workshift = new Workshift(Workshift.G54P1_NAME_BASE, 12, null, false);
//        WorkshiftEditorFrame instanceEditor = new WorkshiftEditorFrame(offset);
//        instanceEditor.setAlwaysOnTop(true);
//        instanceEditor.setVisible(true);
        

    String G54P1_WORKSHIFT = "G10L20P10X-14.9572Y-10.9581Z-14.7454A0.0C0.0(A-90. C180.)";
    String G54P1_TRANSFORM = "G10L20P10X-14.9572Y-10.9581Z-14.7454A-90.0C-180.0(A-90. C180.)";

        WorkshiftTransformEditorPanel.WorkshiftTransformEditorResult data = WorkshiftTransformEditorPanel.showWorkshiftTransformEditor(new Workshift("WS001", null, false, G54P1_WORKSHIFT), new Workshift("WS001", null, false, G54P1_TRANSFORM));

        System.out.println(data.getDialogResult() == JOptionPane.OK_OPTION ? "OK" : "Cancel");
        System.out.println(" Workshift:  " + data.getWorkshift().toString());
        System.out.println(" Transform:  " + data.getTransform().toString());



//        WorkshiftTransformEditorPanel panel = new WorkshiftTransformEditorPanel(new Workshift("WS001", null, false, G54P1_WORKSHIFT), new Workshift("WS001", null, false, G54P1_TRANSFORM));
//        int result = JOptionPane.showConfirmDialog(null, panel,
//            "Edit Workshift Transform", JOptionPane.OK_CANCEL_OPTION);
//
//      if (result == JOptionPane.OK_OPTION) {
//         System.out.println("success");
//      }

//        WorkshiftTransformEditorDialog dlg = new WorkshiftTransformEditorDialog(null, true, new Workshift("WS001", null, false, G54P1_WORKSHIFT), new Workshift("WS001", null, false, G54P1_TRANSFORM));
//        dlg.setVisible(true);
        

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
