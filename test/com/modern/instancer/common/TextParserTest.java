/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modern.instancer.common;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kevinlawrence
 */
public class TextParserTest {
    
//    public TextParserTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }

    public static final String G54P1_SET_TEST = "G10L20P10X-14.9572Y-10.9581Z-14.7454A0.0C0.0(A-90. C180.)";

    public static final String AXIS_ID_X = "X";
    public static final String AXIS_ID_Y = "Y";
    public static final String AXIS_ID_Z = "Z";
    public static final String AXIS_ID_A = "A";
    public static final String AXIS_ID_C = "C";
    /**
     * Test of getDoubleAtLocator method, of class TextParser.
     */
    @Test
    public void testGetDoubleAtLocator() throws Exception {
        System.out.println("getDoubleAtLocator");
        
        String text = G54P1_SET_TEST;
        double expResult,result;
        String valueLocator = "";
        
        valueLocator = AXIS_ID_X;
        expResult = -14.9572;
        result = TextParser.getDoubleAtLocator(valueLocator, text);
//        System.out.println("" + result);
        assertEquals(expResult, result, 0.0);

        valueLocator = AXIS_ID_Y;
        expResult = -10.9581;
        result = TextParser.getDoubleAtLocator(valueLocator, text);
        assertEquals(expResult, result, 0.0);

        valueLocator = AXIS_ID_Z;
        expResult = -14.7454;
        result = TextParser.getDoubleAtLocator(valueLocator, text);
        assertEquals(expResult, result, 0.0);

        valueLocator = AXIS_ID_A;
        expResult = 0.0;
        result = TextParser.getDoubleAtLocator(valueLocator, text);
        assertEquals(expResult, result, 0.0);

        valueLocator = AXIS_ID_C;
        expResult = 0.0;
        result = TextParser.getDoubleAtLocator(valueLocator, text);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getSubString method, of class TextParser.
     */
    @Test
    public void testGetComments() {
        System.out.println("getComments");
        
        String text = G54P1_SET_TEST;
        String expResult, result;
        
        expResult = "(A-90. C180.)";
        result = TextParser.getComments(text);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSubString method, of class TextParser.
     */
    @Test
    public void testGetSubString() {
        System.out.println("getSubString");
        
        String text = G54P1_SET_TEST;
        String expResult, result;
        
        expResult = "(A-90. C180.)";
        result = TextParser.getSubString(text, TextParser.COMMENT_ID_START, TextParser.COMMENT_ID_END);
        assertEquals(expResult, result);
    }
    
}
