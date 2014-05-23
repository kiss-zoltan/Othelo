package prtbead;

/**
 * A reversi pályája és rajta végre hajtható müveletek.
 * 
 * @author Zoli
 */
public class Palya {
	/**
	 * A pálya mezői.
	 */
	private int palya[][] = new int[8][8];
	/**
	 * A fekete bábúk száma.
	 */
	private int fekete;
	/**
	 * TEsztelés miati fügvény.
	 * @param palya uj palya
	 */
	public void setPalya(int[][] palya) {
		this.palya = palya;
	}
	/**
	 * A fehér bábúk száma.
	 */
	private int feher;

	/**
	 * Viszatér a fekete bábúk számával.
	 * 
	 * @return fekete bábúk száma
	 */
	public int getFekete() {
		return fekete;
	}

	/**
	 * Viszatér a fehér bábúk számával.
	 * 
	 * @return a fehér bábúk száma
	 */
	public int getFeher() {
		return feher;
	}

	/**
	 * Inicializálja a pályát.
	 */
	public Palya() {
		uj();
	}

	/**
	 * Beállitja a kezdő állapotát a tábláknak és a bábúk számát is.
	 */
	public void uj() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				palya[i][j] = 0;
			}
		}
		palya[3][3] = 1;
		palya[4][4] = 1;
		palya[3][4] = 2;
		palya[4][3] = 2;
		fekete = 2;
		feher = 2;
	}

	/**
	 * Ellenörzi hogy a paraméterben kapot lépés érvényes e.Ha igen és tényleges
	 * lépés történt akkor a pályát is módósítja.
	 * 
	 * @param x a lépés x kordinátája 0tól 7ig terjedő értek
	 * @param y a lépés y kordinátája 0tól 7ig terjedő értek
	 * @param incx  x tengejen merre kel tovább haladni az
	 * ellenőrzéssel 
	 * @param incy y tengejen merre kel tovább
	 * haladni az ellenőrzéssel 
	 * @param p az aktuálisan lépő bábújának színe 
	 * @param valami megadja hogy ténylegesen
	 * lépünk vagy csak a lépés érvényeségét nézzük
	 * 
	 * @return viszadja hogy a lépés érvényes e
	 */
	private boolean lep(int x, int y, int incx, int incy, int p, boolean valami) {
		if (palya[x][y] != 0)
			return false;
		int ellenfel;
		if (p == 2)
			ellenfel = 1;
		else
			ellenfel = 2;
		int db = 0;

		x += incx;
		y += incy;

		while ((x < 8) && (x >= 0) && (y < 8) && (y >= 0)
				&& palya[x][y] == ellenfel) {
			x += incx;
			y += incy;
			db++;
		}
		if ((db != 0) && (x < 8) && (x >= 0) && (y < 8) && (y >= 0)
				&& (palya[x][y] == p)) {
			if (valami) {
				for (int j = 1; j <= db; j++) {
					x -= incx;
					y -= incy;
					valt(x, y, p);

				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Megváltoztatja a paraméterben kapot kordináták értékét a kapot
	 * értékre.Továbbá <code>fekete</code>,<code>feher</code> változók értékét
	 * is módosítja,a lépés szinétől függően.
	 * 
	 * @param x a lépés x kordinátája 
	 * @param y a lépés y kordinátája 
	 * @param p a lépés szine
	 */
	private void valt(int x, int y, int p) {

		if (palya[x][y] != 0) {
			if (p == 2) {
				feher++;
				fekete--;
			} else {
				feher--;
				fekete++;
			}
		} else {
			if (p == 1)
				fekete++;
			else
				feher++;
		}
		palya[x][y] = p;
	}
	/**
	 * Ellenerzi hogy a paraméterben kapot színnel van e érvényes lépés a táblán.
	 * @param p az ellenőrizni kivánt bábú színe 
	 * @return viszatér hogy van e érvényes lépés
	 */
	public boolean lephet(int p) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((palya[i][j] == 0) && lephetk(i, j, p)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Ellenörzi hogy a paraméterben kapot kordinátán van e érvényes lépés a <code>s</code>
	 * színnel.Minden irányban ellenőriz.Ez még nem igazi lépés csak egy ellenőrzés.
	 * 
	 * 
	 * @param x  a lépés x kórdinátája
	 * 
	 * @param y a lépés y kórdinátája
	 * 
	 * @param s a lépés színe 
	 * @return viszatér azzal hogy van e érvényes lépés a kordinátán
	 */
	private boolean lephetk(int x, int y, int s) {
		if (lep(x, y, 1, 1, s, false))
			return true;
		if (lep(x, y, 1, 0, s, false))
			return true;
		if (lep(x, y, 1, -1, s, false))
			return true;
		if (lep(x, y, 0, 1, s, false))
			return true;
		if (lep(x, y, 0, -1, s, false))
			return true;
		if (lep(x, y, -1, 1, s, false))
			return true;

		if (lep(x, y, -1, 0, s, false))
			return true;

		if (lep(x, y, -1, -1, s, false))
			return true;

		return false;
	}
	/**
	 * Egy lépés ellenőrzése, ha legalább egy lépés is érvényes volt akkor az x,y kordinátás is be állítja
	 * (ez valódi játékos lépés).
	 * 
	 * @param x a lépés x kórdinátája
	 *  @param y a lépés s kórdinátája
	 *  @param s a lépés színe
	 * @return azoknak az irányoknak a száma amelyekben szín váltás történt
	 */
	public int kornyezo(int x, int y, int s) {
		int fa = 0;
		if (lep(x, y, 1, 1, s, true))
			fa++;
		if (lep(x, y, 1, 0, s, true))
			fa++;
		if (lep(x, y, 1, -1, s, true))
			fa++;
		if (lep(x, y, 0, 1, s, true))
			fa++;
		if (lep(x, y, 0, -1, s, true))
			fa++;
		if (lep(x, y, -1, 1, s, true))
			fa++;
		if (lep(x, y, -1, 0, s, true))
			fa++;
		if (lep(x, y, -1, -1, s, true))
			fa++;
		if (fa != 0) {
			palya[x][y] = s;
			if (s == 1) {
				fekete++;
			} else {
				feher++;
			}
		}
		return fa;

	}
	/**
	 * Átadja a a táblát.
	 * @return a teljes tábla
	 */
	public int[][] atad() {
		return palya;
	}

}
