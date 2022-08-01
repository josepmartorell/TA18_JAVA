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
	public Connection connection(String database, String username, String password) {
		
		try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection c = DriverManager.getConnection("jdbc:mysql://192.168.0.14:3306/"+database, username, password);
	        JOptionPane.showMessageDialog(null, "SUCCESSFUL CONNECTION");
		}catch(SQLException | ClassNotFoundException e){
				JOptionPane.showMessageDialog(null, e, "ERROR MESSAGE", 0);
		            e.printStackTrace();
		}

		return null;
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
