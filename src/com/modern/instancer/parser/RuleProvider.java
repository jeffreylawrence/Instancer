/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.parser;

import java.util.HashMap;

/**
 *
 * @author kevinlawrence
 */
public class RuleProvider implements RuleProviderIntf {

    private static HashMap<String, String> labels;
    private static final String LBL_FILE_LOCATION = "File Location: %s";
    private static final String LBL_STEPS = "There are %d machining steps for this program.";

//    private static final String LBL_FILE_LOCATION = "";


    {
        labels = new HashMap<>();
        labels.put(RuleProviderIntf.LBL_ID_FILE_LOCATION, LBL_FILE_LOCATION);


    }

    @Override
    public String getLabel(String labelID) {
        return labels.get(labelID);
    }


//        ProgInfo = Replace(ProgInfo, Environment.NewLine, "")
//        ProgInfo = Replace(ProgInfo, Chr(13), "")
//        ProgInfo = Replace(ProgInfo, Chr(10), "")
    /*
    *  @return a hashmap of strings to be replaced during pre-processing
    */
    
    @Override
    public HashMap<String, String> getPreprocessReplacements() {
        HashMap<String, String> preprocessReplacements = new HashMap<>();
        preprocessReplacements.put(RuleProviderIntf.LINE_SEPARATOR, "");
        preprocessReplacements.put(RuleProviderIntf.LINE_CARRIAGE_RETURN, "");
        preprocessReplacements.put(RuleProviderIntf.LINE_FEED, "");

        return preprocessReplacements;
    }

}
