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
public class WorkshiftList implements WorkshiftListProviderIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        workshiftList = new ArrayList<>();
    }

    public WorkshiftList() {}

    public WorkshiftList(ArrayList<Workshift> worshiftList) {
        this.workshiftList = worshiftList;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Workshift> workshiftList;

    /**
     * @return the workshiftList
     */
    public ArrayList<Workshift> getWorkshiftList() {
        return workshiftList;
    }

    /**
     * @param workshiftList the workshiftList to set
     */
    public void setWorkshiftList(ArrayList<Workshift> workshiftList) {
        this.workshiftList = workshiftList;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="WorkshiftListProviderIntf">
    @Override
    public ArrayList<String> getWorkshiftIDs() {
        ArrayList<String> workshiftIDs = new ArrayList<>();

        workshiftList.stream().forEachOrdered((workshift) -> {
            workshiftIDs.add(workshift.getWorkshiftID());
        });

        return workshiftIDs;
    }

    @Override
    public ArrayList<String> getWorkshiftIDsSorted() {
        ArrayList<String> workshiftIDs = getWorkshiftIDs();
        Collections.sort(workshiftIDs);

        return workshiftIDs;
    }
//</editor-fold>

}
