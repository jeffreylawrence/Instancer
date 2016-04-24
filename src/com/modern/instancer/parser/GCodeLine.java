/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.parser;

import static com.modern.instancer.common.TextParser.safeIsNumeric;
import java.util.ArrayList;

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

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Extended Properties">
    public static final String T0_IDENTIFIER = "T0";
    public static final String M6_IDENTIFIER = "M6";
    public static final String NEW_TOOL_IDENTIFIER = "N";
    public static final String COMMENT_IDENTIFIER = "(";
    public static final String M300_IDENTIFIER = "M300";
    public static final String G356_IDENTIFIER = "G356";
    public static final String M0_IDENTIFIER = "M0";

    public static int NOT_FOUND = -1;
//    public static final String NOT_COMPLETE = "<NOT COMPLETE>";

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

        if (index == NOT_FOUND) {
            return false;
        } else {
            // Eliminates  T0M6,          comment lines,   and M60 to M69 possible gcodes
            return !(isToolZero(line) || isComment(line) || safeIsNumeric(line, index + 2));
        }
    }

    public boolean isM6() {
        return isM6(getLine());
    }

    public static int countM6Lines(ArrayList<String> lines) {
        return countLines(lines, M6_IDENTIFIER);
    }
//</editor-fold>
    
    public static int countLines(ArrayList<String> lines, String searchText) {
        int count = 0;
        return lines.stream().filter((line) -> (line.contains(searchText))).map((_item) -> 1).reduce(count, Integer::sum);
//<editor-fold defaultstate="collapsed" desc="olde code">
//        count = lines.stream().filter((line) -> (line.contains(searchText))).map((_item) -> 1).reduce(count, Integer::sum);

//        for (String line : lines) {
//            if (line.contains(searchText)) {
//                count++;
//            }
//        }
//        return count;
//</editor-fold>
    }

    
    
    

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
    public static boolean isToolZero(String line) {
        return (line.contains(T0_IDENTIFIER) && line.contains(M6_IDENTIFIER));
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
    
    public static boolean isRadiusFraction(String text, int offset) {
        //Radius fractions will have the format R#[?#]/#[?#]
        return (text.indexOf("R", offset) == offset + 1) && (safeIsNumeric(text, offset + 2));
    }

    public static boolean isToolCommentToIgnore(String line) {
        return (isM300(line) || isG356(line) || isM0(line));
//<editor-fold defaultstate="collapsed" desc="logic">
//                If InStr(OneLine, "M300") <> 0 Or InStr(OneLine, "G356") <> 0 Or InStr(OneLine, "M0") <> 0 Then 'Not keeping these comments
//                    CommentFlag = False
//                End If
//</editor-fold>
    }

//<editor-fold defaultstate="collapsed" desc="isM300">
    public static boolean isM300(String line) {
        return line.contains(M300_IDENTIFIER);
    }

    public boolean isM300() {
        return isM300(line);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="isG356">
    public static boolean isG356(String line) {
        return line.contains(G356_IDENTIFIER);
    }

    public boolean isG356() {
        return isG356(line);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="isM0">
    public static boolean isM0(String line) {
        return line.contains(M0_IDENTIFIER);
    }

    public boolean is() {
        return isM0(line);
    }
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="isToolComment">
//<editor-fold defaultstate="collapsed" desc="GetComments - Tool Comments Logic">
//    Private Function GetComments(OneLine As String) As String 'This functions gets all the comments related to each tool
//        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
//        Dim BracketPos As Integer 'position of the "(" in the line
//        Dim CommentFlag As Boolean
//
//        CommentFlag = True
//
//        IsCommentLine = IdCommentLine(OneLine)
//        If IsCommentLine = True Then 'We have a comment line to work with here
// KL - not implemented yet!
//            If SubTool < LinesContent.Length - 12 Then 'subtool is the line number, so here we get rid of the last comments in a file like Pallet Change and related...
//                BracketPos = InStr(OneLine, "(") 'position of the "(" - start of actual comment
//                If Mid(OneLine, BracketPos + 1, 1) = "R" And ((IsNumeric(Mid(OneLine, BracketPos + 2, 1))) Or (IsNumeric(Mid(OneLine, BracketPos + 2, 2))) Or (IsNumeric(Mid(OneLine, BracketPos + 2, 3)))) And Mid(OneLine, BracketPos + 3, 1) <> "/" Then
//                    'previous line eliminates any comments that are the (R0.375) type, but let's in the comments like (R1/4 CORNER ROUNDING...)
//                    CommentFlag = False
//                End If
//                If InStr(OneLine, "M300") <> 0 Or InStr(OneLine, "G356") <> 0 Or InStr(OneLine, "M0") <> 0 Then 'Not keeping these comments
//                    CommentFlag = False
//                End If
//                If CommentFlag = True Then
//                    OneLine = Replace(OneLine, Environment.NewLine, "")
//                    OneLine = Replace(OneLine, Chr(13), "")
//                    OneLine = Replace(OneLine, Chr(10), "")
//                    If InStr(CommentsRequest, OneLine) = 0 Then 'making sure we dont repeat the same comments twice
//                        CommentsRequest = CommentsRequest & OneLine & Environment.NewLine
//                    End If
//                End If
//            End If
//        End If
//        Return CommentsRequest
//    End Function
//</editor-fold>
//    public static charAt(text, )
    public static boolean isToolComment(String line) {
        int commentStart = line.indexOf(COMMENT_IDENTIFIER);

        if (commentStart == NOT_FOUND) {
            return false;
        } else {
            return isRadiusFraction(line, commentStart) && !(isToolCommentToIgnore(line));
        }
//<editor-fold defaultstate="collapsed" desc="logic">
//                return (line.indexOf("R", commentStart) == commentStart + 1) && (safeIsNumeric(line, commentStart + 2));
//                return (line.regionMatches(true, index + 1, "R", 0, 1));
//                If Mid(OneLine, BracketPos + 1, 1) = "R" And ((IsNumeric(Mid(OneLine, BracketPos + 2, 1))) Or (IsNumeric(Mid(OneLine, BracketPos + 2, 2))) Or (IsNumeric(Mid(OneLine, BracketPos + 2, 3)))) And Mid(OneLine, BracketPos + 3, 1) <> "/" Then
//                    'previous line eliminates any comments that are the (R0.375) type, but let's in the comments like (R1/4 CORNER ROUNDING...)
//                    CommentFlag = False
//                End If
//                If InStr(OneLine, "M300") <> 0 Or InStr(OneLine, "G356") <> 0 Or InStr(OneLine, "M0") <> 0 Then 'Not keeping these comments
//                    CommentFlag = False
//                End If
//</editor-fold>
    }

    public boolean isToolComment() {
        return isToolComment(line);
    }

//</editor-fold>
//</editor-fold>
}
