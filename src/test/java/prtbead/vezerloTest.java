package prtbead;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class vezerloTest {
	private player n, n1;
	private vezerlo t;

	@Before
	public void bealit() {
		n = new player("hehe", 2, 1);
		n1 = new player("hehe", 2, 2);
		t = new vezerlo(n, n1);
	}

	@Test
	public void testGetLep() {

		assertEquals(1, t.getLep());
	}

	@Test
	public void testVezerlo() {

	}

	@Test
	public void testVege() {
		assertFalse(t.vege());
		int[][] te = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				te[i][j] = 1;
			}
		}
		t.table.setPalya(te);

		assertTrue(t.vege());
	}

	@Test
	public void testLepes() {
		
		assertTrue(t.lepes(3, 3));
		assertTrue(t.lepes(3, 2));
		assertTrue(t.lepes(3, 3));
	}

}
