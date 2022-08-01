import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Fastjack {

	public Fastjack() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a connection
	 */
	public Connection connection(String ip, String username, String password) {

		Connection c = null;

		try{
			//c = DriverManager.getConnection("jdbc:mysql://192.168.1.31:3306/"+database, username, password);
			c = DriverManager.getConnection(ip, username, password);
			JOptionPane.showMessageDialog(null, "SUCCESSFUL CONNECTION");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e, "ERROR MESSAGE", 0);
			e.printStackTrace();
		}

		return c;
	}

	/**
	 * Closes a connection
	 */
	public void closeConnection(Connection c) throws ClassNotFoundException {

		try{
			c.close();
			JOptionPane.showMessageDialog(null, "THE CONNECTION WITH THE SERVER HAS BEEN TERMINATED");
		}catch(SQLException e){
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
		try {
			Statement st1 = con.createStatement();
			st1.executeUpdate("drop database if exists "+dbName+";");
			
			Statement st2 = con.createStatement();
			st2.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Database created successfully.");

		} catch (SQLException e) {
			// Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Error code: " + e.getErrorCode() + "Message: " + e.getMessage() + "SQL State: "
					+ e.getSQLState() + "Cause: " + e.getCause());
			System.out.println("Error creating the database");
		}
	}
	
	/**
	 * Creates a table
	 * @throws ClassNotFoundException 
	 */
	public void createTable(Connection con, String dbName, String tableName, String query) throws ClassNotFoundException {
		try {
			
			
			String queryDB = "use " + dbName + ";";
			Statement stdb = con.createStatement();
			stdb.executeUpdate(queryDB);
			
//			String queryDB1 = "drop table if exists " + tableName + ";";
//			Statement stdb1 = con.createStatement();
//			stdb.executeUpdate(queryDB1);

			Statement st = con.createStatement();
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Table created successfully!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error creating table.");
		}
	}
	
	/**
	 * Inserts register
	 * @throws ClassNotFoundException 
	 */
	public void insertData(Connection con, String dbName, String tableName, String query) throws ClassNotFoundException {
		try {
			String queryDB = "use " + dbName + ";";
			Statement stdb = con.createStatement();
			stdb.executeUpdate(queryDB);

			Statement st = con.createStatement();
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Data stored correctly!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error creating table.");
		}
	}
	
}
