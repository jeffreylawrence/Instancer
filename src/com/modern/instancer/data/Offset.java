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
public class Offset implements Comparable {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        parentOffsetID = "";
        rotate180Degrees = false;
    }

    public Offset(String offsetID, String parentOffsetID, boolean rotate180Degrees) {
        setOffsetID(offsetID);
        this.parentOffsetID = parentOffsetID;
        this.rotate180Degrees = rotate180Degrees;
    }

    public Offset(String offsetNameBase, int offsetInstance, String parentOffsetID, boolean rotate180Degrees) {
        this.nameBase = offsetNameBase;
        this.instance = offsetInstance;
        this.parentOffsetID = parentOffsetID;
        this.rotate180Degrees = rotate180Degrees;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Validation">
    private boolean validate() throws Exception {
        if (validator != null) {
            OperationStatusProviderIntf validationStatus = validator.validate(this);
            
            if (validationStatusHandler != null) {
                validationStatusHandler.handleStatus(validationStatus);
            } else if (!validationStatus.getStatus()) {
                throw new Exception("Improper data definition for Offset " + getOffsetID() + " => " + validationStatus.getStatusInformation());
            }
        }
        return true;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    public static final String G54P1_NAME_BASE = "G54.1P";

    private final int minimumInstance = 1;
    private final int maximumInstance = 300;

    private OffsetValidatorIntf validator;
    private OperationStatusHandlerIntf validationStatusHandler;

    private String nameBase;
    private int instance;
    private String parentOffsetID;
    private boolean rotate180Degrees;

    /**
     * @return a textual representation of the Offset
     */
    @Override
    public String toString() {
        return String.format("%s%s%s", getOffsetID(), isMain() ? " <BASE>" : "", rotate180Degrees ? " <ROTATE 180>" : "");
    }

    /**
     * @return the offsetID
     */
    public String getOffsetID() {
        return String.format("%s%d", nameBase, getInstance());
    }

    /**
     * @param offsetID the offsetID to set
     */
    public final void setOffsetID(String offsetID) {
        //parse the input offsetID and store the bits...
        if (offsetID.startsWith(G54P1_NAME_BASE)) {
            nameBase = G54P1_NAME_BASE;
            setInstance((int) Integer.valueOf(offsetID.substring(G54P1_NAME_BASE.length())));
        }
    }

    /**
     * @return the baseOriginal
     */
    public boolean isMain() {
        return (parentOffsetID == null) || (parentOffsetID == "");
    }

    /**
     * @return the parentOffsetID
     */
    public String getParentReferenceOffset() {
        return parentOffsetID;
    }

    /**
     * @param parentOffsetID the parentOffsetID to set
     */
    public void setParentOffsetID(String parentOffsetID) {
        this.parentOffsetID = parentOffsetID;
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Comparable Interface Methods">
    public static int COMPARABLE_LESS_THAN = -1;
    public static int COMPARABLE_EQUAL_TO = 0;
    public static int COMPARABLE_MORE_THAN = 1;

    /*
    * return negative integr (COMPARABLE_LESS_THAN = -1) if the specified offSet 
    * "o" sorts ordinally before this object, zero int (COMPARABLE_EQUAL_TO = 0)
    * if the specified offSet "o" sorts identically to this object, or a positive 
    * integer (COMPARABLE_MORE_THAN = 1) if the specified offSet "o" 
    * sorts ordinally after this object.
    *
    * "Root" offsets (those will "null" parent references) will always sort 
    * before non-parent offsets (those that have parents)
     */
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException("A null value Offset cannot be compared to another Offset item for sorting.");
        }

        if (!(o instanceof Offset)) {
            throw new ClassCastException("A non-Offset data type cannot be compared to an Offset item for sorting.");
        }

        Offset offset = (Offset) o;

        // sort those with NULL parents to the top, then sort by offsetID, as a normal String compare
        if (((parentOffsetID == null) && (offset.parentOffsetID == null))
                || ((parentOffsetID != null) && (offset.parentOffsetID != null))) {
            return getOffsetID().compareTo(offset.getOffsetID());
        } else if (parentOffsetID == null) {
            return COMPARABLE_LESS_THAN;
        } else {  // therefore, must be offset.parentOffsetID == null
            return COMPARABLE_MORE_THAN;
        }
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(OffsetValidatorIntf validator) {
        this.validator = validator;
    }

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
//</editor-fold>
}
