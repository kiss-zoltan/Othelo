package swing;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Saját gomb osztály.
 * @author Zoli
 *
 */
public class Elem extends JButton {
	/**
	 * A gomb x kodinátája.
	 */
	public int x;
	/**
	 * A gomb y kordinátája.
	 */
	public int y;
	/**
	 * A gomb osztály konstruktora szöveggel a szöveg mindig egy üres String.
	 * 
	 * @param felirat a gomb felírata 
	 * @param x a gomb x kordinátája
	 * @param y a gomb y kordinátája
	 */
	Elem(String felirat, int x, int y){
		super(felirat);
		this.x = x;
		this.y = y;
	}
	/**
	 * A gomb osztály konstruktora képpel.
	 * 
	 * 
	 * @param icon a gombon meg jelenő kép
	 * @param x a gomb x kordinátája
	 * @param y a gomb y kordinátája
	 */
	Elem(ImageIcon icon, int x, int y){
		super(icon);
		this.x = x;
		this.y = y;
	}
}
