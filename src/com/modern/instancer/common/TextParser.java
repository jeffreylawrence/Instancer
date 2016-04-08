/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.common;

/**
 *
 * @author kevinlawrence
 */
public class TextParser {

//<editor-fold defaultstate="collapsed" desc="Static values">
    private static final int NOT_FOUND = -1;
    private static final String DOUBLE_CHARS = "-0123456789,.";
    private static final String INTEGER_CHARS = "-0123456789,";
    
    public static final String COMMENT_ID_START = "(";
    public static final String COMMENT_ID_END = ")";
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Numeric Methods">
    public static double getDoubleAtLocator(String valueLocator, String text) throws Exception {
        int index = text.indexOf(valueLocator);
        
        if (index == NOT_FOUND){
            throw new Exception(String.format("Locator '%s' not found in text '%s'.", valueLocator, text));
        } else {
            String dblText = "";
            String nextChar = "";
            index += valueLocator.length();
            boolean finished = false;
            
            while ((index < text.length()) && !finished){
                nextChar = String.valueOf(text.charAt(index));
                
                if (DOUBLE_CHARS.contains(nextChar)){
                    dblText += nextChar;
                } else {
                    finished = true;
                }
                
                index++;
            }
            return Double.valueOf(dblText);
        }
    }
    
    public static boolean safeIsNumeric(String text, int index) {
        if ((index < 0) || (index >= text.length())) {
            return false;
        } else {
            try {
                Integer.valueOf(String.valueOf(text.charAt(index)));
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="String Methods">
    public static String getComments(String text){
        return getSubString(text, COMMENT_ID_START, COMMENT_ID_END);
    }
    
    public static String getSubString(String text, String startLocator, String endLocator){
        String result = "";
        
        int start = text.indexOf(startLocator);
        int end = text.indexOf(endLocator);
        
        if ((start != NOT_FOUND) && (end != NOT_FOUND) && (start <= end)) {
            result = text.substring(start, end+1);
        }
        
        return result;
    }
//</editor-fold>
    
}
