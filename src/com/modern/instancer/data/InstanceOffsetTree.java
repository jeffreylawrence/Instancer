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
        instanceTree = new HashMap<>();
    }

    public InstanceOffsetTree() {}
    
    public InstanceOffsetTree(HashMap<String, OffsetList> instanceTree) {
        this.instanceTree = instanceTree;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private HashMap<String, OffsetList> instanceTree;
    
    public OffsetList addInstance(String instance) {
        return instanceTree.put(instance, new OffsetList());
    }
    
    public OffsetList addInstance(String instance, OffsetList offsets) {
        return instanceTree.put(instance, offsets);
    }
    
    public void removeInstance(String instance) {
        instanceTree.remove(instance);
    }
    
    public boolean addOffset(String instance, Offset offset) {
        return instanceTree.get(instance).getOffsetList().add(offset);
    }
    
    public boolean removeOffset(String instance, Offset offset) {
        return instanceTree.get(instance).getOffsetList().remove(offset);
    }
//</editor-fold>
    
}
