package prtbead;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import prtbead.Palya;

public class PalyaTest {
	private Palya t = new Palya();
	@Test
	public void testGetFekete() {
		
		assertEquals(2, t.getFekete());
	}

	@Test
	public void testGetFeher() {
		
		assertEquals(2, t.getFeher());
	}

	@Test
	public void testPalya() {
		
		int tomb[][] = t.atad();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
					assertEquals(1, tomb[i][j]);
				} else {
					if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
						assertEquals(2, tomb[i][j]);
					} else {
						assertEquals(0, tomb[i][j]);
					}
				}
			}
		}
	}

	@Test
	public void testUj() {
		testPalya();
	}

	@Test
	public void testLephet() {
		assertTrue(t.lephet(1));
		assertTrue(t.lephet(2));
	}

	@Test
	public void testKornyezo() {
		int i = t.kornyezo(5, 3, 1);
		assertEquals(i, 1);
		i = t.kornyezo(0, 0, 1);
		assertEquals(i, 0);
		i = t.kornyezo(3, 2, 2);
		assertEquals(1, i);
		i = t.kornyezo(2, 5, 1);
		assertEquals(1, i);
		i = t.kornyezo(6, 3, 2);
		assertEquals(1, i);
		i = t.kornyezo(1, 6, 2);
		assertEquals(1, i);
		i = t.kornyezo(4, 2, 1);
		assertEquals(1, i);
		i = t.kornyezo(5, 2, 2);
		assertEquals(2, i);
		
	}

	@Test
	public void testAtad() {
		testPalya();
	}

}
