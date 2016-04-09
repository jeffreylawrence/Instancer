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
public class WorkshiftTreeData {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        setName("Workshift Tree Structure");
        setWorkshiftTree(new HashMap<>());
    }

    public WorkshiftTreeData() {}
    
    public WorkshiftTreeData(String name, HashMap<String, WorkshiftList> workshiftTree) {
        this.name = name;
        this.workshiftTree = workshiftTree;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private String name;
    private HashMap<String, WorkshiftList> workshiftTree;
    
    public WorkshiftList addWorkshiftParentID(String workshiftParentID) {
        return getWorkshiftTree().put(workshiftParentID, new WorkshiftList());
    }
    
    public WorkshiftList addWorkshiftParentAndChildren(String workshiftIDParent, WorkshiftList workshiftChildren) {
        return getWorkshiftTree().put(workshiftIDParent, workshiftChildren);
    }
    
    public void removeWorkshiftIDParent(String workshiftID) {
        getWorkshiftTree().remove(workshiftID);
    }
    
    public boolean addWorkshiftParentAndChild(String workshiftIDParent, Workshift child) {
        if (getWorkshiftTree().get(workshiftIDParent) == null){
            getWorkshiftTree().put(workshiftIDParent, new WorkshiftList());
        }
        
        return getWorkshiftTree().get(workshiftIDParent).getWorkshiftList().add(child);
    }
    
    public boolean removeChild(String workshiftIDParent, Workshift child) {
        return getWorkshiftTree().get(workshiftIDParent).getWorkshiftList().remove(child);
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
     * @return the workshiftTree
     */
    public HashMap<String, WorkshiftList> getWorkshiftTree() {
        return workshiftTree;
    }

    /**
     * @param workshiftTree the workshiftTree to set
     */
    public void setWorkshiftTree(HashMap<String, WorkshiftList> workshiftTree) {
        this.workshiftTree = workshiftTree;
    }
//</editor-fold>
    
}
