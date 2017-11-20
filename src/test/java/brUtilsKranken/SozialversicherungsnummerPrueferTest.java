package brUtilsKranken;

import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class SozialversicherungsnummerPrueferTest {

	public SozialversicherungsnummerPrueferTest() {
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
	 * Test of pruefeSozialversicherungsnummer method, of class SozialversicherungsnummerPruefer.
	 */
	@Test
	public void testPruefeSozialversicherungsnummerOk01() {
		System.out.println("pruefeSozialversicherungsnummer");
		String nummer = "99050767R002";
		Calendar datum = CalendarUtil.getDate(1967, 7, 5);
		Integer expResult = 0;
		Integer result = SozialversicherungsnummerPruefer.pruefeSozialversicherungsnummer(nummer, datum);
		assertEquals(expResult, result);
	}

	@Test
	public void testPruefeSozialversicherungsnummerOk02() {
		System.out.println("pruefeSozialversicherungsnummer");
		String nummer = "99010190W124";
		Calendar datum = CalendarUtil.getDate(1990, 1, 1);
		Integer expResult = 0;
		Integer result = SozialversicherungsnummerPruefer.pruefeSozialversicherungsnummer(nummer, datum);
		assertEquals(expResult, result);
	}

	@Test
	public void testPruefeSozialversicherungsnummerOk03() {
		System.out.println("pruefeSozialversicherungsnummer");
		String nummer = "99010180W122";
		Calendar datum = CalendarUtil.getDate(1980, 1, 1);
		Integer expResult = 0;
		Integer result = SozialversicherungsnummerPruefer.pruefeSozialversicherungsnummer(nummer, datum);
		assertEquals(expResult, result);
	}

	@Test
	public void testPruefeSozialversicherungsnummerNok01() {
		System.out.println("pruefeSozialversicherungsnummer");
		String nummer = "99010180W125";
		Calendar datum = CalendarUtil.getDate(1980, 1, 1);
		Integer expResult = 3;
		Integer result = SozialversicherungsnummerPruefer.pruefeSozialversicherungsnummer(nummer, datum);
		assertEquals(expResult, result);
	}

	@Test
	public void testPruefeSozialversicherungsnummerNok02() {
		System.out.println("pruefeSozialversicherungsnummer");
		String nummer = "99010280W122"; // sv-nummer enthält nicht das Geburtsdatum an entsprechender Steller als Subststring
		Calendar datum = CalendarUtil.getDate(1980, 1, 1);
		Integer expResult = 2;
		Integer result = SozialversicherungsnummerPruefer.pruefeSozialversicherungsnummer(nummer, datum);
		assertEquals(expResult, result);
	}

	@Test
	public void testPruefeSozialversicherungsnummerNok03() {
		System.out.println("pruefeSozialversicherungsnummer");
		String nummer = null; // fehlende Daten: sv-nummer und/ oder geburtsdatum fehlen
		Calendar datum = CalendarUtil.getDate(1980, 1, 1);
		Integer expResult = 1;
		Integer result = SozialversicherungsnummerPruefer.pruefeSozialversicherungsnummer(null, datum);
		assertEquals(expResult, result);
	}

}
