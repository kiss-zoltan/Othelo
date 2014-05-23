package prtbead;


import static org.junit.Assert.*;

import org.junit.Test;

import prtbead.Palya;

public class PalyaTest {

	@Test
	public void testGetFekete() {
		Palya t = new Palya();
		assertEquals(2, t.getFekete());
	}

	@Test
	public void testGetFeher() {
		Palya t = new Palya();
		assertEquals(2, t.getFeher());
	}

	@Test
	public void testPalya() {
		Palya t = new Palya();
		int tomb[][] = t.atad();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
					assertEquals(1, tomb[i][j]);
				} else {
					if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
						assertEquals(2, tomb[i][j]);
					}else{
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
		Palya t = new Palya();
		assertTrue(t.lephet(1));
		assertTrue(t.lephet(2));
	}

	@Test
	public void testKornyezo() {
		Palya t = new Palya();
		int i=t.kornyezo(5,3, 1);
		assertEquals(i,1);
		i=t.kornyezo(0,0, 1);
		assertEquals(i,0);
	}

	@Test
	public void testAtad() {
		testPalya();
	}



}
