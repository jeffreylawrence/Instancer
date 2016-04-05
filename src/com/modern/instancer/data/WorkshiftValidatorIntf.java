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
public interface WorkshiftValidatorIntf {
    public OperationStatusProviderIntf validate(Workshift offset);
    
    public int getMinInstanceID();
    public int getMaxInstanceID();
}
