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
public class InstanceOffsetTree {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        setName("Instance-Offset Structure");
        setInstanceTree(new HashMap<>());
    }

    public InstanceOffsetTree() {}
    
    public InstanceOffsetTree(String name, HashMap<String, OffsetList> instanceTree) {
        this.name = name;
        this.instanceTree = instanceTree;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private String name;
    private HashMap<String, OffsetList> instanceTree;
    
    public OffsetList addInstance(String instance) {
        return getInstanceTree().put(instance, new OffsetList());
    }
    
    public OffsetList addInstance(String instance, OffsetList offsets) {
        return getInstanceTree().put(instance, offsets);
    }
    
    public void removeInstance(String instance) {
        getInstanceTree().remove(instance);
    }
    
    public boolean addOffset(String instance, Offset offset) {
        if (getInstanceTree().get(instance) == null){
            getInstanceTree().put(instance, new OffsetList());
        }
        
        return getInstanceTree().get(instance).getOffsetList().add(offset);
    }
    
    public boolean removeOffset(String instance, Offset offset) {
        return getInstanceTree().get(instance).getOffsetList().remove(offset);
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
     * @return the instanceTree
     */
    public HashMap<String, OffsetList> getInstanceTree() {
        return instanceTree;
    }

    /**
     * @param instanceTree the instanceTree to set
     */
    public void setInstanceTree(HashMap<String, OffsetList> instanceTree) {
        this.instanceTree = instanceTree;
    }
//</editor-fold>
    
}
