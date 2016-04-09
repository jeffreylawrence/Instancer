/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.data;

import com.modern.instancer.common.TextParser;

/**
 *
 * @author kevinlawrence
 */
public class FiveAxisData {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        comment = "";
        source = "";
    }
    
    public FiveAxisData(){}

    public FiveAxisData(String source) throws Exception {
        parseSource(source);
    }
    
    public FiveAxisData(double x, double y, double z, double a, double c, String comment) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = a;
        this.c = c;

        this.comment = comment;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Methods">
    private void parseSource(String source) throws Exception {
        this.source = source;
        
        x = TextParser.getDoubleAtLocator(AXIS_ID_X, source);
        y = TextParser.getDoubleAtLocator(AXIS_ID_Y, source);
        z = TextParser.getDoubleAtLocator(AXIS_ID_Z, source);
        a = TextParser.getDoubleAtLocator(AXIS_ID_A, source);
        c = TextParser.getDoubleAtLocator(AXIS_ID_C, source);
        
        comment = TextParser.getComments(source);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
//    public static final String G54P1_SET_TEST = "G10L20P10X-14.9572Y-10.9581Z-14.7454A0.0C0.0(A-90. C180.)";

    public static final String AXIS_ID_X = "X";
    public static final String AXIS_ID_Y = "Y";
    public static final String AXIS_ID_Z = "Z";
    public static final String AXIS_ID_A = "A";
    public static final String AXIS_ID_C = "C";

    private double x, y, z, a, c;
    private String comment, source;

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @return the a
     */
    public double getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * @return the c
     */
    public double getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(double c) {
        this.c = c;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }
//</editor-fold>

}
