package prtbead;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class playerTest {
	private player n, n1;
	

	@Before
	public void bealit() {
		n = new player("hehe", 2, 1);
		
	}

	@Test
	public void testPlayer() {
		testGetNev();
		testGetPont();
		testGetSzin();
	}

	@Test
	public void testGetNev() {
		assertEquals("hehe", n.getNev());
	}

	@Test
	public void testGetPont() {
		assertEquals(2, n.getPont());
	}

	@Test
	public void testSetPont() {
		n.setPont(5);
		assertEquals(5, n.getPont());
	}

	@Test
	public void testGetSzin() {
		assertEquals(1, n.getSzin());
	}

}
