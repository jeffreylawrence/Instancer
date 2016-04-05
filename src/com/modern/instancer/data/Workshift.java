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
public class Workshift implements Comparable {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        parentWorkshiftID = "";
        rotate180Degrees = false;
    }

    public Workshift(String offsetID, String parentOffsetID, boolean rotate180Degrees) {
        setWorkshiftID(offsetID);
        this.parentWorkshiftID = parentOffsetID;
        this.rotate180Degrees = rotate180Degrees;
    }

    public Workshift(String offsetNameBase, int offsetInstance, String parentOffsetID, boolean rotate180Degrees) {
        this.nameBase = offsetNameBase;
        this.instance = offsetInstance;
        this.parentWorkshiftID = parentOffsetID;
        this.rotate180Degrees = rotate180Degrees;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Validation">
    private WorkshiftValidatorIntf validator;
    private OperationStatusHandlerIntf validationStatusHandler;
    
    /**
     * @param validator the validator to set
     */
    public void setValidator(WorkshiftValidatorIntf validator) {
        this.validator = validator;
    }


    private boolean validate() throws Exception {
        if (validator != null) {
            OperationStatusProviderIntf validationStatus = validator.validate(this);
            
            if (validationStatusHandler != null) {
                validationStatusHandler.handleStatus(validationStatus);
            } else if (!validationStatus.getStatus()) {
                throw new Exception("Improper data definition for Offset " + getWorkshiftID() + " => " + validationStatus.getStatusInformation());
            }
        }
        return true;
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    public static final String G54P1_NAME_BASE = "G54.1P";

    private final int MIN_INSTANCE_NUMBER = 1;
    private final int MAX_INSTANCE_NUMBER = 300;

    private String nameBase;
    private int instance;
    private String parentWorkshiftID;
    private boolean rotate180Degrees;

    /**
     * @return the instance
     */
    public int getInstance() {
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public void setInstance(int instance) {
        this.instance = instance;
    }

    /**
     * @return a textual representation of the Workshift
     */
    @Override
    public String toString() {
        return String.format("%s%s%s", getWorkshiftID(), isMain() ? " <BASE>" : "", rotate180Degrees ? " <ROTATE 180>" : "");
    }

    /**
     * @return the offsetID
     */
    public String getWorkshiftID() {
        return String.format("%s%d", getNameBase(), getInstance());
    }

    /**
     * @param workshiftID the offsetID to set
     */
    public final void setWorkshiftID(String workshiftID) {
        //parse the input offsetID and store the bits...
        if (workshiftID.startsWith(G54P1_NAME_BASE)) {
            setNameBase(G54P1_NAME_BASE);
            setInstance((int) Integer.valueOf(workshiftID.substring(G54P1_NAME_BASE.length())));
        }
    }

    /**
     * @return the baseOriginal
     */
    public boolean isMain() {
        return (parentWorkshiftID == null) || (parentWorkshiftID == "");
    }

    /**
     * @return the parentWorkshiftID
     */
    public String getParentWorkshiftID() {
        return parentWorkshiftID;
    }

    /**
     * @param parentWorkshiftID the parentWorkshiftID to set
     */
    public void setParentWorkshiftID(String parentWorkshiftID) {
        this.parentWorkshiftID = parentWorkshiftID;
    }

    /**
     * @return the rotate180Degrees
     */
    public boolean isRotate180Degrees() {
        return rotate180Degrees;
    }

    /**
     * @param rotate180Degrees the rotate180Degrees to set
     */
    public void setRotate180Degrees(boolean rotate180Degrees) {
        this.rotate180Degrees = rotate180Degrees;
    }
    
    /**
     * @return the nameBase
     */
    public String getNameBase() {
        return nameBase;
    }

    /**
     * @param nameBase the nameBase to set
     */
    public void setNameBase(String nameBase) {
        this.nameBase = nameBase;
    }
    
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Comparable Interface Methods">
    public static int COMPARABLE_LESS_THAN = -1;
    public static int COMPARABLE_EQUAL_TO = 0;
    public static int COMPARABLE_MORE_THAN = 1;

    /*
    * return negative integer (COMPARABLE_LESS_THAN = -1) if the specified workshift 
    * "o" sorts ordinally before this object, zero int (COMPARABLE_EQUAL_TO = 0)
    * if the specified workshift "o" sorts identically to this object, or a positive 
    * integer (COMPARABLE_MORE_THAN = 1) if the specified offSet "o" 
    * sorts ordinally after this object.
    *
    * "Root" workshifts (those will "null" parent references) will always sort 
    * before non-parent offsets (those that have parents)
     */
    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException("A null value Offset cannot be compared to another Offset item for sorting.");
        }

        if (!(o instanceof Workshift)) {
            throw new ClassCastException("A non-Offset data type cannot be compared to an Offset item for sorting.");
        }

        Workshift offset = (Workshift) o;

        // sort those with NULL parents to the top, then sort by offsetID, as a normal String compare
        if (((parentWorkshiftID == null) && (offset.parentWorkshiftID == null))
                || ((parentWorkshiftID != null) && (offset.parentWorkshiftID != null))) {
            return getWorkshiftID().compareTo(offset.getWorkshiftID());
        } else if (parentWorkshiftID == null) {
            return COMPARABLE_LESS_THAN;
        } else {  // therefore, must be offset.parentWorkshiftID == null
            return COMPARABLE_MORE_THAN;
        }
    }

//</editor-fold>

}
