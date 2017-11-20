package brUtilsKranken;

import java.time.LocalDate;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class CalendarUtilTest {

    public CalendarUtilTest() {
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
     * Berechne einfach mal das Alter
     */
    @Test
    public void berechneAlter01() {

        LocalDate geburtstag = LocalDate.of(1967, 7, 5);
        LocalDate heute = LocalDate.of(2017, 7, 15); // ui!
        int alter = CalendarUtil.berechneAlterZumStichtag(geburtstag, heute);
        System.out.println("geburtstag:" + CalendarUtil.getGermanDateFormat(geburtstag) + "; "
                + "stichtag:" + CalendarUtil.getGermanDateFormat(heute) + " --> alter:" + alter);

        assertTrue(true);
    }

    @Test
    public void berechneAlter02() {

        LocalDate geburtstag = LocalDate.of(1967, 7, 5);
        LocalDate heute = LocalDate.of(2017, 7, 4); // ui!
        int alter = CalendarUtil.berechneAlterZumStichtag(geburtstag, heute);
        System.out.println("geburtstag:" + CalendarUtil.getGermanDateFormat(geburtstag) + "; "
                + "stichtag:" + CalendarUtil.getGermanDateFormat(heute) + " --> alter:" + alter);

        geburtstag = LocalDate.of(1967, 7, 5);
        heute = LocalDate.of(2017, 7, 5); // ui!
        alter = CalendarUtil.berechneAlterZumStichtag(geburtstag, heute);
        System.out.println("geburtstag:" + CalendarUtil.getGermanDateFormat(geburtstag) + "; "
                + "stichtag:" + CalendarUtil.getGermanDateFormat(heute) + " --> alter:" + alter);

        geburtstag = LocalDate.of(1967, 7, 5);
        heute = LocalDate.of(2017, 7, 6); // ui!
        alter = CalendarUtil.berechneAlterZumStichtag(geburtstag, heute);
        System.out.println("geburtstag:" + CalendarUtil.getGermanDateFormat(geburtstag) + "; "
                + "stichtag:" + CalendarUtil.getGermanDateFormat(heute) + " --> alter:" + alter);

        assertTrue(true);
    }

    
    @Test
    public void berechneAlter03() {

        LocalDate geburtstag = LocalDate.of(2017, 11, 4);
        LocalDate heute = LocalDate.of(2017, 11, 4); // ui!
        int alter = CalendarUtil.berechneAlterZumStichtag(geburtstag, heute);
        System.out.println("geburtstag:" + CalendarUtil.getGermanDateFormat(geburtstag) + "; "
                + "stichtag:" + CalendarUtil.getGermanDateFormat(heute) + " --> alter:" + alter);

        geburtstag = LocalDate.of(2017, 11, 4);
        heute = LocalDate.of(2018, 11, 4); // ui!
        alter = CalendarUtil.berechneAlterZumStichtag(geburtstag, heute);
        System.out.println("geburtstag:" + CalendarUtil.getGermanDateFormat(geburtstag) + "; "
                + "stichtag:" + CalendarUtil.getGermanDateFormat(heute) + " --> alter:" + alter);

        geburtstag = LocalDate.of(2017, 11, 4);
        heute = LocalDate.of(2017, 11, 3); // ui!
        alter = CalendarUtil.berechneAlterZumStichtag(geburtstag, heute);
        System.out.println("geburtstag:" + CalendarUtil.getGermanDateFormat(geburtstag) + "; "
                + "stichtag:" + CalendarUtil.getGermanDateFormat(heute) + " --> alter:" + alter);        

        assertTrue(true);
    }    
    
    
    /**
     * Test of berechneAlterZumStichtag method, of class CalendarUtil.
     */
    @Test
    public void testBerechneAlterZumStichtag_LocalDate_LocalDate_Schaltjahr01() {

        LocalDate geburtstag = LocalDate.of(2000, 2, 29); // geboren am 29.02.2000 - ein Schaltjahr!
        LocalDate stichtag = LocalDate.of(2001, 2, 28); // in DE hat man lat BGB Geburstag, wenn der Tag vor dem Jahrestag zuende ist!
        int expResult = 0;
        int result = CalendarUtil.berechneAlterZumStichtag(geburtstag, stichtag);

        System.out.println("geburtstag:" + CalendarUtil.getGermanDateFormat(geburtstag) + "; "
                + "stichtag:" + CalendarUtil.getGermanDateFormat(stichtag) + " --> alter:" + result);

        assertEquals(expResult, result);
    }

    @Test
    public void testBerechneAlterZumStichtag_LocalDate_LocalDate_Schaltjahr02() {

        LocalDate geburtstag = LocalDate.of(2000, 2, 29);   // geboren am 29.02.2000 - ein Schaltjahr!
        LocalDate stichtag = LocalDate.of(2001, 3, 1);      // nun ist sie ein jahr alt
        int expResult = 1;
        int result = CalendarUtil.berechneAlterZumStichtag(geburtstag, stichtag);
        System.out.println("geburtstag:" + CalendarUtil.getGermanDateFormat(geburtstag) + "; "
                + "stichtag:" + CalendarUtil.getGermanDateFormat(stichtag) + " --> alter:" + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentDateWithMinTime method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetCurrentDateWithMinTime() {
        System.out.println("getCurrentDateWithMinTime");
        Calendar expResult = null;
        Calendar result = CalendarUtil.getCurrentDateWithMinTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        int year = 0;
        int month = 0;
        int day = 0;
        Calendar expResult = null;
        Calendar result = CalendarUtil.getDate(year, month, day);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        int hour = 0;
        int minute = 0;
        int sec = 0;
        Calendar expResult = null;
        Calendar result = CalendarUtil.getTime(hour, minute, sec);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeToMaximum method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testSetTimeToMaximum() {
        System.out.println("setTimeToMaximum");
        Calendar calendar = null;
        Calendar expResult = null;
        Calendar result = CalendarUtil.setTimeToMaximum(calendar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeToMinimum method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testSetTimeToMinimum() {
        System.out.println("setTimeToMinimum");
        Calendar calendar = null;
        Calendar expResult = null;
        Calendar result = CalendarUtil.setTimeToMinimum(calendar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToMaximum method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testSetToMaximum() {
        System.out.println("setToMaximum");
        int field = 0;
        Calendar calendar = null;
        Calendar expResult = null;
        Calendar result = CalendarUtil.setToMaximum(field, calendar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToMinimum method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testSetToMinimum() {
        System.out.println("setToMinimum");
        int field = 0;
        Calendar calendar = null;
        Calendar expResult = null;
        Calendar result = CalendarUtil.setToMinimum(field, calendar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMaximum method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testIsMaximum() {
        System.out.println("isMaximum");
        int field = 0;
        Calendar calendar = null;
        boolean expResult = false;
        boolean result = CalendarUtil.isMaximum(field, calendar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMinimum method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testIsMinimum() {
        System.out.println("isMinimum");
        int field = 0;
        Calendar calendar = null;
        boolean expResult = false;
        boolean result = CalendarUtil.isMinimum(field, calendar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTimeMaximum method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testIsTimeMaximum() {
        System.out.println("isTimeMaximum");
        Calendar calendar = null;
        boolean expResult = false;
        boolean result = CalendarUtil.isTimeMaximum(calendar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTimeMinimum method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testIsTimeMinimum() {
        System.out.println("isTimeMinimum");
        Calendar calendar = null;
        boolean expResult = false;
        boolean result = CalendarUtil.isTimeMinimum(calendar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of errechneVergangeneTage method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testErrechneVergangeneTage() {
        System.out.println("errechneVergangeneTage");
        Calendar cal = null;
        int expResult = 0;
        int result = CalendarUtil.errechneVergangeneTage(cal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateAge method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testCalculateAge() {
        System.out.println("calculateAge");
        Calendar birthdate = null;
        Calendar otherDate = null;
        int expResult = 0;
        int result = CalendarUtil.calculateAge(birthdate, otherDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormattedTimeStamp method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetFormattedTimeStamp() {
        System.out.println("getFormattedTimeStamp");
        Calendar cal = null;
        String expResult = "";
        String result = CalendarUtil.getFormattedTimeStamp(cal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimestampWithoutMilliseconds method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetTimestampWithoutMilliseconds() {
        System.out.println("getTimestampWithoutMilliseconds");
        Calendar cal = null;
        String expResult = "";
        String result = CalendarUtil.getTimestampWithoutMilliseconds(cal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeStamp method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetTimeStamp() {
        System.out.println("getTimeStamp");
        String expResult = "";
        String result = CalendarUtil.getTimeStamp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUhrzeit method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetUhrzeit_0args() {
        System.out.println("getUhrzeit");
        String expResult = "";
        String result = CalendarUtil.getUhrzeit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUhrzeit method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetUhrzeit_Calendar() {
        System.out.println("getUhrzeit");
        Calendar cal = null;
        String expResult = "";
        String result = CalendarUtil.getUhrzeit(cal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultFormattetString method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetDefaultFormattetString() {
        System.out.println("getDefaultFormattetString");
        Calendar date = null;
        int format = 0;
        String expResult = "";
        String result = CalendarUtil.getDefaultFormattetString(date, format);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormattetString method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetFormattetString() {
        System.out.println("getFormattetString");
        Calendar date = null;
        int format = 0;
        String expResult = "";
        String result = CalendarUtil.getFormattetString(date, format);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormattetStringMitMonatLang method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetFormattetStringMitMonatLang() {
        System.out.println("getFormattetStringMitMonatLang");
        Calendar date = null;
        String expResult = "";
        String result = CalendarUtil.getFormattetStringMitMonatLang(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormattetStringOhneTrennzeichen method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetFormattetStringOhneTrennzeichen() {
        System.out.println("getFormattetStringOhneTrennzeichen");
        Calendar date = null;
        int format = 0;
        String expResult = "";
        String result = CalendarUtil.getFormattetStringOhneTrennzeichen(date, format);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isGregorianLeapYear method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testIsGregorianLeapYear() {
        System.out.println("isGregorianLeapYear");
        Calendar cal = null;
        boolean expResult = false;
        boolean result = CalendarUtil.isGregorianLeapYear(cal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormattedIntegerJJJJMMTT method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testGetFormattedIntegerJJJJMMTT() {
        System.out.println("getFormattedIntegerJJJJMMTT");
        Calendar cal = null;
        Integer expResult = null;
        Integer result = CalendarUtil.getFormattedIntegerJJJJMMTT(cal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of berechneMonatsDifferenz method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testBerechneMonatsDifferenz() {
        System.out.println("berechneMonatsDifferenz");
        Calendar aelteresDatum = null;
        Calendar juengeresDatum = null;
        int monate = 0;
        boolean expResult = false;
        boolean result = CalendarUtil.berechneMonatsDifferenz(aelteresDatum, juengeresDatum, monate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of berechneTagesDifferenz method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testBerechneTagesDifferenz() {
        System.out.println("berechneTagesDifferenz");
        Calendar aelteresDatum = null;
        Calendar juengeresDatum = null;
        int tage = 0;
        boolean expResult = false;
        boolean result = CalendarUtil.berechneTagesDifferenz(aelteresDatum, juengeresDatum, tage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDateEqual method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testIsDateEqual() {
        System.out.println("isDateEqual");
        Calendar cal1 = null;
        Calendar cal2 = null;
        boolean expResult = false;
        boolean result = CalendarUtil.isDateEqual(cal1, cal2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFieldEqual method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testIsFieldEqual() {
        System.out.println("isFieldEqual");
        Calendar cal1 = null;
        Calendar cal2 = null;
        int field = 0;
        boolean expResult = false;
        boolean result = CalendarUtil.isFieldEqual(cal1, cal2, field);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDateAfter method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testIsDateAfter() {
        System.out.println("isDateAfter");
        Calendar cal1 = null;
        Calendar cal2 = null;
        boolean expResult = false;
        boolean result = CalendarUtil.isDateAfter(cal1, cal2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFieldAfter method, of class CalendarUtil.
     */
    @Ignore("not ready yet")
    @Test
    public void testIsFieldAfter() {
        System.out.println("isFieldAfter");
        Calendar cal1 = null;
        Calendar cal2 = null;
        int field = 0;
        boolean expResult = false;
        boolean result = CalendarUtil.isFieldAfter(cal1, cal2, field);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
