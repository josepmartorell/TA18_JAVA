import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Fastjack {

	public Fastjack() {
		
	}

	/**
	 * Creates a connection
	 */
	public Connection connection(String ip, String username, String password) {
		// We create a connection
		Connection c = null;
		// Surround the connection with a try-catch with the options that can happen
		try {
			c = DriverManager.getConnection(ip, username, password);
			JOptionPane.showMessageDialog(null, "Successfull connection.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "ERROR MESSAGE", 0);
			e.printStackTrace();
		}
		
		// We return the connection
		return c;
	}

	/**
	 * Closes a connection
	 */
	public void closeConnection(Connection c) throws ClassNotFoundException {

		// We close the connection and surround the action with a try-catch
		try {
			c.close();
			JOptionPane.showMessageDialog(null, "Connection closed.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "ERROR MESSAGE", 0);

		}

	}

	/**
	 * Creates a database
	 * 
	 * @param con    Connection
	 * @param dbName Name of the database
	 * @param query  The query to execute
	 * @throws ClassNotFoundException
	 */
	public void createDB(String dbName, Connection con, String query) throws ClassNotFoundException {
		// We surround the actions with a try-catch
		try {
			// We create an statement for every query to execute
			Statement st1 = con.createStatement();
			st1.executeUpdate("drop database if exists " + dbName + ";");

			Statement st2 = con.createStatement();
			st2.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Database created successfully.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error creating the database", "ERROR", 0);
			System.out.println("Error code: " + e.getErrorCode() + "Message: " + e.getMessage() + "SQL State: "
					+ e.getSQLState() + "Cause: " + e.getCause());
		}
	}

	/**
	 * Creates a table
	 * 
	 * @throws ClassNotFoundException
	 */
	public void createTable(Connection con, String dbName, String tableName, String query)
			throws ClassNotFoundException {
		try {

			String queryDB = "use " + dbName + ";";
			Statement stdb = con.createStatement();
			stdb.executeUpdate(queryDB);

			Statement st = con.createStatement();
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Table created successfully.");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error creating table.", "ERROR", 0);
		}
	}

	/**
	 * Inserts register
	 * 
	 * @throws ClassNotFoundException
	 */
	public void insertData(Connection con, String dbName, String tableName, String query)
			throws ClassNotFoundException {
		try {
			String queryDB = "use " + dbName + ";";
			Statement stdb = con.createStatement();
			stdb.executeUpdate(queryDB);

			Statement st = con.createStatement();
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Data inserted correctly.");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error inserting data.", "ERROR", 0);
		}
	}
}
