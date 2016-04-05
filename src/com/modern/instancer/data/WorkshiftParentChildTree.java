/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.data;

import java.util.HashMap;

/**
 *
 * @author kevinlawrence
 */
public class WorkshiftParentChildTree {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        setName("Workshift Parent-Child Structure");
        setWorkshiftParentChildTree(new HashMap<>());
    }

    public WorkshiftParentChildTree() {}
    
    public WorkshiftParentChildTree(String name, HashMap<String, WorkshiftList> workshiftParentChildTree) {
        this.name = name;
        this.workshiftParentChildTree = workshiftParentChildTree;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private String name;
    private HashMap<String, WorkshiftList> workshiftParentChildTree;
    
    public WorkshiftList addWorkshiftParentID(String workshiftParentID) {
        return getWorkshiftParentChildTree().put(workshiftParentID, new WorkshiftList());
    }
    
    public WorkshiftList addWorkshiftParentAndChildren(String workshiftIDParent, WorkshiftList workshiftChildren) {
        return getWorkshiftParentChildTree().put(workshiftIDParent, workshiftChildren);
    }
    
    public void removeWorkshiftIDParent(String workshiftID) {
        getWorkshiftParentChildTree().remove(workshiftID);
    }
    
    public boolean addWorkshiftParentAndChild(String workshiftIDParent, Workshift child) {
        if (getWorkshiftParentChildTree().get(workshiftIDParent) == null){
            getWorkshiftParentChildTree().put(workshiftIDParent, new WorkshiftList());
        }
        
        return getWorkshiftParentChildTree().get(workshiftIDParent).getWorkshiftList().add(child);
    }
    
    public boolean removeChild(String workshiftIDParent, Workshift child) {
        return getWorkshiftParentChildTree().get(workshiftIDParent).getWorkshiftList().remove(child);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the workshiftParentChildTree
     */
    public HashMap<String, WorkshiftList> getWorkshiftParentChildTree() {
        return workshiftParentChildTree;
    }

    /**
     * @param workshiftParentChildTree the workshiftParentChildTree to set
     */
    public void setWorkshiftParentChildTree(HashMap<String, WorkshiftList> workshiftParentChildTree) {
        this.workshiftParentChildTree = workshiftParentChildTree;
    }
//</editor-fold>
    
}
