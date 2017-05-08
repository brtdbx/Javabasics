/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brUtils;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 1790
 */
public class MathUtilsTest {
    
    public MathUtilsTest() {
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
     * Test of isWholeNumber method, of class MathUtils.
     */
    @Test
    public void testIsWholeNumber01() {
        System.out.println("isWholeNumber: " +  "-12.000000000000000000");
        BigDecimal bd = new BigDecimal("-12.000000000000000000");
        boolean expResult = true;
        boolean result = MathUtils.isWholeNumber(bd);
        assertEquals(expResult, result);
//        fail("Test testIsWholeNumber01 ist gescheitert");
    }


    /**
     * Test of isWholeNumber method, of class MathUtils.
     */
    @Test
    public void testIsWholeNumber02() {
        System.out.println("isWholeNumber: " +  "-12.00000000000000000001");
        BigDecimal bd = new BigDecimal("-12.0000000000000000001");
        boolean expResult = false;
        boolean result = MathUtils.isWholeNumber(bd);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
 
}
