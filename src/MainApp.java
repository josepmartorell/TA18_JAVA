import java.sql.*;

public class MainApp {

	final static String user = "remote";
	final static String pass = "abcd1234";
	final static String dbase = "actividades";
	final static String ip = "jdbc:mysql://192.168.1.31:3306?useTimezone=true&serverTimezone=UTC";
	
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Fastjack jack = new Fastjack();
		
		Connection c = jack.connection(ip, dbase, user, pass);
		
		jack.closeConnection(c);
	}
}
