/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.data;

/**
 *
 * @author kevinlawrence
 */
public class Tool {
    
    
    int startPosition;                  //  NewToolStartPos 'Array containing the start position of each new tool
    int endPosition;                    //  NewToolEndPos   'Array containing the end position of each new tool
    String toolName;                    //  EachTool        'contains the text of each tool seperately
    String comments;                    //  ToolCommentPerm 'this contains the tool comments for each tool
    String number;                      //  ToolNumPerm     'this contains the tool number for each tool
    String heightOffset;                //  HOffPerm        'this contains the height offsets for each tool
    String diameterOffset;              //  DOffPerm        'this contains the diameter offsets for each tool
    String offsets;                     //  OffsetsPerm     'this contains the offsets for each tool (0,0) = (tool, offset)
    String offsetLine;                  //  OffsetLine      'this is the line number where it found an offset, used to get the angle associated to this offset

    String angle;                       //  AnglePerm       'this contains the angles for each offset, for each tool
    String rpm;                         //  RPMperm         'this contains the RPM for each tool 
    String feed;                        //  FeedPerm        'this contains the feeds for each tool 
    String lowZ;                        //  LowZPerm        'this contains the lowest Z level for each tool 
    String oneSection; //TODO - refactor toString() //  OneSection      'contains 1 tool with all its related info, in each element of the array - Used for printing 
    
//        Array.Resize(NewToolStartPos, M6Result) 'make the array the same size as the number of M6 found, so that it looks for new tools the right number of times.
//        Array.Resize(NewToolEndPos, M6Result) 'make the array the same size as the number of M6 found, so that it looks for new tools the right number of times.
//        Array.Resize(EachTool, M6Result) 'make the array the same size as number of M6 found.
//        Array.Resize(ToolCommentPerm, M6Result)
//        Array.Resize(ToolNumPerm, M6Result)
//        Array.Resize(HOffPerm, M6Result)
//        Array.Resize(DOffPerm, M6Result)
    
//        Array.Resize(OffsetsPerm, M6Result)
//        Array.Resize(OffsetLine, M6Result)

//        Array.Resize(AnglePerm, M6Result)
//        Array.Resize(RPMperm, M6Result)
//        Array.Resize(FeedPerm, M6Result)
//        Array.Resize(LowZPerm, M6Result)
//        Array.Resize(OneSection, M6Result)
    
    //rules for finding a new tool
    // Line contains an N in first 3 positions
    // Line contains at least one numeric character after it, and not more than 3 
    // Line contains a comment, Indicated by a "("
    // 
    
}
