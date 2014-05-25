package adatkapcs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A játékban használt adatbázis müveleteket tartalmazó osztály.
 * 
 * @author Zoli
 * 
 */
public class jdbc {

	/**
	 * A komunikációra használt kapcsolat.
	 */
	private static Connection con;
	/**
	 * Az adatbázisban lévő tábla neve.
	 */
	private String tabla;

	/**
	 * Létre hoz egy kapcsolatott az adatbázissal.És ellenörzi hogy létezik e a
	 * paraméterben kapot tábla ha nem létre hozza.
	 * 
	 * @param tabla
	 *            tábla név amit használunk.
	 */
	public jdbc(String tabla) {
		try {
			String felhnev = "h_B7V7Y7";
			String jelszo = "kassai";
			this.tabla = tabla;
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g", felhnev,
					jelszo);

			if (!existstable())
				createtable();
		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}

	}

	/**
	 * Feltölti a <code>tabla</code> táblába a paraméterben kapot adatokat.
	 * 
	 * @param nev
	 *            egy játékos név
	 * @param eredmeny
	 *            a játékos eredménye
	 */
	public void feltolt(String nev, int eredmeny) {
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into "
					+ tabla + " values(?,?)");

			pstmt.setString(1, nev);
			pstmt.setDouble(2, eredmeny);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
		} catch (SQLException e) {
			logger.warn(e.getMessage());

		}

	}
	/**
	 * Logolásra használt eszköz.
	 */
	private static Logger logger =(Logger) LoggerFactory.getLogger(jdbc.class);
	/**
	 * Visza adja a <code>tabla</code> ban lévő öszes adatot egy listában. A
	 * lista minden eleme egy sorra/rekodja a táblának.
	 * 
	 * @return a táblában lévő öszes adat
	 */
	public LinkedList<String> kiir() {
		LinkedList<String> lista = new LinkedList<String>();
		try {

			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("select * from " + tabla);
			while (rset.next()) {
				String nev = rset.getString("nev");
				int eredmeny = rset.getInt("eredmeny");
				nev = nev.concat(" " + Integer.toString(eredmeny));
				lista.add(nev);
			}

		} catch (SQLException e) {
			logger.warn(e.getMessage());

		}
		return lista;
	}

	/**
	 * Meg adja hogy létezik e a tábla amiben dolgozni szeretnénk.
	 * 
	 * @return true ha létezik egyébként false
	 * 
	 */
	private boolean existstable() {
		try {

			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery("select * from tab where tname='"
					+ tabla.toUpperCase() + "'");

			return rst.next();

		} catch (SQLException e) {
			logger.warn(e.getMessage());

		}
		return false;
	}

	/**
	 * Létre hozza a táblát amiben dolgozni szertnénk.
	 * 
	 * @throws SQLException
	 */
	private void createtable() {
		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("create table " + tabla
					+ " (nev varchar2(50),eredmeny number)");
			con.commit();
		} catch (SQLException e) {
			logger.warn(e.getMessage());

		}
	}
}
