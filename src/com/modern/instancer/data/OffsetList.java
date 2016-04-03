/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kevinlawrence
 */
public class OffsetList implements OffsetListProviderIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        offsetList = new ArrayList<>();
    }

    public OffsetList() {}

    public OffsetList(ArrayList<Offset> offsetList) {
        this.offsetList = offsetList;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Offset> offsetList;

    /**
     * @return the offsetList
     */
    public ArrayList<Offset> getOffsetList() {
        return offsetList;
    }

    /**
     * @param offsetList the offsetList to set
     */
    public void setOffsetList(ArrayList<Offset> offsetList) {
        this.offsetList = offsetList;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="OffsetListProviderIntf">
    @Override
    public ArrayList<String> getOffsetIDs() {
        ArrayList<String> offsetIDs = new ArrayList<>();

        offsetList.stream().forEachOrdered((offset) -> {
            offsetIDs.add(offset.getOffsetID());
        });

        return offsetIDs;
    }

    @Override
    public ArrayList<String> getOffsetIDsSorted() {
        ArrayList<String> offsetIDs = getOffsetIDs();
        Collections.sort(offsetIDs);

        return offsetIDs;
    }
//</editor-fold>

}
