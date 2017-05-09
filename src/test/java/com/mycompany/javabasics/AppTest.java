package com.mycompany.javabasics;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        System.out.println("hallo from tests");
        //fail("The test case is a prototype.");
    }

}
