package brUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        System.out.println("isWholeNumber: " + "-12.000000000000000000");
        BigDecimal bd = new BigDecimal("-12.000000000000000000");
        boolean result = MathUtils.isWholeNumber(bd);
        assertEquals(true, result);
    }

    /**
     * Test of isWholeNumber method, of class MathUtils.
     */
    @Test
    public void testIsWholeNumber02() {
        System.out.println("isWholeNumber: " + "-12.00000000000000000001");
        BigDecimal bd = new BigDecimal("-12.0000000000000000001");
        boolean result = MathUtils.isWholeNumber(bd);
        assertEquals(false, result);
    }

    @Test
    public void someBigDecimal() {

        BigDecimal x1, x2, x1Rounded, x2Rounded, result, resultRounded;
        x1 = new BigDecimal("10.0015");
        x2 = new BigDecimal("20.0014");

        // java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result requires scale here!!
        BigDecimal x6 = new BigDecimal("1.0").divide(new BigDecimal("3.0"), 10, RoundingMode.HALF_EVEN);

        x1Rounded = x1.setScale(3, BigDecimal.ROUND_HALF_UP);
        x2Rounded = x2.setScale(3, BigDecimal.ROUND_HALF_UP);

        Double d = 1e-34;
        BigDecimal x3 = new BigDecimal(d);
        BigDecimal x4 = (new BigDecimal(BigInteger.ONE)).scaleByPowerOfTen(-34);

        System.out.println("x3=" + x3 + "\nx3.unscaledValue()=" + x3.unscaledValue() + "\nx3.scale()=" + x3.scale());
        System.out.println("x4=" + x4 + "\nx4.unscaledValue()=" + x4.unscaledValue() + "\nx4.scale()=" + x4.scale());

        BigDecimal x5 = x4.subtract(x3);
        System.out.println("x5=" + x5.toEngineeringString());

        System.out.println("x1=" + x1);
        System.out.println("x2=" + x2);
        System.out.println("x1Rounded=" + x1Rounded);
        System.out.println("x2Rounded=" + x2Rounded);
        result = x1.add(x2);
        System.out.println("x1+x2 = " + result);
        resultRounded = result.setScale(3, BigDecimal.ROUND_HALF_UP);
        System.out.println("resultRounded=" + resultRounded);

    }

    @Test
    public void someBigDecimal2() {

        boolean erlaubt = false;

        int myscale = 10;
//      BigDecimal eps = BigDecimal.valueOf(1, 10); // i.e 1^(-10)
//

        System.out.println("ugly Divisions");
        BigDecimal bd1 = new BigDecimal("20");
        BigDecimal bd2 = new BigDecimal("3");

        // this is ok, as 10*3 = 30     
        BigDecimal bd3 = bd1.multiply(bd2);

        //but this calclulation throws an arithmetic exception, since 10/3 = 0.333333..., has infinite scale!
        //BigDecimal bd4 = bd1.divide(bd2); 
        // performs the division, so that myscale digits are precisely (including the given roundind mode)
        BigDecimal bd5 = MathUtils.saveDivision(bd1, bd2, myscale, RoundingMode.DOWN);
//        BigDecimal bd5 = saveDivision(bd1, bd2, myscale);
        System.out.println("" + bd5);
        BigDecimal bd6 = MathUtils.saveDivision(bd1, bd2, myscale, RoundingMode.UP);
        System.out.println("" + bd6);
        BigDecimal bd9 = MathUtils.saveDivision(bd1, bd2, myscale, RoundingMode.HALF_UP);
//        BigDecimal bd9 = saveDivision(bd1, bd2);
        System.out.println("" + bd9);
        System.out.println("" + bd9.subtract(bd6));

        System.out.println("isZeroWithRespectToScale // kleiner als Test ...");
        BigDecimal delta1 = bd5.subtract(bd6);
        System.out.println("" + delta1 + " --> isZeroWithRespectToScale(scale:" + myscale + "): " + MathUtils.isZeroWithRespectToScale(delta1, myscale));
        System.out.println("" + delta1 + " --> isZeroWithRespectToScale(scale:" + (myscale - 1) + "): " + MathUtils.isZeroWithRespectToScale(delta1, myscale - 1));

        System.out.println("Test for whole number");
        BigDecimal bd7 = new BigDecimal("-12.000000000000000000");
        System.out.println("" + bd7 + " --> isWholeNumber: " + MathUtils.isWholeNumber(bd7));

        BigDecimal bd8 = new BigDecimal("-12.000000000000000000001");
        System.out.println("" + bd8 + " --> isWholeNumber: " + MathUtils.isWholeNumber(bd8));

    }

    @Test
    public void dioptrienTest() {

        boolean ok;

        ok = dioptrienErlaubt(new BigDecimal("0.2551"));
        ok = dioptrienErlaubt(new BigDecimal("0.25"));
        ok = dioptrienErlaubt(new BigDecimal("0.25000000001"));
        ok = dioptrienErlaubt(new BigDecimal("99.25000000001"));
        ok = dioptrienErlaubt(new BigDecimal("99.2500000000"));
        ok = dioptrienErlaubt(new BigDecimal("7.75"));
        ok = dioptrienErlaubt(new BigDecimal("2.85"));
        ok = dioptrienErlaubt(new BigDecimal("12.25000000001"));
        ok = dioptrienErlaubt(new BigDecimal("13"));
        ok = dioptrienErlaubt(new BigDecimal("0.75"));
        ok = dioptrienErlaubt(new BigDecimal("1.25"));

    }

    private boolean dioptrienErlaubt(BigDecimal d) {
        // Die Funktion prüft, ob die übergebene BigDecimal - Zahl d ein Vielfaches von 0.25 ist, ndem sie prüft, ob 4*d ganzzahlig ist.
        System.out.println("-----------------------------------");
        boolean erlaubt = MathUtils.isWholeNumber((d.multiply(BigDecimal.valueOf(4))));
        System.out.println("d=" + d + "    ---> erlaubt:" + erlaubt);
        
        return erlaubt;
    }
}
