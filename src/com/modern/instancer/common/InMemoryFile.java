/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.common;

import com.modern.instancer.common.Library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author kevinlawrence
 */
public final class InMemoryFile {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        setLines(new ArrayList<>());
    }

    public InMemoryFile() {}

    public InMemoryFile(ArrayList<String> lines) {
        this.lines = lines;
    }

    public InMemoryFile(String[] lines) {
        for(String line: lines){
            this.lines.add(line);
        }
    }

    public InMemoryFile(File file) {
        setFile(file);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private final static String NEW_LINE = "\n";

    private ArrayList<String> lines;
    private File file;

    /**
     * @return the lines
     */
    public ArrayList<String> getLines() {
        return lines;
    }

    /**
     * @param lines the lines to set
     */
    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    /**
     * @return the file path
     */
    public String getAbsolutePath() {
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return "";
        }
    }

    public boolean addLine(String line){
        if (!line.contains(NEW_LINE)){
            return lines.add(line + NEW_LINE);
        } else {
            return lines.add(line);
        }
    }
    
    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    addLine(line + NEW_LINE);
                }
            } catch (IOException e) {
//                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
//            e.printStackTrace();
        }
    }

    public void openFile() {
        File newFile = Library.openFile();

        if (newFile != null) {
            setFile(newFile);
        }
    }

//</editor-fold>
}
