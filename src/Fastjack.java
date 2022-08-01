import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Fastjack {

	public Fastjack() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a connection
	 */
	public Connection connection(String ip, String database, String username, String password) {

		Connection c = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			JOptionPane.showMessageDialog(null, "Driver charged successfully.");
			//c = DriverManager.getConnection("jdbc:mysql://192.168.1.31:3306/"+database, username, password);
			c = DriverManager.getConnection(ip, username, password);
			JOptionPane.showMessageDialog(null, "SUCCESSFUL CONNECTION");
		}catch(SQLException | ClassNotFoundException e){
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
}
