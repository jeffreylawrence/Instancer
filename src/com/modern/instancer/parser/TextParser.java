/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.parser;

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

    public static ArrayList<String> parseGCodeText(ArrayList<String> gCodeLines) {
        ArrayList<String> parsedData = new ArrayList<>();

        //put in file name/path (later...)
        parsedData.add(String.format(FILE_PATH, NOT_COMPLETE));

        //count the number of machining steps
        parsedData.add(String.format(MACHINING_STEPS, countM6Lines(gCodeLines)));
//<editor-fold defaultstate="collapsed" desc="Machining Steps - 'FindM6' Sub Logic">
//    Private Function FindM6(GcodeResult As String) As Integer
//
//        Dim CurrentPos As Integer 'position of found M6 in the line
//        Dim SearchResult As Integer  'returns what we were looking for
//        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
//        Dim IsToolZeroLine As Boolean 'true if it is a T0M6 line, false if not
//
//        SearchResult = 0
//        M6Result = 0
//
//        For Each SingleLine As String In LinesContent
//            IsCommentLine = IdCommentLine(SingleLine) 'calling the function to check if current line is a comment line => IsCommentLine = true if it is a comment line
//            IsToolZeroLine = IdToolZeroLine(SingleLine) 'calling the function to check if current line is a T0M6 line
//            If InStr(SingleLine, GcodeResult) <> 0 Then 'found the string sought after
//                If GcodeResult = "M6" Then 'Enters this section only if looking for M6, to find how many tools in the file tools
//                    CurrentPos = InStr(SingleLine, "M6")
//                    If Not IsNumeric(Mid(SingleLine, CurrentPos + 2, 1)) And IsCommentLine = False And IsToolZeroLine = False Then 'Eliminates M60 to M69 possible gcodes, comment lines, and T0M6 line
//                        M6Result = M6Result + 1
//                        SearchResult = M6Result
//                    End If
//                End If
//            End If
//        Next
//
//        Return SearchResult
//    End Function
//</editor-fold>
        
        

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
        ArrayList<Integer> newToolLineIndexList = getNewToolLineStartIndexes(gCodeLines);

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

    public static int countM6Lines(ArrayList<String> lines) {
        return countLines(lines, M6_IDENTIFIER);
    }

    //TODO - consider refactor as generic, passing in boolean method with param "String", returning boolean
    public static int countLines(ArrayList<String> lines, String searchText) {
        int count = 0;

//        count = lines.stream().filter((line) -> (isM6(line))).map((_item) -> 1).reduce(count, Integer::sum);
        for (String line : lines) {
            if (isM6(line)) {
                count++;
            }
        }
        return count;
    }
    
    
//<editor-fold defaultstate="collapsed" desc="getNewToolLineIndexes()">
    
    private static ArrayList<Integer> getNewToolLineStartIndexes(ArrayList<String> lines) {
        ArrayList<Integer> lineIndexes = new ArrayList<>();
        
        for (int i = 0; i < lines.size(); i++) {
            if (isNewToolLine(lines.get(i))) {
                lineIndexes.add(i);
            }
        }
        
        //TODO - consider if this should be wrapped up into a "Tool" class
        
//<editor-fold defaultstate="collapsed" desc="End of 'tool section' logic">
//      There appears to be special processing for the "end" of each tool section
//      which calculates the last line for the section; the logic is that
//      the last line of a tool section is the line before the line that starts
//      next section (obviously!) except for the last tool, which use the end of
//      file as the end point (obviously!)
//      Ignore this for now, and respect the processing at the other end that
//      uses the structure
/*
NewToolStartPos(StartIndex) = LineIndex 'assigns the current line number to the new tool start index - this line is the line where the new tool starts
If StartIndex > 0 Then 'not the 1st tool
If StartIndex + 1 = NewToolEndPos.Length Then 'found the last tool
NewToolEndPos(StartIndex) = LinesContent.Length 'for the last tool, the line number is the last line number in the file
End If
NewToolEndPos(StartIndex - 1) = LineIndex - 1 'assigns the line number to the end of a new tool
End If
StartIndex = StartIndex + 1
If NewToolStartPos.Length = 1 Then
NewToolEndPos(StartIndex - 1) = LinesContent.Length 'for a program with just 1 tool, the line number is the last line number in the file
End If
*/
//</editor-fold>
        
        return lineIndexes;
    }
    
    public static final String NEW_TOOL_IDENTIFIER = "N";
    
    public static boolean isNewToolLine(String text){
        int index = text.indexOf(NEW_TOOL_IDENTIFIER);
        
        if (index == NOT_FOUND){
            return false;
        } else {
            return (index < 3) && safeIsNumeric(text, index + 1) && safeIsNumeric(text, index + 4) && isComment(text);
        }
    }
    
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

//</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="isM6 -> M6 is a tool line">
    
    
//        For Each SingleLine As String In LinesContent
//            IsCommentLine = IdCommentLine(SingleLine) 'calling the function to check if current line is a comment line => IsCommentLine = true if it is a comment line
//            IsToolZeroLine = IdToolZeroLine(SingleLine) 'calling the function to check if current line is a T0M6 line
//            If InStr(SingleLine, GcodeResult) <> 0 Then 'found the string sought after
//                If GcodeResult = "M6" Then 'Enters this section only if looking for M6, to find how many tools in the file tools
//                    CurrentPos = InStr(SingleLine, "M6")
//                    If Not IsNumeric(Mid(SingleLine, CurrentPos + 2, 1)) And IsCommentLine = False And IsToolZeroLine = False Then 'Eliminates M60 to M69 possible gcodes, comment lines, and T0M6 line
//                        M6Result = M6Result + 1
//                        SearchResult = M6Result
//                    End If
//                End If
//            End If
//        Next
    
//                    If Not IsNumeric(Mid(SingleLine, CurrentPos + 2, 1)) And IsCommentLine = False And IsToolZeroLine = False Then 'Eliminates M60 to M69 possible gcodes, comment lines, and T0M6 line
    public static boolean isM6(String text) {
        int index = text.indexOf(M6_IDENTIFIER);
        
        if (index != NOT_FOUND) {
            return false;
        } else {
            // Eliminates  T0M6,          comment lines,   and M60 to M69 possible gcodes
            return !(isToolZero(text) || isComment(text) || safeIsNumeric(text, index + 2));
        }
    }

    public boolean isM6() {
        return isM6(text);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="isToolZero">
    
////<editor-fold defaultstate="collapsed" desc="IdToolZeroLine Sub">
//    Private Function IdToolZeroLine(OneLine As String) As Boolean 'this finds if the current line is a comment line
//        Dim ToolZeroLine As Boolean
//
//        If InStr(OneLine, "T0") <> 0 And InStr(OneLine, "M6") <> 0 Then 'found a T0M6 line
//            ToolZeroLine = True
//        Else
//            ToolZeroLine = False
//        End If
//        Return ToolZeroLine
//    End Function
//</editor-fold>
    
    public static final String T0_IDENTIFIER = "T0";
    
    
    public static boolean isToolZero(String text) {
        return (text.indexOf(T0_IDENTIFIER) != NOT_FOUND) && (text.indexOf(M6_IDENTIFIER) != NOT_FOUND);
    }
    
    public boolean isToolZero() {
        return isToolZero(text);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="isComment">
    
//<editor-fold defaultstate="collapsed" desc="IdCommentLine Sub Logic">
//    Private Function IdCommentLine(OneLine As String) As Boolean 'this finds if the current line is a comment line
//        Dim CommentFlag As Boolean
//
//        If InStr(OneLine, "(") <> 0 Then 'found a comment line
//            CommentFlag = True
//        Else
//            CommentFlag = False
//        End If
//        Return CommentFlag
//    End Function
//</editor-fold>
    
    public static boolean isComment(String text) {
        return text.contains(COMMENT_IDENTIFIER);
    }
    
    public boolean isComment() {
        return isComment(text);
    }
//</editor-fold>
    
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
