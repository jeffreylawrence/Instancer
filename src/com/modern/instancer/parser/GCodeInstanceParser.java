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

    public static final String REPEAT_START = "G54";
    public static final String REPEAT_END = "G91";
//    public static final String REPEAT_START = "G54.1P10";
//    public static final String REPEAT_END = "G91G28Z0.0M5";

    public static final String REPEAT_START_MARKER = "(INSTANCER - SECTION REPEAT START)";
    public static final String REPEAT_END_MARKER = "(INSTANCER - SECTION REPEAT END)";

    public static final String REMOVE_LINE = "(REMOVED[%s]:%s)";
    public static final String REMOVE_LINE_REASON_TOOL_CHANGE = "TOOL CHANGE";

    public static InMemoryFile createInstanceFile(InMemoryFile source, int instanceCounter, String retraction) {
        InMemoryFile instanceFile = new InMemoryFile();
        ArrayList<String> repeatSection = new ArrayList<>();

        for (int i = 0; i < source.getLines().size(); i++) {
            if (source.getLines().get(i).contains(REPEAT_START)) {
                repeatSection.clear();

                do {
                    if (source.getLines().get(i).contains(REPEAT_END)) {
                        repeatSection.add(retraction);
                        i--;
                        break;
                    }
                    repeatSection.add(source.getLines().get(i));
                    i++;
                } while (i < source.getLines().size());

                //put this into the new file
                for (int repeat = 0; repeat <= instanceCounter; repeat++) {
                    instanceFile.addLine(REPEAT_START_MARKER + " Instance #" + repeat);

                    for (int lineNumber = 0; lineNumber < repeatSection.size(); lineNumber++) {
                        instanceFile.addLine(repeatSection.get(lineNumber));
                    }

                    instanceFile.addLine(REPEAT_END_MARKER + " Instance #" + repeat);

                    // scrub the lines, after the first section:
                    //   - remove tool change lines
                    if (repeat == 0) {
                        // iterate backwards, so we don't get index out of bounds error 
                        for (int lineNumber = repeatSection.size() - 1; lineNumber >= 0 ; lineNumber--) {
                            if (GCodeLine.isToolChangeLine(repeatSection.get(lineNumber))){
                                repeatSection.set(lineNumber, String.format(REMOVE_LINE, REMOVE_LINE_REASON_TOOL_CHANGE, repeatSection.get(lineNumber)));
                            }
                        }

                    }

                }

            } else {
                instanceFile.addLine(source.getLines().get(i));
            }
        }

        return instanceFile;
    }

}
