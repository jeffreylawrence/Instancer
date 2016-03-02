/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.data;

import java.util.ArrayList;

/**
 *
 * @author kevinlawrence
 */
public class OffsetValidatorG54P1 implements OffsetValidatorIntf {
    
//<editor-fold defaultstate="collapsed" desc="OffsetValidatorIntf Methods">
    @Override
    public OperationStatusProviderIntf validate(Offset offset) {
        //make sure name is correct form
        //  - starts with "G54.P1"
        //  - has instance between min and max allowable value
        //TODO: make sure does not conflict with existing offset list (unless it is the same offset) >>> how to do this (store original value?)
        
        if (!offset.getOffsetID().startsWith(G54P1_NAME_BASE)){
            return new OperationStatusProvider(false, String.format("Offset '%s' does not start with standard prefix '%s'.", offset.getOffsetID(), G54P1_NAME_BASE));
        }
        
        if ((offset.getInstance() < minimumInstanceID) || (offset.getInstance() > maximumInstanceID)){
            return new OperationStatusProvider(false, String.format("Offset instance number '%d' is not between minimum (%d) and maximum (%d) allowed values.", offset.getInstance(), minimumInstanceID, maximumInstanceID));
        }
        
        return new OperationStatusProvider(true, String.format("Offset '%s' passed validation rules.", offset.getOffsetID()));
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public OffsetValidatorG54P1(ArrayList<Offset> offsets){
        this.offsets = offsets;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    public static final String G54P1_NAME_BASE = "G54.1P";
    
    private ArrayList<Offset> offsets;
    
    private final int minimumInstanceID = 1;
    private final int maximumInstanceID = 300;
    private boolean allowDuplicateOffsetID = false;
//</editor-fold>
}
