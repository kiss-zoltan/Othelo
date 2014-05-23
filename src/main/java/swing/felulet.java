package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




import javax.swing.UIManager;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import adatkapcs.jdbc;
import prtbead.player;
import prtbead.vezerlo;
/**
 * Az első felület amit látunk az alap adatok megadása és a játék inditása lehetséges.
 * 
 * @author Zoli
 *
 */
public class felulet extends JFrame {
	/**
	 * Loggolásra használt eszköz.
	 */
	private static Logger logger =(Logger) LoggerFactory.getLogger(jdbc.class);
	/**
	 * A frame egy panelja.
	 */
	private JPanel contentPane;
	/**
	 * Az első játékos neve.
	 */
	private JTextField player1Name;
	/**
	 * A második játékos neve.
	 */
	private JTextField player2Name;
	/**
	 * Az első játékos fehér színt beálító radiobuttonja.
	 */
	JRadioButton white1;
	/**
	 * A második játékos fehér színt beálító radiobuttonja.
	 */
	JRadioButton white2;
	/**
	 * Az első játékos fekete színt beálító radiobuttonja.
	 */
	JRadioButton black1;
	/**
	 * A második játékos fekete színt beálító radiobuttonja.
	 */
	JRadioButton black2;

	/**
	 * Elindítja az alkalmazást.
	 * @param args semire sem használt változó
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					felulet frame = new felulet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Ellenőrzi hogy minden adat kivan e töltve.
	 * 
	 * @return true  ha kivan minden mező töltve false egyébként
	 */
	private boolean isValidFields() {

		if ((!black1.isSelected() && !white1.isSelected())
				|| player1Name.getText().length() == 0
				|| player2Name.getText().length() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Létre hozza a framet.
	 */
	public felulet() {
		setTitle("Othello");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		player1Name = new JTextField();
		player1Name.setColumns(10);

		player2Name = new JTextField();
		player2Name.setColumns(10);

		JLabel lblNewLabel = new JLabel("1 játékos");

		JLabel lblNewLabel_1 = new JLabel("2 játékos");

		JButton startButton = new JButton("START GAME");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isValidFields()) {

					int szin1 = black1.isSelected() ? 1 : 2;
					int szin2 = black2.isSelected() ? 1 : 2;

					player p1 = new player(player1Name.getText().trim(), 2,
							szin1);
					player p2 = new player(player2Name.getText().trim(), 2,
							szin2);

					final vezerlo vez1 = new vezerlo(p1, p2);
					logger.info("Játék kezdődik.");
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								JatekPalya frame = new JatekPalya(vez1);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

				}
			}
		});

		black1 = new JRadioButton("black");
		black1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				white1.setSelected(false);
				black2.setSelected(false);
				black1.setSelected(true);
				white2.setSelected(true);
			}
		});

		white1 = new JRadioButton("white");
		white1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				white1.setSelected(true);
				black2.setSelected(true);
				black1.setSelected(false);
				white2.setSelected(false);
			}
		});

		black2 = new JRadioButton("black");
		black2.setEnabled(false);

		white2 = new JRadioButton("white");
		white2.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(player1Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(black1)
								.addComponent(white1))
							.addGap(43)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(white2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addComponent(black2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addComponent(player2Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(79)
							.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(player1Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(player2Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(black1)
						.addComponent(black2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(white1)
						.addComponent(white2))
					.addGap(31)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
