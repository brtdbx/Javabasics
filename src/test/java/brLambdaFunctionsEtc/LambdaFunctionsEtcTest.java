/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brLambdaFunctionsEtc;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
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
public class LambdaFunctionsEtcTest {
    
    public LambdaFunctionsEtcTest() {
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
     * Test of calcSomething method, of class LambdaFunctionsEtc.
     */
    @Test
    public void testLambdaFunctionsEtc01() {
        double startX = 0;
        double endX = 10;
        double steps = 100;
        double stepWidth = (endX - startX) / steps;


        System.out.println("someRekursion " + LambdaFunctionsEtc.someRekursion(100.00));
        System.out.println("recursiveFunction " + LambdaFunctionsEtc.recursiveFunction(x -> x * x, 100.00));

        double x1 = startX;
        double x2, res1, res2;
        
        LambdaFunctionsEtc lamCalculations = new LambdaFunctionsEtc();
        
        for (int i = 0; i < steps; i++) {
            x2 = x1 + stepWidth;
            res1 = LambdaFunctionsEtc.calcSomething(LambdaFunctionsEtc.myFunction, x1, x2);
            res2 = LambdaFunctionsEtc.calcSomething2(LambdaFunctionsEtc.myFunction2, x1, x2);
            System.out.println("x1, x2, res1, res2:" + x1 + ", " + x2 + "," + res1 + ", " + res2 + ", LambdaFunctionsEtc.myFunction(x1) " + LambdaFunctionsEtc.myFunction.applyAsDouble(x1));
            x1 = x2;

        }
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    
}
