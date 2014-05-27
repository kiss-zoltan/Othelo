package swing;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import adatkapcs.jdbc;
import prtbead.vezerlo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Magát a pályát megjelenítő osztály.
 * 
 * @author Zoli
 *
 */
public class JatekPalya extends JFrame {
	/**
	 * Logolásra használt eszköz.
	 */
	private static Logger logger =(Logger) LoggerFactory.getLogger(jdbc.class);
	/**
	 * Vezérlő ami a megjelenítéshez szolgáltad információt.
	 */
	final private vezerlo vez;
	/**
	 * Label ami az első játékos pontját jeleníti meg.
	 */
	JLabel pont1;
	/**
	 * Label ami a második játékos pontját jeleníti meg.
	 */
	JLabel pont2;
	/**
	 * A Jatekpalya frameje.
	 */
	private PontokTabla frame;
	/**
	 * A JatekPalya elindítása.
	 * 
	 * @param vez egy vezérlő ami már tartalmazza a szükséges információkat
	 */
	public JatekPalya(final vezerlo vez) {
		super();
		this.vez = vez;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btpont = new JButton("Pontok");
		btpont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.info("Pontok kirajzolva.");
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame = new PontokTabla();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		pont1 = new JLabel("2");

		JButton btfelad = new JButton("Uj jatek");
		btfelad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vez.table.uj();
				feltoltPalyaElemek();
				renderPalya();
				pont1.setText(String.valueOf(vez.table.getFekete()));
				pont2.setText(String.valueOf(vez.table.getFeher()));
			}
		});

		pont2 = new JLabel("2");
		String s = vez.getLep() == 1 ? "fekete" : "fehér";
		lbKijon = new JLabel(s);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(pont1)
								.addGap(103)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(btfelad)
												.addComponent(btpont))
								.addGap(71)
								.addComponent(lbKijon)
								.addPreferredGap(ComponentPlacement.RELATED,
										127, Short.MAX_VALUE)
								.addComponent(pont2).addGap(40)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(5)
																.addComponent(
																		btfelad)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						pont2)
																				.addComponent(
																						pont1)
																				.addComponent(
																						btpont)))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(26)
																.addComponent(
																		lbKijon)))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(8, 8, 0, 0));

		feltoltPalyaElemek();
		renderPalya();
	}
	
	/**
	 * A felület fő panelja.
	 */
	private JPanel contentPane;
	/**
	 * Panel amelyen a pálya foglal helyet.
	 */
	private JPanel panel_1;
	/**
	 * A pályát tartalmazó mátrix.
	 */
	Elem[][] palyaElemek = new Elem[8][8];
	/**
	 * A fekete bábú meg jelenítéséhez használt kép.
	 */
	private ImageIcon iconBlack =new ImageIcon( JatekPalya.class.getResource ("/black.png") );
	/**
	 * A fehér bábú meg jelenítéséhez használt kép.
	 */

	
	private ImageIcon iconWhite = new ImageIcon( JatekPalya.class.getResource ("/white.png") );
	/**
	 * Meg jeleníti hogy kijön.
	 */
	private JLabel lbKijon;
	/**
	 * Létre hozza a pályát alkotó gombokat.És feltölti a <code>palyaElemek</code> mátrixot.
	 * 
	 */
	public void feltoltPalyaElemek() {

		int[][] k = vez.table.atad();
		for (int i = 0; i <= 7; i++)
			for (int j = 0; j <= 7; j++) {
				final Elem gomb;
				switch (k[i][j]) {
				case 1:
					gomb = new Elem(iconBlack, i, j);
					break;
				case 2:
					gomb = new Elem(iconWhite, i, j);
					break;

				default:
					gomb = new Elem("", i, j);
					break;
				}

				gomb.setPreferredSize(new Dimension(20, 20));
				gomb.setBackground(Color.WHITE);

				gomb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (vez.lepes(gomb.x, gomb.y,"eredmeny")) {
							feltoltPalyaElemek();
							renderPalya();
							pont1.setText(String.valueOf(vez.table.getFekete()));
							pont2.setText(String.valueOf(vez.table.getFeher()));
							lbKijon.setText(vez.getLep() == 1 ? "fekete"
									: "fehér");
						} else {
							if (!vez.vege()) {
								JOptionPane
										.showMessageDialog(contentPane,
												"nincs lehetséges lépés másik játékos jön");
								lbKijon.setText(vez.getLep() == 1 ? "fekete"
										: "fehér");
							}
						}
					}
				});
				palyaElemek[i][j] = gomb;
				
			}
		logger.info("A pálya létre hozva.");
	}
	/**
	 * Létre hozza a pályát,feltölti a gombokkal. 
	 */
	public void renderPalya() {
		contentPane.remove(panel_1);
		panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(8, 8, 0, 0));
		panel_1.setBackground(Color.WHITE);
		for (int i = 0; i <= 7; i++)
			for (int j = 0; j <= 7; j++) {

				if (palyaElemek[i][j] != null)
					panel_1.add(palyaElemek[i][j]);

			}
		contentPane.add(panel_1, BorderLayout.CENTER);
		logger.info("pálya rajzolva");
		validate();
		repaint();
	}

}
