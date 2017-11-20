/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brUtilsKranken;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hv63x19
 */
public class SteuerIdPrueferTest {
	
	public SteuerIdPrueferTest() {
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
	 * Test of pruefeSteuerid method, of class SteuerIdPruefer.
	 */
	@Test
	public void testPruefeSteueridOk01() {
		System.out.println("pruefeSteuerid");
		String steuerid = "12345678000";
		Integer expResult = 0;
		Integer result = SteuerIdPruefer.pruefeSteuerid(steuerid);
		assertEquals(expResult, result);
	}

	@Test
	public void testPruefeSteueridOk02() {
		System.out.println("pruefeSteuerid");
		String steuerid = "12345670005";
		Integer expResult = 0;
		Integer result = SteuerIdPruefer.pruefeSteuerid(steuerid);
		assertEquals(expResult, result);
	}

	@Test
	public void testPruefeSteueridNok01() {
		System.out.println("pruefeSteuerid");
		String steuerid = "123456780002";
		Integer expResult = 1; // falsche Länge
		Integer result = SteuerIdPruefer.pruefeSteuerid(steuerid);
		assertEquals(expResult, result);
	}	

	@Test
	public void testPruefeSteueridNok02() {
		System.out.println("pruefeSteuerid");
		String steuerid = "12345671000";
		Integer expResult = 2; // falsche Prüfziffer
		Integer result = SteuerIdPruefer.pruefeSteuerid(steuerid);
		assertEquals(expResult, result);
	}		
	@Test
	public void testPruefeSteueridNok03() {
		System.out.println("pruefeSteuerid");
		String steuerid = "12355670000";
		Integer expResult = 3; // zuviele doppelte oder fehlende Ziffern
		Integer result = SteuerIdPruefer.pruefeSteuerid(steuerid);
		assertEquals(expResult, result);
	}		
	
}
