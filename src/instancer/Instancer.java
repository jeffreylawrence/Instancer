/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancer;

import com.modern.instancer.data.Workshift;
import com.modern.instancer.gui.InstancerMain;
//import com.modern.instancer.gui.WorkshiftTransformEditorPanel;
import com.modern.instancer.gui.WorkshiftInstanceEditorPanel;
import com.modern.instancer.gui.WorkshiftTreeDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author kevinlawrence
 */
public class Instancer { 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int versionMajor = 0;
        int versionMinor = 3;
        
        InstancerMain instancerMain = new InstancerMain(versionMajor, versionMinor);
        instancerMain.setVisible(true);

//        Workshift workshift = new Workshift(Workshift.G54P1_NAME_BASE, 12, null, false);

//        String G54P1_WORKSHIFT = "G10L20P10X-14.9572Y-10.9581Z-14.7454A0.0C0.0(A-90. C180.)";
//        String G54P1_TRANSFORM = "G10L20P10X-14.9572Y-10.9581Z-14.7454A0.0C0.0(A-90. C180.)";

//        testWorkshiftTransformEditor();
//        testWorkshiftTransformTreeDialog();
    }

    private static void testWorkshiftTransformEditor() {
        String G54P1_WORKSHIFT = "G10L20P10X-14.9572Y-10.9581Z-14.7454A0.0C0.0(A-90. C180.)";
        String G54P1_TRANSFORM = "G10L20P10X-14.9572Y-10.9581Z-14.7454A-90.0C-180.0(A-90. C180.)";

//        Image i = new Image();
//        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("instancer_icon_250x250.png")));
//        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("instancer_icon_250x250.png")));
        WorkshiftInstanceEditorPanel.WorkshiftInstanceEditorResult data = WorkshiftInstanceEditorPanel.showWorkshiftInstanceEditor(new Workshift("WS001", null, false, G54P1_WORKSHIFT), new Workshift("WS001", null, false, G54P1_TRANSFORM));

        System.out.println(data.getDialogResult() == JOptionPane.OK_OPTION ? "OK" : "Cancel");
        System.out.println(" Workshift:  " + data.getWorkshift().toString());
        System.out.println(" Transform:  " + data.getInstance().toString());
    }

    private static void testWorkshiftTransformTreeDialog() {
        WorkshiftTreeDialog dlg = new WorkshiftTreeDialog(null, true);
        dlg.setVisible(true);
    }

}
