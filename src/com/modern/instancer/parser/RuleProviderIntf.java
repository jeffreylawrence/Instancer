/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.parser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kevinlawrence
 */
public interface RuleProviderIntf {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String LINE_FEED = String.format("\n");
    public static final String LINE_CARRIAGE_RETURN = String.format("\r");

    public static final String LBL_ID_FILE_LOCATION = "FILE_LOCATION";
//            ProgInfo = "File Location: " & FilePathNoNewLine
//        ProgInfo = Replace(ProgInfo, Environment.NewLine, "")
//        ProgInfo = Replace(ProgInfo, Chr(13), "")
//        ProgInfo = Replace(ProgInfo, Chr(10), "")
//        StepsInfo = "There are " & CStr(GcodeTempValue) & " machining steps for this program."

    public String getLabel(String labelID);
//    public ArrayList<String> getLabel();

    public HashMap<String, String> getPreprocessReplacements();

}
/*

Module GcodeModule

    Dim GcodeText As String 'contains the entire gcode file
    Dim LoopCounter As Integer 'used for the loop going thru the file
    Dim OneLine As String 'gets 1 line of gcodes at a time
    Public M6Result As Integer 'Counts the number of valid M6 in the file
    Dim LinesContent() As String 'This array contains each line of the file
    Dim CommentsRequest As String 'contains whatever info we are trying to find for each tool
    Dim ToolNumRequest As String 'contains the tool number.
    Dim HOffRequest As String 'contains the height offsets
    Dim DOffRequest As String 'contains the diameter offsets
    Dim OffsetsRequest As String 'contains the offsets
    Dim OffsetTruncated As String 'the OffsetsRequest value, split every 100 characters or so, for printing
    Dim AngleRequest As String 'contains the angles for each offset, if 4 or 5 axis machine
    Dim RPMrequest As String 'contains the RPM
    Dim FeedRequest As String 'contains the Feed
    Dim LowZRequest As Double 'contains the lowest Z level
    Dim OffsetTypes(0) As String 'contains all the types of offsets from the .ini file
    Dim LineIndex As Integer 'index number of each line of the file
    Dim SubTool As Integer 'Contains the line number
    Public HeaderSectionText As String 'this is the header section, before the 1st tool
    Public OneSection(0) As String 'contains 1 tool with all its related info, in each element of the array - Used for printing
    Public OffsetsPerm(0) As String 'this contains the offsets for each tool (0,0) = (tool, offset)
    Public OffSetPatternType As String 'contains the actual offset pattern type in this file
    Public ToolNumPerm(0) As String 'this contains the tool number for each tool
    Public ToolNumUnique(0) As String 'this contains the tool numbers as well, but removes the duplicated ones (ones that come more than once in the program)
    Public ToolCommentPerm(0) As String 'this contains the tool comments for each tool
    Public NoToolDup(0) As String 'contains all the tool numbers, without repeating the ones that come more than once
    Public ToolDescription(0) As String 'will contain only the tool description of each tool, after stripping all other comments from the tools

    Public Sub GcodeManipulation()
        Dim GcodeTempValue As Integer 'contains the result of what we will be looking for in the file
        Dim NewToolStartPos(0) As Integer 'Array containing the start position of each new tool
        Dim NewToolEndPos(0) As Integer 'Array containing the end position of each new tool
        Dim EachTool(0) As String 'contains the text of each tool seperately
        Dim ExtractorWindowText As String 'This contains everything to display in the Extractor window
        Dim NumberOfToolsCounter As Integer 'counts the current tool array position in NewToolStartPos()
        Dim NumberOfLines As Integer 'this contains how many lines are in the file
        Dim CurrentPos As Integer 'current position of the cursor
        Dim StartIndex As Integer 'index number of the start and end of each tool
        Dim TempCounter As Integer
        Dim HeaderComment As String 'contains the comments in the program header
        Dim ProgInfo As String 'contains the few lines at the very top of the extractor window
        Dim StepsInfo As String 'contains the number of tools in the program
        Dim HOffPerm(0) As String 'this contains the height offsets for each tool
        Dim DOffPerm(0) As String 'this contains the diameter offsets for each tool
        Dim OffsetLine(0) As Integer 'this is the line number where it found an offset, used to get the angle associated to this offset

        Dim AnglePerm(0) As String 'this contains the angles for each offset, for each tool
        Dim AngleCounter As Integer
        Dim AngleLine As String 'a line with the angle for associated offset
        Dim AnglePattern As Boolean = False 'true is pattern is matched
        Dim AngleMatched As Boolean = False
        Dim AnglePosition As Integer 'position of the angle in the line
        Dim PreviousAngle As String = Nothing 'Contains the angle from the previous offset, for when angles are the same as previous one
        
        Dim RPMperm(0) As String 'this contains the RPM for each tool
        Dim FeedPerm(0) As String 'this contains the feeds for each tool
        Dim LowZPerm(0) As Double 'this contains the lowest Z level for each tool
        Dim OffsetPattern As String 'this contains all the offset types in 1 single variable
        Dim OffsetTypeCount As Integer 'contains how many types of offsets are in the .ini file
        Dim OffsetCounter As Integer 'just a counter for a loop
        Dim OffsetOneLine As String
        Dim IsCommentLine As Boolean 'to check if we are dealing with a comment line
        Dim FilePathNoNewLine As String 'here i use a variable to strip any carriage return from the original path
        Dim DisplayExtractor As String = Nothing
        Dim OffsetCheck As Boolean 'this will check if it finds the pattern from the offset from the .ini file
        Dim NoCommentLine As String 'this will get the string back after the comments are stripped off
        Dim OffCounter As Integer 'simple counter 
        Dim OffPattern As String 'this contains the pattern to check against, to find the matching sequence in the line (which is the actual offset)

        OffsetTypeCount = 0
        GcodeText = ""
        LoopCounter = 0
        OneLine = ""
        M6Result = 0
        LineIndex = 0
        StartIndex = 0
        ProgInfo = ""
        ExtractorWindowText = ""
        SubTool = 0
        Array.Resize(NoToolDup, 0) 'this was necessary to reset the array, so that if we open a file when a file is already opened, the tool count will be correct.

        GcodeText = frmStartForm.GcodeText
        NumberOfLines = CountLines(GcodeText) 'here it calls the function that counts the number of lines and that puts each line in the array LinesContent
        GcodeTempValue = CInt(FindM6("M6")) 'to find the number of M6 in the file

        'For the MAM, when the program comes back in computer from the machine, there is no new line!!  This here will detect and fix that
        If GcodeTempValue = 0 Then
            If InStr(GcodeText, Chr(10)) <> 0 Then
                'MessageBox.Show("Prick!:")
                GcodeText = Replace(GcodeText, Chr(10), Environment.NewLine)
                NumberOfLines = CountLines(GcodeText) 'here it calls the function that counts the number of lines and that puts each line in the array LinesContent
                GcodeTempValue = CInt(FindM6("M6")) 'try again after adding carriage return
                frmStartForm.txtBoxCode.Text = GcodeText
            End If
        End If



        FilePathNoNewLine = frmStartForm.GcodeFilePath
        ProgInfo = "File Location: " & FilePathNoNewLine
        ProgInfo = Replace(ProgInfo, Environment.NewLine, "")
        ProgInfo = Replace(ProgInfo, Chr(13), "")
        ProgInfo = Replace(ProgInfo, Chr(10), "")
        StepsInfo = "There are " & CStr(GcodeTempValue) & " machining steps for this program."
        StepsInfo = Replace(StepsInfo, Environment.NewLine, "")
        StepsInfo = Replace(StepsInfo, Chr(13), "")
        StepsInfo = Replace(StepsInfo, Chr(10), "")

        Array.Resize(NewToolStartPos, M6Result) 'make the array the same size as the number of M6 found, so that it looks for new tools the right number of times.
        Array.Resize(NewToolEndPos, M6Result) 'make the array the same size as the number of M6 found, so that it looks for new tools the right number of times.
        Array.Resize(EachTool, M6Result) 'make the array the same size as number of M6 found.
        Array.Resize(ToolCommentPerm, M6Result)
        Array.Resize(ToolNumPerm, M6Result)
        Array.Resize(HOffPerm, M6Result)
        Array.Resize(DOffPerm, M6Result)
        Array.Resize(OffsetsPerm, M6Result)
        Array.Resize(OffsetLine, M6Result)
        Array.Resize(AnglePerm, M6Result)
        Array.Resize(RPMperm, M6Result)
        Array.Resize(FeedPerm, M6Result)
        Array.Resize(LowZPerm, M6Result)
        Array.Resize(OneSection, M6Result)

        'This section finds the line number (index in array) of each new tool beginning.
        For Each NewTool As String In LinesContent
            If InStr(NewTool, "N") <> 0 Then 'found a "N"
                CurrentPos = InStr(NewTool, "N")
                If IsNumeric(Mid(NewTool, CurrentPos + 1, 1)) And Not IsNumeric(Mid(NewTool, CurrentPos + 1, 4)) And CurrentPos < 3 Then 'this SEEMS to be a valid N sequence number, but some Goto command could refer to N100 for example...
                    IsCommentLine = IdCommentLine(NewTool)
                    If IsCommentLine = True Then 'Ok, now there is a N# line, with a comment on that line, this should be fairly certain that it is a valid N sequence line
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
                    End If
                End If
            End If
            LineIndex = LineIndex + 1
        Next NewTool

        '*** Get the program header comments ***
        HeaderComment = ""
        CommentsRequest = ""
        For TempCounter = 1 To NewToolStartPos(0)
            HeaderComment = GetComments(LinesContent(TempCounter - 1))
        Next

        '*** Here we split the offset types from the .ini file into an array we can use later to search for offsets thru the file

        OffsetPattern = frmStartForm.SectionManip(1)
        OffsetPattern = Replace(OffsetPattern, Environment.NewLine, "&") 'replacing the new line characters with the & symbol, so i can count how many offset types we have in the .ini file
        OffsetPattern = Replace(OffsetPattern, Chr(10), "") 'removing the spaces
        OffsetPattern = Replace(OffsetPattern, Chr(13), "&") 'adding a "&" at the end of the line, will make things simple in a bit...

        For TempCounter = 1 To OffsetPattern.Length 'this loop determines how many types of offsets we have in the .ini file
            If Mid(OffsetPattern, TempCounter, 1) = "&" Then
                OffsetTypeCount = OffsetTypeCount + 1
            End If
        Next
        ReDim OffsetTypes(OffsetTypeCount - 1) 'sets the number of element in the array to the number of offset types from .ini file
        For OffsetCounter = 0 To OffsetTypes.Length - 1 'looping thru each type of offsets
            If OffsetTypeCount > 1 Then 'more than 1 offset type
                For TempCounter = 1 To OffsetPattern.Length 'reading the line OffsetPattern 1 character at a time
                    If Mid(OffsetPattern, TempCounter, 1) = "&" Then 'the "&" is the splitter between each offset type
                        OffsetTypes(OffsetCounter) = Microsoft.VisualBasic.Left(OffsetPattern, TempCounter - 1) 'sets the OffsetTypes
                        OffsetPattern = Microsoft.VisualBasic.Right(OffsetPattern, (OffsetPattern.Length - TempCounter)) 'this removes the 1st part of the types, because it's already allocated on previous line
                    End If
                Next
            Else
                OffsetTypes(OffsetCounter) = OffsetPattern 'there is only 1 type, so the array is just 1 long and it is equal to OffsetPattern
            End If
        Next

        '*** Here we start to look for specific info for each tool ***

        For TempCounter = 0 To NewToolStartPos.Length - 1 'Here i am isolating each tool - this loop can be used to call some functions to get the number of offsets, min. Z levels, etc, for each tool
            CommentsRequest = "" 'resets the commentline variable for each tool
            ToolNumRequest = "No Tool Number!" 'reset Tool number for each tool
            HOffRequest = "No HOFF!" 'reset Height offset for each tool
            DOffRequest = "No DOFF!" 'reset diameter offset for each tool
            OffsetsRequest = "No Offsets!"
            RPMrequest = "No RPM!"
            FeedRequest = "No Feed!"
            LowZRequest = 1000
            AnglePattern = False
            AngleMatched = False

            For SubTool = NewToolStartPos(TempCounter) To NewToolEndPos(TempCounter) 'SubTool is the line number... so this loops each line, related to each tool

                '*** Calling Function to get the tool comments for each tool ***
                If SubTool < LinesContent.Length Then 'here, it will stop checking contents on the last line of the file. Otherwise, the next line gives alarm, checking for comments 1 line passed the last line...
                    CommentsRequest = GetComments(LinesContent(SubTool))
                    If CommentsRequest <> "" Then
                        ToolCommentPerm(TempCounter) = CommentsRequest
                    End If
                End If

                '*** Calling function to get the tool number ***
                ToolNumRequest = GetToolNumber(LinesContent(SubTool - 1))
                If ToolNumRequest <> "No Tool Number!" Then
                    ToolNumPerm(TempCounter) = ToolNumRequest
                End If

                '*** Calling function to get the Height Offset ***
                HOffRequest = GetHOff(LinesContent(SubTool - 1))
                HOffPerm(TempCounter) = HOffRequest

                '*** Calling the function to get the Diameter Offset ***
                DOffRequest = GetDOff(LinesContent(SubTool - 1))
                DOffPerm(TempCounter) = DOffRequest

                '*** Calling the function to get the OFFSETS ***
                'OffsetsRequest = GetOffsets(LinesContent(SubTool - 1))
                'OffsetsPerm(TempCounter) = OffsetsRequest

                '*** Calling the function to get the RPM ***
                RPMrequest = GetRPM(LinesContent(SubTool - 1))
                RPMperm(TempCounter) = RPMrequest

                '*** Calling the function to get the Feeds ***
                FeedRequest = GetFeed(LinesContent(SubTool - 1))
                FeedPerm(TempCounter) = FeedRequest

                '*** Calling the function to get the lowest Z level ***
                LowZRequest = GetLowZ(LinesContent(SubTool - 1))
                LowZPerm(TempCounter) = LowZRequest

                '***get the offsets and angles for each tool for 4 axis (B) machine (FMS)
                OffCounter = 0
                OffsetCheck = False
                OffsetOneLine = LinesContent(SubTool - 1)
                OffsetOneLine = RemoveUselessCharacters(OffsetOneLine) 'strips useless characters

                For OffCounter = 0 To OffsetTypes.Length - 1 'will do this loop for each type of offsets from .ini file
                    OffPattern = "*" & OffsetTypes(OffCounter) & "*"
                    OffsetCheck = OffsetOneLine Like OffPattern
                    If OffsetCheck = True Then 'found an offset matching an offset type from .ini file
                        OffSetPatternType = OffPattern
                        IsCommentLine = IdCommentLine(OffsetOneLine) 'checks if there are comments on the same line, calling function IdCommentLine
                        If IsCommentLine = True Then 'yes, there are comments
                            NoCommentLine = StripComments(OffsetOneLine) 'we are calling a function to remove the comments
                            OffsetOneLine = NoCommentLine
                        End If
                        OffsetOneLine = Replace(OffsetOneLine, "G00", "") 'some offset lines have a G0 on it, this will remove it
                        OffsetOneLine = Replace(OffsetOneLine, "G0", "")
                        If OffsetsRequest = "No Offsets!" Then 'This is the 1st offset it finds for current tool
                            OffsetsRequest = OffsetOneLine
                        ElseIf InStr(OffsetsRequest, OffsetOneLine) = 0 Then 'this ensures that I don't repeat the same offset more than once
                            OffsetsRequest = OffsetsRequest & ", " & OffsetOneLine
                        End If
                        'get the angle for this offset!
                        AngleMatched = False 'reset anglematched so it does take the previous angle by mistake
                        If frmStartForm.NumberOfAxisFlag = 4 Then 'this is a FMS program, we can get the B angles
                            For AngleCounter = SubTool + 1 To SubTool + 6 'looking up to 6 lines ahead
                                AngleLine = LinesContent(AngleCounter)
                                If InStr(AngleLine, "G356") <> 0 Then 'on probing line like this, there can be some B values, which are not an angle!  We have to disregard those lines
                                    Exit For
                                End If
                                AnglePattern = AngleLine Like "*B#*"
                                If AnglePattern = True Then
                                    AngleMatched = True
                                End If
                                AnglePattern = AngleLine Like "*B.#*"
                                If AnglePattern = True Then
                                    AngleMatched = True
                                End If
                                AnglePattern = AngleLine Like "*B-#*"
                                If AnglePattern = True Then
                                    AngleMatched = True
                                End If
                                AnglePattern = AngleLine Like "*B-.#*"
                                If AnglePattern = True Then
                                    AngleMatched = True
                                End If
                                If AngleMatched = True Then
                                    IsCommentLine = IdCommentLine(AngleLine) 'checks if there are comments on the same line, calling function IdCommentLine
                                    If IsCommentLine = True Then 'yes, there are comments
                                        NoCommentLine = StripComments(AngleLine) 'we are calling a function to remove the comments
                                        AngleLine = NoCommentLine
                                    End If
                                    AnglePosition = InStr(AngleLine, "B")
                                    If IsNumeric(Mid(AngleLine, AnglePosition + 1, 8)) Then
                                        AngleRequest = Mid(AngleLine, AnglePosition, 9)
                                        If AngleRequest = "B0" Then
                                            AngleRequest = "B0.0"
                                        End If
                                        PreviousAngle = AngleRequest
                                    ElseIf IsNumeric(Mid(AngleLine, AnglePosition + 1, 7)) Then
                                        AngleRequest = Mid(AngleLine, AnglePosition, 8)
                                        PreviousAngle = AngleRequest
                                    ElseIf IsNumeric(Mid(AngleLine, AnglePosition + 1, 6)) Then
                                        AngleRequest = Mid(AngleLine, AnglePosition, 7)
                                        PreviousAngle = AngleRequest
                                    ElseIf IsNumeric(Mid(AngleLine, AnglePosition + 1, 5)) Then
                                        AngleRequest = Mid(AngleLine, AnglePosition, 6)
                                        PreviousAngle = AngleRequest
                                    ElseIf IsNumeric(Mid(AngleLine, AnglePosition + 1, 4)) Then
                                        AngleRequest = Mid(AngleLine, AnglePosition, 5)
                                        PreviousAngle = AngleRequest
                                    ElseIf IsNumeric(Mid(AngleLine, AnglePosition + 1, 3)) Then
                                        AngleRequest = Mid(AngleLine, AnglePosition, 4)
                                        PreviousAngle = AngleRequest
                                    ElseIf IsNumeric(Mid(AngleLine, AnglePosition + 1, 2)) Then
                                        AngleRequest = Mid(AngleLine, AnglePosition, 3)
                                        PreviousAngle = AngleRequest
                                    ElseIf IsNumeric(Mid(AngleLine, AnglePosition + 1, 1)) Then
                                        AngleRequest = Mid(AngleLine, AnglePosition, 2)
                                        If AngleRequest = "B0" Then
                                            AngleRequest = "B0.0"
                                        End If
                                        PreviousAngle = AngleRequest
                                    Else
                                        MessageBox.Show("POS")
                                    End If
                                    AngleRequest = RemoveUselessCharacters(AngleRequest)
                                    PreviousAngle = RemoveUselessCharacters(PreviousAngle)
                                    Exit For
                                Else
                                    PreviousAngle = RemoveUselessCharacters(PreviousAngle)
                                    AngleRequest = PreviousAngle
                                End If
                            Next
                            AngleRequest = RemoveUselessCharacters(AngleRequest)
                            OffsetsRequest = OffsetsRequest & " => " & AngleRequest
                        End If
                    End If
                Next

            Next
            If OffsetsRequest.Length > 100 Then 'after 100 characters, the rest is lost on the printout.  This will split the lines in 100 max characters.
                OffsetTruncated = GetOffsetTruncated(OffsetsRequest)
                OffsetsRequest = OffsetTruncated
            End If
            OffsetsPerm(TempCounter) = OffsetsRequest
        Next

        '************************************************************************************************
        'New section for removing duplicate tools from the array ToolNumPerm.  The tools # listed in the array ToolNumUnique are not repeating.

        Dim NoDupCount As Integer 'counter
        Dim MiniCounter As Integer 'counter
        Dim ToolDup As Boolean 'flag to see if the tool is a duplicate or not
        Dim NoDupResize As Integer 'to grow the NoToolDup as we find tools that are not duplicated
        Dim FanFlag As Boolean 'if the fan is found, it's true
        Dim ProbeFlag As Boolean 'if the probe is found, it's true
        Dim UniqueTools As String 'will make a sentence saying how many unique tools there are
        Dim ToolNoFan As String 'will make a sentence saying how many unique tools without the chip fan
        Dim ToolNoProbe As String 'will make a sentence saying how many unique tools without the probe
        Dim DifferentTools As Integer 'the number of different tools, including fans and probes etc
        Dim FirstToolFlag As Boolean 'identifies the 1st tool

        NoDupCount = 0
        MiniCounter = 0
        ToolDup = False
        NoDupResize = 1
        FanFlag = False
        ProbeFlag = False
        DifferentTools = 0
        FirstToolFlag = True

        For NoDupCount = NoDupCount To ToolNumPerm.Length - 1 'looking into the original array of tool numbers

            If NoToolDup.Length = 0 Then
                If ToolCommentPerm(NoDupCount).Contains("CHIP FAN") = True Or ToolCommentPerm(NoDupCount).Contains("PROBE") = True Then 'found the chip fan tool or the probe
                    ToolDup = True 'makes sure we dont check the chip fan or the probe with this flag
                    If ToolCommentPerm(NoDupCount).Contains("CHIP FAN") = True Then
                        FanFlag = True 'found the fan
                    Else : ProbeFlag = True 'if it's not the fan, then it's the probe!
                    End If
                    ' Else

                End If
            Else
                For MiniCounter = MiniCounter To NoToolDup.Length - 1 'now checking each element of the new array (no duplicates) if the original array's current tool exists in the new array already

                    If ToolNumPerm(NoDupCount) = NoToolDup(MiniCounter) Then 'found a duplicate tool
                        ToolDup = True
                        Exit For
                    End If

                    If ToolCommentPerm(NoDupCount).Contains("CHIP FAN") = True Or ToolCommentPerm(NoDupCount).Contains("PROBE") = True Then 'found the chip fan tool or the probe
                        ToolDup = True 'makes sure we dont check the chip fan or the probe with this flag
                        If ToolCommentPerm(NoDupCount).Contains("CHIP FAN") = True Then
                            FanFlag = True 'found the fan
                        Else : ProbeFlag = True 'if it's not the fan, then it's the probe!
                        End If
                        Exit For
                    End If
                Next
            End If
            'Put the tools that are not duplicated or not a CHIP FAN inside the new array containing the tools that are unique
            'Also add the tool description for each of those tools
            If ToolDup = False Then
                Array.Resize(NoToolDup, NoDupResize)
                Array.Resize(ToolDescription, NoDupResize)
                ToolDescription(NoDupResize - 1) = GetToolDescriptionOnly(ToolCommentPerm(NoDupCount)) 'calling a function in the GcodeModule to keep only the tool description comments, not all the extra comments for each tool.
                NoToolDup(NoDupResize - 1) = ToolNumPerm(NoDupCount) 'add a NEW tool # in the array - keeps only the non duplicated tools
                NoDupResize = NoDupResize + 1
            End If
            ToolDup = False
            MiniCounter = 0
        Next

        ToolNoFan = "There are " & NoToolDup.Length & " tools to be checked with TLC (CHIP FAN IN USE)" & Environment.NewLine
        ToolNoProbe = "There are " & NoToolDup.Length & " tools to be checked with TLC (PROBE IN USE)" & Environment.NewLine
        DifferentTools = NoToolDup.Length 'if the fan or probe not used, then we need a value to the number of different tools

        If FanFlag = True Then
            DifferentTools = DifferentTools + 1 'the number of unique tools, including the fan
        End If

        If FanFlag = False Then
            ToolNoFan = "The CHIP FAN is not used in this program" & Environment.NewLine
        End If

        If ProbeFlag = True Then
            DifferentTools = DifferentTools + 1 'the number of unique tools, including the probe
        End If

        If ProbeFlag = False Then
            ToolNoProbe = "The PROBE is not used in this program" & Environment.NewLine
        End If

        If ProbeFlag = True And FanFlag = True Then
            ToolNoFan = "There are " & NoToolDup.Length & " tools to be checked with TLC (CHIP FAN AND PROBE ARE IN USE)" & Environment.NewLine
            ToolNoProbe = ""
        End If

        UniqueTools = "There are " & DifferentTools & " DIFFERENT tools" 'how many tools are not duplicated


        '*************************************************************************************************

        'Here, it's for showing the stuff in Extractor window.

        HeaderSectionText = ProgInfo & Environment.NewLine & StepsInfo & Environment.NewLine & UniqueTools & Environment.NewLine & ToolNoFan & ToolNoProbe & Environment.NewLine _
            & HeaderComment & Environment.NewLine & Environment.NewLine

        NumberOfToolsCounter = 1
        For NumberOfToolsCounter = 1 To NewToolStartPos.Length

            ExtractorWindowText = ExtractorWindowText & ToolCommentPerm(NumberOfToolsCounter - 1) 'The tool comments for each tool
            ExtractorWindowText = ExtractorWindowText & "Tool Number: " & ToolNumPerm(NumberOfToolsCounter - 1) & Environment.NewLine 'The tool number for each tool"
            ExtractorWindowText = ExtractorWindowText & "Height Offset: " & HOffPerm(NumberOfToolsCounter - 1) & Environment.NewLine
            ExtractorWindowText = ExtractorWindowText & "Diameter Offset: " & DOffPerm(NumberOfToolsCounter - 1) & Environment.NewLine
            ExtractorWindowText = ExtractorWindowText & "Offset: " & OffsetsPerm(NumberOfToolsCounter - 1) & Environment.NewLine
            ExtractorWindowText = ExtractorWindowText & "Speed: " & RPMperm(NumberOfToolsCounter - 1) & Environment.NewLine
            ExtractorWindowText = ExtractorWindowText & "Feed: " & FeedPerm(NumberOfToolsCounter - 1) & Environment.NewLine
            ExtractorWindowText = ExtractorWindowText & "Lowest Z Level: " & LowZPerm(NumberOfToolsCounter - 1)
            OneSection(NumberOfToolsCounter - 1) = ExtractorWindowText
            DisplayExtractor = DisplayExtractor & OneSection(NumberOfToolsCounter - 1) & Environment.NewLine & Environment.NewLine
            ExtractorWindowText = Nothing

        Next

        frmStartForm.txtBoxExtractor.Text = HeaderSectionText & DisplayExtractor

        'Enable TLC function below
        If frmStartForm.txtBoxExtractor.Text <> "" And frmStartForm.ActualFileExt = "RAZ" Or frmStartForm.ActualFileExt = "RAG" Or frmStartForm.ActualFileExt = "MC1" _
            Or frmStartForm.ActualFileExt = "YCM" Then
            frmStartForm.btnTLC.Enabled = True
        Else
            frmStartForm.btnTLC.Enabled = False
        End If

    End Sub

    Private Function FindM6(GcodeResult As String) As Integer

        Dim CurrentPos As Integer 'position of found M6 in the line
        Dim SearchResult As Integer  'returns what we were looking for
        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
        Dim IsToolZeroLine As Boolean 'true if it is a T0M6 line, false if not

        SearchResult = 0
        M6Result = 0

        For Each SingleLine As String In LinesContent
            IsCommentLine = IdCommentLine(SingleLine) 'calling the function to check if current line is a comment line => IsCommentLine = true if it is a comment line
            IsToolZeroLine = IdToolZeroLine(SingleLine) 'calling the function to check if current line is a T0M6 line
            If InStr(SingleLine, GcodeResult) <> 0 Then 'found the string sought after
                If GcodeResult = "M6" Then 'Enters this section only if looking for M6, to find how many tools in the file tools
                    CurrentPos = InStr(SingleLine, "M6")
                    If Not IsNumeric(Mid(SingleLine, CurrentPos + 2, 1)) And IsCommentLine = False And IsToolZeroLine = False Then 'Eliminates M60 to M69 possible gcodes, comment lines, and T0M6 line
                        M6Result = M6Result + 1
                        SearchResult = M6Result
                    End If
                End If
            End If
        Next

        Return SearchResult
    End Function

    Private Function CountLines(FileText As String) As Integer 'counts the number of lines and that puts each line in the array LinesContent
        Dim CharNL As Char = CChar(Environment.NewLine)
        LinesContent = GcodeText.Split(CharNL)
        Return (LinesContent.Length)
    End Function
    Private Function IdCommentLine(OneLine As String) As Boolean 'this finds if the current line is a comment line
        Dim CommentFlag As Boolean

        If InStr(OneLine, "(") <> 0 Then 'found a comment line
            CommentFlag = True
        Else
            CommentFlag = False
        End If
        Return CommentFlag
    End Function

    Private Function IdToolZeroLine(OneLine As String) As Boolean 'this finds if the current line is a comment line
        Dim ToolZeroLine As Boolean

        If InStr(OneLine, "T0") <> 0 And InStr(OneLine, "M6") <> 0 Then 'found a T0M6 line
            ToolZeroLine = True
        Else
            ToolZeroLine = False
        End If
        Return ToolZeroLine
    End Function

    Private Function StripComments(NoComments As String) As String 'This function removes comments from a string
        Dim CommentStartPos As Integer 'position of "("
        Dim CommentEndPos As Integer 'position of ")"
        Dim CommentLength As Integer 'Length of the comment, including the brackets ()
        Dim Comment As String 'This contains the comment itself, including the brackets

        CommentStartPos = InStr(NoComments, "(")
        CommentEndPos = InStr(NoComments, ")")
        CommentLength = (CommentEndPos - CommentStartPos) + 2
        Comment = Mid(NoComments, CommentStartPos, CommentLength) 'this contains the comments

        NoComments = Replace(NoComments, Comment, "") 'removing the comment from the string
        Return NoComments
    End Function
    Private Function GetComments(OneLine As String) As String 'This functions gets all the comments related to each tool
        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
        Dim BracketPos As Integer 'position of the "(" in the line
        Dim CommentFlag As Boolean

        CommentFlag = True

        IsCommentLine = IdCommentLine(OneLine)
        If IsCommentLine = True Then 'We have a comment line to work with here
            If SubTool < LinesContent.Length - 12 Then 'subtool is the line number, so here we get rid of the last comments in a file like Pallet Change and related...
                BracketPos = InStr(OneLine, "(") 'position of the "(" - start of actual comment
                If Mid(OneLine, BracketPos + 1, 1) = "R" And ((IsNumeric(Mid(OneLine, BracketPos + 2, 1))) Or (IsNumeric(Mid(OneLine, BracketPos + 2, 2))) Or (IsNumeric(Mid(OneLine, BracketPos + 2, 3)))) And Mid(OneLine, BracketPos + 3, 1) <> "/" Then
                    'previous line eliminates any comments that are the (R0.375) type, but let's in the comments like (R1/4 CORNER ROUNDING...)
                    CommentFlag = False
                End If
                If InStr(OneLine, "M300") <> 0 Or InStr(OneLine, "G356") <> 0 Or InStr(OneLine, "M0") <> 0 Then 'Not keeping these comments
                    CommentFlag = False
                End If
                If CommentFlag = True Then
                    OneLine = Replace(OneLine, Environment.NewLine, "")
                    OneLine = Replace(OneLine, Chr(13), "")
                    OneLine = Replace(OneLine, Chr(10), "")
                    If InStr(CommentsRequest, OneLine) = 0 Then 'making sure we dont repeat the same comments twice
                        CommentsRequest = CommentsRequest & OneLine & Environment.NewLine
                    End If
                End If
            End If
        End If
        Return CommentsRequest
    End Function

    Private Function GetToolNumber(OneLine As String) As String 'This function returns the current tool number
        Dim CurrentPos As Integer 'position of found M6 in the line
        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
        Dim IsToolZeroLine As Boolean 'true if it is a T0M6 line, false if not
        Dim ToolNumPos As Integer 'position of the "T" in the tool number line
        Dim NoCommentLine As String 'this will get the string back after the comments are stripped off

        IsToolZeroLine = IdToolZeroLine(OneLine) 'calling the function to check if current line is a T0M6 line => IsToolZeroLine = true then it is a T0M6 line

        If IsToolZeroLine = False Then 'eliminates T0M6 lines
            If InStr(OneLine, "M6") <> 0 Then 'found a line containing M6
                CurrentPos = InStr(OneLine, "M6")
                If Not IsNumeric(Mid(OneLine, CurrentPos + 2, 1)) Then 'Eliminates M60 to M69 possible gcodes
                    If InStr(OneLine, "T") <> 0 Then
                        IsCommentLine = IdCommentLine(OneLine) 'checks if there are comments on the same line, calling function IdCommentLine
                        If IsCommentLine = True Then 'yes, there are comments
                            NoCommentLine = StripComments(OneLine) 'we are calling a function to remove the comments
                            OneLine = NoCommentLine
                        End If
                        ToolNumPos = InStr(OneLine, "T") 'Position on the current line of "T"
                        If IsNumeric(Mid(OneLine, ToolNumPos + 1, 4)) Then
                            ToolNumRequest = (Mid(OneLine, ToolNumPos, 5))
                        ElseIf IsNumeric(Mid(OneLine, ToolNumPos + 1, 3)) Then
                            ToolNumRequest = (Mid(OneLine, ToolNumPos, 4))
                        ElseIf IsNumeric(Mid(OneLine, ToolNumPos + 1, 2)) Then
                            ToolNumRequest = (Mid(OneLine, ToolNumPos, 3))
                        ElseIf IsNumeric(Mid(OneLine, ToolNumPos + 1, 1)) Then
                            ToolNumRequest = (Mid(OneLine, ToolNumPos, 2))
                        End If
                    End If
                End If
            End If
        End If
        Return ToolNumRequest
    End Function

    Private Function GetHOff(OneLine As String) As String 'This function returns the current Height Offset
        Dim HOffPos As Integer 'position of the "H" in the G43 line
        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
        Dim NoCommentLine As String 'this will get the string back after the comments are stripped off

        If InStr(OneLine, "G43") <> 0 Then 'found a G43 line
            If InStr(OneLine, "H") <> 0 Then
                IsCommentLine = IdCommentLine(OneLine) 'checks if there are comments on the same line, calling function IdCommentLine
                If IsCommentLine = True Then 'yes, there are comments
                    NoCommentLine = StripComments(OneLine) 'we are calling a function to remove the comments
                    OneLine = NoCommentLine
                End If
                HOffPos = InStr(OneLine, "H")
                If IsNumeric(Mid(OneLine, HOffPos + 1, 3)) Then
                    If HOffRequest = "No HOFF!" Then 'this is the 1st HOFF it finds for current tool
                        HOffRequest = Mid(OneLine, HOffPos, 4)
                    Else
                        If InStr(HOffRequest, Mid(OneLine, HOffPos, 4)) = 0 Then 'checking that the HOFF was not already listed (avoid repeating the same HOFF)
                            HOffRequest = HOffRequest & ", " & Mid(OneLine, HOffPos, 4)
                        End If
                    End If
                ElseIf IsNumeric(Mid(OneLine, HOffPos + 1, 2)) Then
                        If HOffRequest = "No HOFF!" Then 'this is the 1st HOFF it finds for current tool
                            HOffRequest = Mid(OneLine, HOffPos, 3)
                    Else
                        If InStr(HOffRequest, Mid(OneLine, HOffPos, 3)) = 0 Then 'checking that the HOFF was not already listed (avoid repeating the same HOFF)
                            HOffRequest = HOffRequest & ", " & Mid(OneLine, HOffPos, 3)
                        End If
                    End If
                ElseIf IsNumeric(Mid(OneLine, HOffPos + 1, 1)) Then
                        If HOffRequest = "No HOFF!" Then 'this is the 1st HOFF it finds for current tool
                            HOffRequest = Mid(OneLine, HOffPos, 2)
                    Else
                        If InStr(HOffRequest, Mid(OneLine, HOffPos, 2)) = 0 Then 'checking that the HOFF was not already listed (avoid repeating the same HOFF)
                            HOffRequest = HOffRequest & ", " & Mid(OneLine, HOffPos, 2)
                        End If
                    End If
                End If
            End If
        End If
        Return HOffRequest
    End Function

    Private Function GetDOff(OneLine As String) As String 'This function returns the current Diameter Offset
        Dim DOffPos As Integer 'position of the "D" in the G41 or G42 line
        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
        Dim NoCommentLine As String 'this will get the string back after the comments are stripped off

        If InStr(OneLine, "G41") <> 0 Or InStr(OneLine, "G42") <> 0 Then 'found a G41 or G42 line
            If InStr(OneLine, "D") <> 0 Then
                IsCommentLine = IdCommentLine(OneLine) 'checks if there are comments on the same line, calling function IdCommentLine
                If IsCommentLine = True Then 'yes, there are comments
                    NoCommentLine = StripComments(OneLine) 'we are calling a function to remove the comments
                    OneLine = NoCommentLine
                End If
                DOffPos = InStr(OneLine, "D")
                If IsNumeric(Mid(OneLine, DOffPos + 1, 3)) Then
                    If DOffRequest = "No DOFF!" Then 'this is the 1st DOFF it finds for current tool
                        DOffRequest = Mid(OneLine, DOffPos, 4)
                    Else
                        If InStr(DOffRequest, Mid(OneLine, DOffPos, 4)) = 0 Then 'checking that the Doff was not already listed (avoid repeating the same DOFF)
                            DOffRequest = DOffRequest & ", " & Mid(OneLine, DOffPos, 4)
                        End If
                    End If
                ElseIf IsNumeric(Mid(OneLine, DOffPos + 1, 2)) Then
                    If DOffRequest = "No DOFF!" Then 'this is the 1st DOFF it finds for current tool
                        DOffRequest = Mid(OneLine, DOffPos, 3)
                    Else
                        If InStr(DOffRequest, Mid(OneLine, DOffPos, 3)) = 0 Then 'checking that the Doff was not already listed (avoid repeating the same DOFF)
                            DOffRequest = DOffRequest & ", " & Mid(OneLine, DOffPos, 3)
                        End If
                    End If
                ElseIf IsNumeric(Mid(OneLine, DOffPos + 1, 1)) Then
                    If DOffRequest = "No DOFF!" Then 'this is the 1st DOFF it finds for current tool
                        DOffRequest = Mid(OneLine, DOffPos, 2)
                    Else
                        If InStr(DOffRequest, Mid(OneLine, DOffPos, 2)) = 0 Then 'checking that the Doff was not already listed (avoid repeating the same DOFF)
                            DOffRequest = DOffRequest & ", " & Mid(OneLine, DOffPos, 2)
                        End If
                    End If
                End If
            End If
        End If
        Return DOffRequest
    End Function

    Private Function GetOffsets(OneLine As String) As String 'This function returns the offsets for current tool
        Dim OffsetCheck As Boolean 'this will check if it finds the pattern from the offset from the .ini file
        Dim OffPattern As String 'this contains the pattern to check against, to find the matching sequence in the line (which is the actual offset)
        Dim OffCount As Integer 'simple counter
        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
        Dim NoCommentLine As String 'this will get the string back after the comments are stripped off

        OffCount = 0
        OffsetCheck = False
        OneLine = Replace(OneLine, " ", "")
        OneLine = Replace(OneLine, Environment.NewLine, "")
        OneLine = Replace(OneLine, Chr(13), "")
        OneLine = Replace(OneLine, Chr(10), "")

        For OffCount = 0 To OffsetTypes.Length - 1
            OffPattern = "*" & OffsetTypes(OffCount) & "*"
            OffsetCheck = OneLine Like OffPattern
            If OffsetCheck = True Then
                IsCommentLine = IdCommentLine(OneLine) 'checks if there are comments on the same line, calling function IdCommentLine
                If IsCommentLine = True Then 'yes, there are comments
                    NoCommentLine = StripComments(OneLine) 'we are calling a function to remove the comments
                    OneLine = NoCommentLine
                End If
                If OffsetsRequest = "No Offsets!" Then 'This is the 1st offset it finds for current tool
                    OffsetsRequest = OneLine
                    'get the angle for this offset!

                ElseIf InStr(OffsetsRequest, OneLine) = 0 Then 'this ensures that I don't repeat the same offset more than once
                    OffsetsRequest = OffsetsRequest & ", " & OneLine
                End If
            End If
        Next

        Return OffsetsRequest
    End Function

    Private Function GetRPM(OneLine As String) As String 'This function returns the RPM for current tool
        Dim RPMPos As Integer
        Dim RPMCheck As Boolean
        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
        Dim NoCommentLine As String 'this will get the string back after the comments are stripped off

        RPMCheck = False

        RPMCheck = OneLine Like "*S#*" 'RPMCheck will = TRUE if it is a proper S value
        If RPMCheck = True Then
            IsCommentLine = IdCommentLine(OneLine) 'checks if there are comments on the same line, in case there could be a S in the comments... calling the function IdCommentLine
            If IsCommentLine = True Then 'yes, there are comments
                NoCommentLine = StripComments(OneLine) 'we are calling a function to remove the comments, makes life easier after that to get the RPM, cause we will be sure there is only 1 S on the line.
                OneLine = NoCommentLine
            End If
            RPMPos = InStr(OneLine, "S")
            If IsNumeric(Mid(OneLine, RPMPos + 1, 5)) Then
                If RPMrequest = "No RPM!" Then 'this is the 1st RPM it finds for current tool
                    RPMrequest = Mid(OneLine, RPMPos, 6)
                ElseIf InStr(RPMrequest, Mid(OneLine, RPMPos, 6)) = 0 Then 'checking that the RPM was not already listed (avoid repeating the same RPM)
                    RPMrequest = RPMrequest & ", " & Mid(OneLine, RPMPos, 6)
                End If
            ElseIf IsNumeric(Mid(OneLine, RPMPos + 1, 4)) Then
                If RPMrequest = "No RPM!" Then 'this is the 1st RPM it finds for current tool
                    RPMrequest = Mid(OneLine, RPMPos, 5)
                ElseIf InStr(RPMrequest, Mid(OneLine, RPMPos, 5)) = 0 Then 'checking that the RPM was not already listed (avoid repeating the same RPM)
                    RPMrequest = RPMrequest & ", " & Mid(OneLine, RPMPos, 5)
                End If
            ElseIf IsNumeric(Mid(OneLine, RPMPos + 1, 3)) Then
                If RPMrequest = "No RPM!" Then 'this is the 1st RPM it finds for current tool
                    RPMrequest = Mid(OneLine, RPMPos, 4)
                ElseIf InStr(RPMrequest, Mid(OneLine, RPMPos, 4)) = 0 Then 'checking that the RPM was not already listed (avoid repeating the same RPM)
                    RPMrequest = RPMrequest & ", " & Mid(OneLine, RPMPos, 4)
                End If
            ElseIf IsNumeric(Mid(OneLine, RPMPos + 1, 2)) Then
                If RPMrequest = "No RPM!" Then 'this is the 1st RPM it finds for current tool
                    RPMrequest = Mid(OneLine, RPMPos, 3)
                ElseIf InStr(RPMrequest, Mid(OneLine, RPMPos, 3)) = 0 Then 'checking that the RPM was not already listed (avoid repeating the same RPM)
                    RPMrequest = RPMrequest & ", " & Mid(OneLine, RPMPos, 3)
                End If
            ElseIf IsNumeric(Mid(OneLine, RPMPos + 1, 1)) Then
                If RPMrequest = "No RPM!" Then 'this is the 1st RPM it finds for current tool
                    RPMrequest = Mid(OneLine, RPMPos, 2)
                ElseIf InStr(RPMrequest, Mid(OneLine, RPMPos, 2)) = 0 Then 'checking that the RPM was not already listed (avoid repeating the same RPM)
                    RPMrequest = RPMrequest & ", " & Mid(OneLine, RPMPos, 2)
                End If
            End If
        End If
        Return RPMrequest
    End Function

    Private Function GetFeed(OneLine As String) As String 'This function returns the Feeds for current tool
        Dim FeedPos As Integer
        Dim FeedCheck As Boolean
        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
        Dim NoCommentLine As String 'this will get the string back after the comments are stripped off

        FeedCheck = False

        FeedCheck = OneLine Like "*F#*" 'feedcheck will = TRUE if it is a proper feed value
        If FeedCheck = True Then

            IsCommentLine = IdCommentLine(OneLine) 'checks if there are comments on the same line, in case there could be a F in the comments... calling the function IdCommentLine
            If IsCommentLine = True Then 'yes, there are comments
                NoCommentLine = StripComments(OneLine) 'we are calling a function to remove the comments, makes life easier after that to get the Feed, cause we will be sure there is only 1 F on the line.
                OneLine = NoCommentLine
            End If


            FeedPos = InStr(OneLine, "F")
            If FeedRequest = "No Feed!" Then
                FeedRequest = Microsoft.VisualBasic.Right(OneLine, (OneLine.Length - FeedPos) + 1) 'this is the 1st feed that it finds for current tool
            ElseIf InStr(FeedRequest, Microsoft.VisualBasic.Right(OneLine, (OneLine.Length - FeedPos) + 1)) = 0 Then 'here i check that the feed it just found is not already listed (avoid repeating same Feed)
                FeedRequest = FeedRequest & ", " & Microsoft.VisualBasic.Right(OneLine, (OneLine.Length - FeedPos) + 1) 'it was not already listed, so here i add it in the list
            End If
        End If
        Return FeedRequest
    End Function

    Private Function GetLowZ(Oneline As String) As Double 'This function returns the lower Z level for current tool
        Dim LowZPos As Integer
        Dim LowZCheck As Boolean
        Dim DoubleCheck As Boolean
        Dim NoCommentLine As String 'this will get the string back after the comments are stripped off
        Dim IsCommentLine As Boolean 'true if it is a comment line, false if not
        Dim TempLowZ As Double 'temporary Z value, will get compared to keep only the lowest Z every time it finds a Z
        Dim ZString As String 'the string starting from the "Z" to the end

        LowZCheck = False
        DoubleCheck = False
        IsCommentLine = False
        TempLowZ = 1000

        'Below here i check if the line has a proper Z address
        LowZCheck = Oneline Like "*Z#*"
        If LowZCheck = True Then
            DoubleCheck = True
        End If
        LowZCheck = Oneline Like "*Z.#*"
        If LowZCheck = True Then
            DoubleCheck = True
        End If
        LowZCheck = Oneline Like "*Z-#*"
        If LowZCheck = True Then
            DoubleCheck = True
        End If
        LowZCheck = Oneline Like "*Z-.#*"
        If LowZCheck = True Then
            DoubleCheck = True
        End If
        If InStr(Oneline, "G91") <> 0 Then 'this will ignore the lines with a Z on a G91 line
            DoubleCheck = False
        End If
        'It will go thru the rest of this function only if it has a proper Z line
        If DoubleCheck = True Then
            IsCommentLine = IdCommentLine(Oneline) 'checks if there are comments on the same line, in case there could be a Z in the comments... calling the function IdCommentLine
            If IsCommentLine = True Then 'yes, there are comments
                NoCommentLine = StripComments(Oneline) 'we are calling a function to remove the comments, makes life easier after that to get the lowest Z, cause we will be sure there is only 1 Z on the line.
                Oneline = NoCommentLine
            End If
            LowZPos = InStr(Oneline, "Z")
            ZString = Microsoft.VisualBasic.Right(Oneline, (Oneline.Length - LowZPos))

            If IsNumeric(Mid(ZString, 1, 8)) Then
                TempLowZ = CDbl((Mid(ZString, 1, 8)))
            ElseIf IsNumeric(Mid(ZString, 1, 7)) Then
                TempLowZ = CDbl(Mid(ZString, 1, 7))
            ElseIf IsNumeric(Mid(ZString, 1, 6)) Then
                TempLowZ = CDbl(Mid(ZString, 1, 6))
            ElseIf IsNumeric(Mid(ZString, 1, 5)) Then
                TempLowZ = CDbl(Mid(ZString, 1, 5))
            ElseIf IsNumeric(Mid(ZString, 1, 4)) Then
                TempLowZ = CDbl(Mid(ZString, 1, 4))
            ElseIf IsNumeric(Mid(ZString, 1, 3)) Then
                TempLowZ = CDbl(Mid(ZString, 1, 3))
            ElseIf IsNumeric(Mid(ZString, 1, 2)) Then
                TempLowZ = CDbl(Mid(ZString, 1, 2))
            ElseIf IsNumeric(Mid(ZString, 1, 1)) Then
                TempLowZ = CDbl(Mid(ZString, 1, 1))
            End If

            If TempLowZ < LowZRequest Then
                LowZRequest = TempLowZ
            End If
        End If
        Return LowZRequest
    End Function

    Private Function GetOffsetTruncated(SplitOffset As String) As String
        Dim ChangeLineValue As Double = 0 'will trigger something to change line when we print, because the line is too long for the paper
        Dim NewLinePos As Integer

        ChangeLineValue = (SplitOffset.Length / 100) 'after 100 characters, i want to change line, otherwise printout is missing some offsets
        ChangeLineValue = Math.Truncate(ChangeLineValue) 'this rounds to how many times there are 100 characters
        For OffCounter As Integer = 1 To CInt(ChangeLineValue)
            For SmallCounter As Integer = CInt((OffCounter * 100)) To SplitOffset.Length
                If Mid(SplitOffset, SmallCounter, 2) = ", " Then
                    NewLinePos = SmallCounter
                    Mid(SplitOffset, SmallCounter, 2) = Environment.NewLine
                    Exit For
                End If
            Next
        Next

        Return SplitOffset
    End Function

    Public Function RemoveUselessCharacters(NoUselessChar As String) As String 'removing all spaces, carriager returns, new lines, line feeds
        NoUselessChar = Replace(NoUselessChar, " ", "")
        NoUselessChar = Replace(NoUselessChar, Environment.NewLine, "")
        NoUselessChar = Replace(NoUselessChar, Chr(13), "")
        NoUselessChar = Replace(NoUselessChar, Chr(10), "")
        Return NoUselessChar
    End Function
    Public Function GetToolDescriptionOnly(ToolDescOnly As String) As String 'Getting only the 1st comment from an array containing multiple comments
        'used in TLC to extract only the tool description comments of the tools, otherwise, each tool has all the comments related to the tool, not just tool description.

        Dim BracketPos As Integer 'position of the closing bracket of the comments

        BracketPos = InStr(ToolDescOnly, ")")
        ToolDescOnly = Microsoft.VisualBasic.Left(ToolDescOnly, BracketPos) 'will only keep the left side of 1st comment
        BracketPos = InStr(ToolDescOnly, "(")
        ToolDescOnly = Microsoft.VisualBasic.Right(ToolDescOnly, (Len(ToolDescOnly) - (BracketPos - 1))) 'now only keeps what's in the brackets, nothing before, nothing after
        Return ToolDescOnly
    End Function
End Module


*/
