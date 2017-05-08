package com.mycompany.javabasics;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    
    public AppTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of dateTimeTests method, of class App.
     */
    @Test
    public void testDateTimeTests() {
        System.out.println("dateTimeTests");
        App instance = new App();
        instance.dateTimeTests();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showTextProps method, of class App.
     */
    @Test
    public void testShowTextProps() {
        System.out.println("showTextProps");
        App instance = new App();
        instance.showTextProps();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printUtf8CharTable method, of class App.
     */
    @Test
    public void testPrintUtf8CharTable() throws Exception {
        System.out.println("printUtf8CharTable");
        App instance = new App();
        instance.printUtf8CharTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of splitTest method, of class App.
     */
    @Test
    public void testSplitTest() {
        System.out.println("splitTest");
        App instance = new App();
        instance.splitTest();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    
}
