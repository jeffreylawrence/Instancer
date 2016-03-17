/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.parser;

import static com.modern.instancer.parser.TextParser.safeIsNumeric;

/**
 *
 * @author kevinlawrence
 */
public class GCodeLine {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        this.line = "";
    }

    public GCodeLine(String line) {
        this.line = line;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    public static final String M6_IDENTIFIER = "M6";
    public static final String COMMENT_IDENTIFIER = "(";
    public static int NOT_FOUND = -1;
//    public static final String NOT_COMPLETE = "<NOT COMPLETE>";

    public static final String NEW_TOOL_IDENTIFIER = "N";

    private String line;

    /**
     * @return the line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(String line) {
        this.line = line;
    }

    public static boolean isNewToolLine(String line) {
        int index = line.indexOf(NEW_TOOL_IDENTIFIER);

        if (index == NOT_FOUND) {
            return false;
        } else {
            return (index < 3) && safeIsNumeric(line, index + 1) && safeIsNumeric(line, index + 4) && isComment(line);
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
    public static boolean isM6(String line) {
        int index = line.indexOf(M6_IDENTIFIER);

        if (index != NOT_FOUND) {
            return false;
        } else {
            // Eliminates  T0M6,          comment lines,   and M60 to M69 possible gcodes
            return !(isToolZero(line) || isComment(line) || safeIsNumeric(line, index + 2));
        }
    }

    public boolean isM6() {
        return isM6(getLine());
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

    public static boolean isToolZero(String line) {
        return (line.indexOf(T0_IDENTIFIER) != NOT_FOUND) && (line.indexOf(M6_IDENTIFIER) != NOT_FOUND);
    }

    public boolean isToolZero() {
        return isToolZero(line);
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
    public static boolean isComment(String line) {
        return line.contains(COMMENT_IDENTIFIER);
    }

    public boolean isComment() {
        return isComment(line);
    }
//</editor-fold>  

//</editor-fold>
}
