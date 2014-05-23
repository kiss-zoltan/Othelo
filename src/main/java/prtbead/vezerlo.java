package prtbead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import adatkapcs.jdbc;

/**
 * Irányítja a játék menetét.
 * 
 * @author Zoli
 */
public class vezerlo {
	/**
	 * Egy pálya amit kivülről is el lehet érni.
	 */
	public Palya table;
	/**
	 * A játékosokat tartalmazó tömb.
	 */
	private player[] pl = new player[2];
	/**
	 * Meg mondja hogy melyik játékos következik.
	 */
	private int lep;
	/**
	 * A Palya osztály palya változójának egy pilanatnyi értékét tárolja.
	 */
	private int[][] t;
	/**
	 * Logolásra használt eszköz.
	 */

	private static Logger logger = (Logger) LoggerFactory.getLogger(jdbc.class);

	/**
	 * Vissza adja hogy melyik játékos jön.
	 * 
	 * @return 1 ha fekete 2 ha fehér,
	 */
	public int getLep() {
		return lep;
	}

	/**
	 * A vezérlő példányósítása,a pálya inicializálása és beálltítja hogy ki jön
	 * a <code>lep</code> segítségével.
	 * 
	 * @param p1
	 *            első játékos
	 * @param p2
	 *            második játékos
	 */
	public vezerlo(player p1, player p2) {
		this.table = new Palya();
		this.pl[0] = p1;
		this.pl[1] = p2;
		table.uj();
		lep = pl[0].getSzin();
	}

	/**
	 * Ellenörzi hogy vége van a játéknak az az nincs több üres hely.
	 * 
	 * @return true ha vége egyébként false
	 */
	public boolean vege() {
		t = table.atad();
		boolean v = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (t[i][j] == 0) {
					v = false;

				}
			}
		}
		if (v)
			logger.info("Vége a játéknak");
		else {
			logger.info("Nincs vége a játéknak.");
		}

		return v;
	}

	/**
	 * Egy lépésnél szükséges ellenörzéseket hajtja végre:létezik érvényes
	 * lépés,az aktuális lépés jár e színváltással vége e a játéknak.
	 * 
	 * @param x
	 *            lépés x kordinátája
	 * @param y
	 *            lépés y kordinátája
	 * @return true ha van érvényes lépése annak a színek amelyik következik
	 */
	public boolean lepes(int x, int y) {
		if (!table.lephet(lep)) {
			lep = lep == 1 ? 2 : 1;
			logger.info("Aktuális játékos nem léphetett a másik lép.");
			return false;
		}
		if (table.kornyezo(x, y, lep) != 0) {
			lep = lep == 1 ? 2 : 1;
			if (vege()) {
				pl[0].setPont(pl[0].getSzin() == 1 ? table.getFekete() : table
						.getFeher());
				pl[1].setPont(pl[1].getSzin() == 1 ? table.getFekete() : table
						.getFeher());
				jdbc k = new jdbc("eredmeny");
				k.feltolt(pl[0].getNev(), pl[0].getPont());
				k.feltolt(pl[1].getNev(), pl[1].getPont());
				logger.info("Vege a játéknak.");
			}
		}
		logger.info("Érvényes lépés történt.");
		return true;
	}

}
