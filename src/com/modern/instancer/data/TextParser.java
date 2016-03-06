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
public class TextParser {

//<editor-fold defaultstate="collapsed" desc="Extended Properties">
//    public static boolean contains(String text, String searchString) {
//        return text.contains(searchString);
//    }
//    
//    public boolean contains(String searchString) {
//        return contains(text, searchString);
//    }
    
    
    public static final String NOT_COMPLETE = "<NOT COMPLETE>";
    public static final String FILE_PATH = "File Location: %s";
    public static final String MACHINING_STEPS = "There are %d machining steps for this program.";
    
    public static ArrayList<String> parseGCodeText(ArrayList<String> gCodeLines){
        ArrayList<String> parsedData = new ArrayList<>();
        
        //put in file name/path (later...)
        parsedData.add(String.format(FILE_PATH, NOT_COMPLETE));
        
        //count the number of machining steps
        parsedData.add(String.format(MACHINING_STEPS, countM6Lines(gCodeLines)));
        
        
        // Find the number of tools in the program
//<editor-fold defaultstate="collapsed" desc="New Tool Code">
//        'This section finds the line number (index in array) of each new tool beginning.
//        For Each NewTool As String In LinesContent
//            If InStr(NewTool, "N") <> 0 Then 'found a "N"
//                CurrentPos = InStr(NewTool, "N")
//                If IsNumeric(Mid(NewTool, CurrentPos + 1, 1)) And Not IsNumeric(Mid(NewTool, CurrentPos + 1, 4)) And CurrentPos < 3 Then 'this SEEMS to be a valid N sequence number, but some Goto command could refer to N100 for example...
//                    IsCommentLine = IdCommentLine(NewTool)
//                    If IsCommentLine = True Then 'Ok, now there is a N# line, with a comment on that line, this should be fairly certain that it is a valid N sequence line
//
//                        NewToolStartPos(StartIndex) = LineIndex 'assigns the current line number to the new tool start index - this line is the line where the new tool starts
//                        If StartIndex > 0 Then 'not the 1st tool
//                            If StartIndex + 1 = NewToolEndPos.Length Then 'found the last tool
//                                NewToolEndPos(StartIndex) = LinesContent.Length 'for the last tool, the line number is the last line number in the file
//                            End If
//                            NewToolEndPos(StartIndex - 1) = LineIndex - 1 'assigns the line number to the end of a new tool
//                        End If
//                        StartIndex = StartIndex + 1
//                        If NewToolStartPos.Length = 1 Then
//                            NewToolEndPos(StartIndex - 1) = LinesContent.Length 'for a program with just 1 tool, the line number is the last line number in the file
//                        End If
//                    End If
//                End If
//            End If
//            LineIndex = LineIndex + 1
//        Next NewTool
//</editor-fold>
     
        
        return parsedData;
    }
    
    
    
    
    public static final String M6_IDENTIFIER = "M6";
    public static final String COMMENT_IDENTIFIER = "(";
    public static int NOT_FOUND = -1;

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

    public static int countM6Lines(ArrayList<String> lines){
        return countLines(lines, M6_IDENTIFIER);
    }
    
    //TODO - consider refactor as generic, passing in boolean method with param "String", returning boolean
    public static int countLines(ArrayList<String> lines, String searchText){
        int count = 0;
        
        for (String line: lines){
            if (isM6(line)) {
                count++;
            }
        }
        return count;
    }
    
    
//                    If Not IsNumeric(Mid(SingleLine, CurrentPos + 2, 1)) And IsCommentLine = False And IsToolZeroLine = False Then 'Eliminates M60 to M69 possible gcodes, comment lines, and T0M6 line
    public static boolean isM6(String text) {
        int index = text.indexOf(M6_IDENTIFIER);

        if (index == NOT_FOUND) {
            return false;
        } else {
            return !(isToolZero(text) || isComment(text) || safeIsNumeric(text, index + 2));
        }
    }

    public boolean isM6() {
        return isM6(text);
    }

    public static boolean isToolZero(String text) {
        return false;
    }

    public boolean isToolZero() {
        return isToolZero(text);
    }

    public static boolean isComment(String text) {
        return text.contains(COMMENT_IDENTIFIER);
    }

    public boolean isComment() {
        return isComment(text);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        text = "";
    }

    public TextParser() {
    }

    public TextParser(String text) {
        this.text = text;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private String text;

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
//</editor-fold>

}
