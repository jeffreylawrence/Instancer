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
public interface OffsetListProviderIntf {
    public ArrayList<String> getOffsetIDs();
    public ArrayList<String> getOffsetIDsSorted();
}
