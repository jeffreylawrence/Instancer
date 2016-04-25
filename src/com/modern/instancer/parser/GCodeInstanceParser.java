package com.modern.instancer.parser;

import com.modern.instancer.common.InMemoryFile;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author kevinlawrence
 */
public class GCodeInstanceParser {
    
    public static final String REPEAT_START = "G54.1P10";
    public static final String REPEAT_END = "G91G28Z0.0M5";
    
    public static final String REPEAT_START_MARKER = "(INSTANCER - SECTION REPEAT START)";
    public static final String REPEAT_END_MARKER = "(INSTANCER - SECTION REPEAT END)";
    
    public static InMemoryFile createInstanceFile(InMemoryFile source, int repeatCounter){
        InMemoryFile instanceFile = new InMemoryFile();
        ArrayList<String> repeatSection = new ArrayList<>();
        
        for (int i = 0; i < source.getLines().size(); i++){
            if (source.getLines().get(i).contains(REPEAT_START)) {
                repeatSection.clear();
                
                do {
                    repeatSection.add(source.getLines().get(i));
                    if (source.getLines().get(i).contains(REPEAT_END)) {
                        break;
                    }
                    i++;
                } while (i < source.getLines().size());
                
                //put this into the new file
                for (int repeat = 0; repeat <= repeatCounter; repeat++) {
                    instanceFile.addLine(REPEAT_START_MARKER + " Instance #" + repeat);
                    
                    for (int section = 0; section < repeatSection.size(); section++) {
                        instanceFile.addLine(repeatSection.get(section));
                    }
                    
                    instanceFile.addLine(REPEAT_END_MARKER + " Instance #" + repeat);
                }
                
            } else {
                instanceFile.addLine(source.getLines().get(i));
            }
        }

        return instanceFile;
    }
    
    
}
