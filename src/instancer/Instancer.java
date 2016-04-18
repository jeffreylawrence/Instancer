/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancer;

import com.modern.instancer.data.Workshift;
import com.modern.instancer.gui.InstancerMain;
import com.modern.instancer.gui.WorkshiftTransformEditorPanel;
import com.modern.instancer.gui.WorkshiftTransformTreeDialog;
import java.awt.Image;
import javax.swing.ImageIcon;
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

        testWorkshiftTransformEditor();
        testWorkshiftTransformTreeDialog();
    }

//<editor-fold defaultstate="collapsed" desc="WorkshiftUpdateHandlerIntf">
//    @Override
//    public void onUpdate(Workshift offset) {
//        System.out.println("Workshift = " + offset.getWorkshiftID());
//    }
//</editor-fold>
    
    private static void testWorkshiftTransformEditor() {
        String G54P1_WORKSHIFT = "G10L20P10X-14.9572Y-10.9581Z-14.7454A0.0C0.0(A-90. C180.)";
        String G54P1_TRANSFORM = "G10L20P10X-14.9572Y-10.9581Z-14.7454A-90.0C-180.0(A-90. C180.)";

//        Image i = new Image();
        
//        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("instancer_icon_250x250.png")));
//        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("instancer_icon_250x250.png")));

        WorkshiftTransformEditorPanel.WorkshiftTransformEditorResult data = WorkshiftTransformEditorPanel.showWorkshiftTransformEditor(new Workshift("WS001", null, false, G54P1_WORKSHIFT), new Workshift("WS001", null, false, G54P1_TRANSFORM));

        System.out.println(data.getDialogResult() == JOptionPane.OK_OPTION ? "OK" : "Cancel");
        System.out.println(" Workshift:  " + data.getWorkshift().toString());
        System.out.println(" Transform:  " + data.getTransform().toString());
    }
    
    private static void testWorkshiftTransformTreeDialog(){
        WorkshiftTransformTreeDialog dlg = new WorkshiftTransformTreeDialog(null, true);
        dlg.setVisible(true);

    }

}
