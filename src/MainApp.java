import java.sql.*;

import javax.swing.JOptionPane;

public class MainApp {

	final static String user = "remote";
	final static String pass = "abcd1234";
	final static String dbase = "actividades";
	final static String ip = "jdbc:mysql://192.168.1.31:3306?useTimezone=true&serverTimezone=UTC";
	final static String dbName = "TA18_DB";

	// Initialize class MySQL to use its methods
	static Fastjack jack = new Fastjack();

	/**
	 * Main method that calls the main menu after charging the driver of MySQL
	 * connection
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// We surround the charge of the driver with an obligatory "Try-catch"
		try {
			// Charge of the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			JOptionPane.showMessageDialog(null, "Driver charged.");

			// If everything fine, we call the menu
			initializeMenu();
		} catch (ClassNotFoundException e) {
			System.out.println("Error charging the Driver for MySQL connection.");
			System.out.println("Message: " + e.getMessage() + "Cause: " + e.getCause());
		}
	}

	/**
	 * Initial menu
	 * @throws ClassNotFoundException 
	 */
	public static void initializeMenu() throws ClassNotFoundException {
		// Before start, we create the database for all the exercises
		createExerciseDatabase();

		// Menu options
		final String options = "Choose the database theme you want to create:" + "\n1 - Tienda informatica"
				+ "\n2 - Empleados" + "\n3 - Almacenes" + "\n4 - Peliculas y salas" + "\n5 - Directores"
				+ "\n6 - Piezas y proveedores" + "\n7 - Cientificos" + "\n8 - Grandes almacenes"
				+ "\n9 - Investigadores" + "\n0 - Exit";
		String option = "";

		// Start menu loop
		do {
			option = JOptionPane.showInputDialog(options);
			switch (option) {
			case "0":
				JOptionPane.showMessageDialog(null, "End of the program.");
				break;
			case "1":
				executeExercise1();
				break;
			case "2":
				//executeExercise2();
				break;
			case "3":
				//executeExercise3();
				break;
			case "4":
				//executeExercise4();
				break;
			case "5":
				//executeExercise5();
				break;
			case "6":
				//executeExercise6();
				break;
			case "7":
				//executeExercise7();
				break;
			case "8":
				//executeExercise8();
				break;
			case "9":
				//executeExercise9();
				break;
			default:
				JOptionPane.showMessageDialog(null, "The value introduced is incorrect.", "VALUE ERROR", 0);
			}

		} while (!option.equals("0"));
	}

	/**
	 * This method creates the database for all the exercises
	 * @throws ClassNotFoundException 
	 */
	public static void createExerciseDatabase() throws ClassNotFoundException {
		// We do the connection
		Connection con = jack.connection(ip, dbase, user, pass);

		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);
	}

	/**
	 * This method executes the first exercise of the task
	 * @throws ClassNotFoundException 
	 */
	public static void executeExercise1() throws ClassNotFoundException {
		// We initialize a String with the name of the database
		

		// We do the connection
		Connection con = jack.connection(ip, dbase, user, pass);

		// We create the tables
		String query = "CREATE TABLE `fabricantes` (`CODIGO` int NOT NULL, `NOMBRE` varchar(255) NOT NULL, PRIMARY KEY (`CODIGO`));";
		String tableName = "fabricantes";
		jack.createTable(con, dbName, tableName, query);

		query = "CREATE TABLE `articulos` (`CODIGO` int NOT NULL,`NOMBRE` varchar(255) NOT NULL,`PRECIO` decimal(10,0) NOT NULL,`FABRICANTE` int NOT NULL, PRIMARY KEY (`CODIGO`),KEY `FABRICANTE` (`FABRICANTE`),CONSTRAINT `articulos_ibfk_1` FOREIGN KEY (`FABRICANTE`) REFERENCES `fabricantes` (`CODIGO`));";
		tableName = "articulos";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "INSERT INTO `articulos` VALUES (1,'Hard drive',240,5),(2,'Memory',120,6),(3,'ZIP drive',150,4),(4,'Floppy disk',5,6),(5,'Monitor',240,1);";
		jack.insertData(con, dbName, tableName, query);

		query = "INSERT INTO `fabricantes` VALUES (1,'Sony'),(2,'Creative Labs'),(3,'Hewlett-Packard'),(4,'Iomega'),(5,'Fujitsu'),(6,'Winchester');";
		tableName = "fabricantes";
		jack.insertData(con, dbName, tableName, query);

		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");
	}
}
