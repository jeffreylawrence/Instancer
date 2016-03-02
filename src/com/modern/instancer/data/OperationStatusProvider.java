/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.data;

/**
 *
 * @author kevinlawrence
 */
public class OperationStatusProvider implements OperationStatusProviderIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public OperationStatusProvider(boolean status, String statusInformation){
        this.status = status;
        this.statusInformation = statusInformation;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private final boolean status;
    private final String statusInformation;
    
    @Override
    public boolean getStatus() {
        return status;
    }
    
    @Override
    public String getStatusInformation() {
        return statusInformation;
    }
//</editor-fold>
    
}
