package prtbead;
/**
 * 
 * Jáékosokról a szükséges információ.
 * 
 * @author Zoli
 *
 */
public class player {
	/**
	 * Egy játékos neve.
	 */
	private String nev;
	/**
	 * A játékos pontja.
	 */
	private int pont;
	/**
	 * A játékos színe.
	 */
	private int szin;
	/**
	 * Egy játékos létrehozása.
	 * 
	 * @param nev játékos neve
	 * @param pont játékos pontja
	 * @param szin játékos szine
	 */
	public player(String nev, int pont, int szin) {
		super();
		this.nev = nev;
		this.pont = pont;
		this.szin = szin;
	}
	/**
	 * Visza adja a játékos nevét.
	 * 
	 * @return játékos neve
	 */
	public String getNev() {
		return nev;
	}
	/**
	 * Visza adja a játékos pontját.
	 * @return játékos pontja
	 */
	public int getPont() {
		return pont;
	}
	/**
	 * Beálítja a játékos pontját.
	 * @param pont a játékos pontja
	 */
	public void setPont(int pont) {
		this.pont = pont;
	}
	/**
	 * Vissza adja a játékos színét.
	 * @return a játékos színe
	 */
	public int getSzin() {
		return szin;
	}

}
