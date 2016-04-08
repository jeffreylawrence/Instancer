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
public class WorkshiftValidatorG54P1 implements WorkshiftValidatorIntf {
    
//<editor-fold defaultstate="collapsed" desc="WorkshiftValidatorIntf Methods">
    @Override
    public OperationStatusProviderIntf validate(Workshift workshift) {
        //make sure name is correct form
        //  - starts with "G54.P1"
        //  - has instance between min and max allowable value
        //TODO: make sure does not conflict with existing offset list (unless it is the same Workshift) >>> how to do this (store original value?)
        
        if (!workshift.getWorkshiftID().startsWith(G54P1_NAME_BASE)){
            return new OperationStatusProvider(false, String.format("Workshift '%s' does not start with standard prefix '%s'.", workshift.getWorkshiftID(), G54P1_NAME_BASE));
        }
        
        if ((workshift.getInstance() < MIN_INSTANCE_ID) || (workshift.getInstance() > MAX_INSTANCE_ID)){
            return new OperationStatusProvider(false, String.format("Workshift instance number '%d' is not between minimum (%d) and maximum (%d) allowed values.", workshift.getInstance(), MIN_INSTANCE_ID, MAX_INSTANCE_ID));
        }
        
        return new OperationStatusProvider(true, String.format("Workshift '%s' passed validation rules.", workshift.getWorkshiftID()));
    }
    
    private static final int MIN_INSTANCE_ID = 1;
    private static final int MAX_INSTANCE_ID = 300;

    @Override
    public int getMinInstanceID() {
        return MIN_INSTANCE_ID;
    }

    @Override
    public int getMaxInstanceID() {
        return MAX_INSTANCE_ID;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public WorkshiftValidatorG54P1(ArrayList<Workshift> workshifts){
        this.workshifts = workshifts;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    public static final String G54P1_NAME_BASE = "G54.1P";
//    public static final String G54P1_TEST_SET = "G10L20P10X-14.9572Y-10.9581Z-14.7454A0.0C0.0(A-90. C180.)";
    
    private ArrayList<Workshift> workshifts;
    private boolean allowDuplicateWorkshiftID = false;
//</editor-fold>
}
