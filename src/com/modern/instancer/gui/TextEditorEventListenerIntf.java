/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.gui;

import com.modern.instancer.common.InMemoryFile;

/**
 *
 * @author kevinlawrence
 */
public interface TextEditorEventListenerIntf {
    public void handleProcessedFile(InMemoryFile file);
}
