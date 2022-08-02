import java.sql.*;

import javax.swing.JOptionPane;

public class MainApp {

	final static String user = "remote";
	final static String pass = "PASSWORD";
	final static String ip = "jdbc:mysql://192.168.1.129:3306?useTimezone=true&serverTimezone=UTC";
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
				executeExercise2();
				break;
			case "3":
				executeExercise3();
				break;
			case "4":
				executeExercise4();
				break;
			case "5":
				executeExercise5();
				break;
			case "6":
				executeExercise6();
				break;
			case "7":
				executeExercise7();
				break;
			case "8":
				executeExercise8();
				break;
			case "9":
				executeExercise9();
				break;
			default:
				JOptionPane.showMessageDialog(null, "The value introduced is incorrect.", "VALUE ERROR", 0);
			}

		} while (!option.equals("0"));
	}

	/**
	 * This method executes the first exercise of the task
	 * @throws ClassNotFoundException 
	 */
	public static void executeExercise1() throws ClassNotFoundException {

		// We do the connection
		Connection con = jack.connection(ip, user, pass);
		
		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);

		// We create the tables
		query = "CREATE TABLE `fabricantes` (`CODIGO` int NOT NULL, `NOMBRE` varchar(255) NOT NULL, PRIMARY KEY (`CODIGO`));";
		String tableName = "fabricantes";
		jack.createTable(con, dbName, tableName, query);

		query = "CREATE TABLE `articulos` (`CODIGO` int NOT NULL,`NOMBRE` varchar(255) NOT NULL,`PRECIO` decimal(10,0) NOT NULL,`FABRICANTE` int NOT NULL, PRIMARY KEY (`CODIGO`),KEY `FABRICANTE` (`FABRICANTE`),CONSTRAINT `articulos_ibfk_1` FOREIGN KEY (`FABRICANTE`) REFERENCES `fabricantes` (`CODIGO`));";
		tableName = "articulos";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "INSERT INTO `fabricantes` VALUES (1,'Sony'),(2,'Creative Labs'),(3,'Hewlett-Packard'),(4,'Iomega'),(5,'Fujitsu'),(6,'Winchester');";
		tableName = "fabricantes";
		jack.insertData(con, dbName, tableName, query);
		
		query = "INSERT INTO `articulos` VALUES (1,'Hard drive',240,5),(2,'Memory',120,6),(3,'ZIP drive',150,4),(4,'Floppy disk',5,6),(5,'Monitor',240,1);";
		tableName = "articulos";
		jack.insertData(con, dbName, tableName, query);

		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");
	}
	
	/**
	 * This method executes the second exercise of the task
	 */
	public static void executeExercise2() throws ClassNotFoundException{

		// We do the connection
		Connection con = jack.connection(ip, user, pass);
		
		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);

		// We create the tables
		query = "create table Departamentos (codigo int, nombre nvarchar(100), presupuesto int, primary key (codigo));";
		String tableName = "Departamentos";
		jack.createTable(con, dbName, tableName, query);

		query = "create table Empleados (dni int, nombre nvarchar(100), apellidos nvarchar(255), departamento int, primary key (dni), foreign key (departamento) references Departamentos (codigo));";
		tableName = "Empleados";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "insert into Departamentos (codigo, nombre, presupuesto) values (1, 'depart1', 50000), (2, 'depart2', 60000), (3, 'depart3', 80000);";
		tableName = "Departamentos";
		jack.insertData(con, dbName, tableName, query);

		query = "insert into Empleados (dni, nombre, apellidos, departamento) values (1, 'emple1', 'apel1', 1), (2, 'emple2', 'apel2', 1), (3, 'emple3', 'apel3', 2);";
		tableName = "Empleados";
		jack.insertData(con, dbName, tableName, query);

		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");
	}
	
	/**
	 * This method executes the third exercise of the task
	 * @throws ClassNotFoundException 
	 */
	public static void executeExercise3() throws ClassNotFoundException {
		// We do the connection
		Connection con = jack.connection(ip, user, pass);

		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);
		
		// We create the tables
		query = "create table Almacenes (codigo int, lugar nvarchar(255), capacidad int, primary key (codigo));";
		String tableName = "Almacenes";
		jack.createTable(con, dbName, tableName, query);

		query = "create table Cajas (numReferencia char(5), contenido nvarchar(100), valor double, almacen int, primary key (numReferencia), foreign key (almacen) references Almacenes (codigo));";
		tableName = "Cajas";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "insert into Almacenes (codigo, lugar, capacidad) values (1,'Valencia',3),(2,'Barcelona',4), (3,'Bilbao',7), (4,'Los Angeles',2), (5,'San Francisco',8)";
		tableName = "Almacenes";
		jack.insertData(con, dbName, tableName, query);

		query = "insert into Cajas (numReferencia, contenido, valor, almacen) values ('0MN7','Rocks',180,3),\r\n"
				+ "						   	('4H8P','Rocks',250,1),\r\n"
				+ "                           ('4RT3','Scissors',190,4),\r\n"
				+ "                           ('7G3H','Rocks',200,1),\r\n"
				+ "                           ('8JN6','Papers',75,1),\r\n"
				+ "                           ('8Y6U','Papers',50,3),\r\n"
				+ "                           ('9J6F','Papers',175,2),\r\n"
				+ "                           ('LL08','Rocks',140,4),\r\n"
				+ "                           ('P0H6','Scissors',125,1),\r\n"
				+ "                           ('P2T6','Scissors',150,2),\r\n"
				+ "                           ('TU55','Papers',90,5)";
		tableName = "Cajas";
		jack.insertData(con, dbName, tableName, query);

		
		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");

	}

	/**
	 * This method executes the fourth exercise of the task
	 * @throws ClassNotFoundException 
	 */
	public static void executeExercise4() throws ClassNotFoundException {
		// We do the connection
		Connection con = jack.connection(ip, user, pass);

		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);
		
		// We create the tables
		query = "create table Peliculas (codigo int, nombre nvarchar(100), calificacionEdad varchar(100), primary key (codigo));";
		String tableName = "Peliculas";
		jack.createTable(con, dbName, tableName, query);

		query = "create table Salas (codigo int, nombre nvarchar(100), pelicula int default null, primary key (codigo), foreign key (pelicula) references Peliculas (codigo));";
		tableName = "Salas";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "insert into Peliculas (codigo, nombre, calificacionEdad) values (1,'Citizen Kane','PG'),\r\n"
				+ "							   (2,'Singin\\' in the Rain','G'),\r\n"
				+ "                               (3,'The Wizard of Oz','G'),\r\n"
				+ "                               (4,'The Quiet Man',NULL),\r\n"
				+ "                               (5,'North by Northwest',NULL),\r\n"
				+ "                               (6,'The Last Tango in Paris','NC-17'),\r\n"
				+ "							   	  (7,'Some Like it Hot','PG-13'),\r\n"
				+ "                               (8,'A Night at the Opera',NULL),\r\n"
				+ "                               (9,'Citizen King','G')";
		tableName = "Peliculas";
		jack.insertData(con, dbName, tableName, query);

		query = "insert into Salas (codigo, nombre, pelicula) values (1,'Odeon',5),\r\n"
				+ "						   (2,'Imperial',1),\r\n"
				+ "                           (3,'Majestic',NULL),\r\n"
				+ "                           (4,'Royale',6),\r\n"
				+ "                           (5,'Paraiso',3),\r\n"
				+ "                           (6,'Nickelodeon',NULL)";
		tableName = "Salas";
		jack.insertData(con, dbName, tableName, query);
		
		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");

	}
	
	/**
	 * This method executes the fifth exercise of the task
	 * @throws ClassNotFoundException 
	 */
	public static void executeExercise5() throws ClassNotFoundException {
		// We do the connection
		Connection con = jack.connection(ip, user, pass);

		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);

		// We create the tables
		query = "create table Despachos (Numero int auto_increment, Capacidad int, CONSTRAINT PRIMARY KEY (Numero));";
		String tableName = "Despachos";
		jack.createTable(con, dbName, tableName, query);

		query = "create table Directores (DNI varchar(9), NomApels nvarchar(255), DNIJefe nvarchar(255) references Directores(DNI), Despacho int, CONSTRAINT PRIMARY KEY (DNI), CONSTRAINT FOREIGN KEY (Despacho) references Despachos(Numero) ON DELETE CASCADE ON UPDATE CASCADE);";
		tableName = "Directores";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "insert into Despachos (Capacidad) values (1), (2), (1), (2), (3);";
		tableName = "Despachos";
		jack.insertData(con, dbName, tableName, query);

		query = "insert into Directores (DNI, NomApels, DNIJefe, Despacho) values ('11111111A', 'Albertito Sarampito', '', 1), ('22222222B', 'Maria Tehachece', '', 3), ('33333333C', 'Casimiro Vistalarga', '11111111A', 5), ('44444444D', 'Manolito Jodidito', '11111111A', 5), ('55555555E', 'Pollito Magico', '11111111A', 5);";
		tableName = "Directores";
		jack.insertData(con, dbName, tableName, query);

		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");

	}
	
	/**
	 * This method executes the sixth exercise of the task
	 */
	public static void executeExercise6() throws ClassNotFoundException{

		// We do the connection
		Connection con = jack.connection(ip, user, pass);
		
		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);

		// We create the tables
		query = "create table piezas (Codigo int auto_increment, Nombre nvarchar(100) not null, constraint pk_piezas primary key (codigo));";
		String tableName = "piezas";
		jack.createTable(con, dbName, tableName, query);

		query = "CREATE TABLE proveedores ( Id char(4), Nombre nvarchar(100) not null, constraint pk_proveedores  primary key (id));";
		tableName = "proveedores";
		jack.createTable(con, dbName, tableName, query);
		
		query = "CREATE TABLE suministra ( CodigoPieza int, IdProveedor char(4), Precio int, constraint foreign key (CodigoPieza) REFERENCES piezas (Codigo) on delete cascade on update cascade, constraint foreign key (IdProveedor) REFERENCES proveedores (Id) on delete cascade on update cascade, constraint primary key(codigoPieza, idProveedor));";
		tableName = "suministra";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "insert piezas (Nombre) values ('Pieza 1'), ('Pieza 2'), ('Pieza 3'), ('Pieza 4'), ('Pieza 5'), ('Pieza 6'), ('Pieza 7'), ('Pieza 8'), ('Pieza 9'), ('Pieza 10');";
		tableName = "piezas";
		jack.insertData(con, dbName, tableName, query);

		query = "insert proveedores (Id, Nombre) values ('AB01', 'Proveedor 1'), ('AB02', 'Proveedor 2'), ('AB03', 'Proveedor 3'), ('AB04', 'Proveedor 4'), ('AB05', 'Proveedor 5'), ('AB06', 'Proveedor 6'), ('AB07', 'Proveedor 7'), ('AB08', 'Proveedor 8'), ('AB09', 'Proveedor 9'), ('AB10', 'Proveedor 10');";
		tableName = "proveedores";
		jack.insertData(con, dbName, tableName, query);
		
		query = "insert suministra (CodigoPieza, IdProveedor, Precio) values (1, 'AB01', 10), (2, 'AB01', 12), (1, 'AB02', 20), (3, 'AB05', 8), (4, 'AB08', 10), (6, 'AB09', 7), (7, 'AB07', 17), (9, 'AB10', 12), (5, 'AB03', 20), (10, 'AB04', 10);";
		tableName = "suministra";
		jack.insertData(con, dbName, tableName, query);

		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");
	}
	
	/**
	 * This method executes the seventh exercise of the task
	 * @throws ClassNotFoundException 
	 */
	public static void executeExercise7() throws ClassNotFoundException {
		// We do the connection
		Connection con = jack.connection(ip, user, pass);

		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);

		// We create the tables
		query = "create table Cientificos (dni varchar(8), nomApels nvarchar(255) not null, CONSTRAINT PRIMARY KEY (DNI));";
		String tableName = "Cientificos";
		jack.createTable(con, dbName, tableName, query);

		query = "create table Proyectos (id char(4), nombre nvarchar(255), horas int, CONSTRAINT PRIMARY KEY (id));";
		tableName = "Proyectos";
		jack.createTable(con, dbName, tableName, query);
		
		query = "create table Asignados_a (id int auto_increment, cientifico	varchar(8), proyecto char(4), CONSTRAINT FOREIGN KEY (cientifico) references Cientificos(dni) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY (proyecto) references Proyectos(id) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT PRIMARY KEY (id));";
		tableName = "Asignados_a";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "INSERT INTO Cientificos(dni, nomApels) VALUES (11111111, 'Pepito Grillo'),(22222222, 'Grillo Pepito'),(33333333, 'Pepo Saltamontes'),(44444444, 'Noseme Ocurre'),(55555555, 'Aitor Menta');";
		tableName = "Cientificos";
		jack.insertData(con, dbName, tableName, query);

		query = "INSERT INTO Proyectos(id, nombre, horas) VALUES (1, 'Vida Infinita', 30000),(2, 'Project X', 4),(3, 'Proyecto alpha', 6),(4, 'Proyecto gamma', 800),(5, 'Proyecto beta', 500);";
		tableName = "Proyectos";
		jack.insertData(con, dbName, tableName, query);
		
		query = "INSERT INTO Asignados_a(cientifico, proyecto) VALUES (11111111, 1),(22222222, 2),(33333333, 3),(44444444, 4),(55555555, 5);";
		tableName = "Asignados_a";
		jack.insertData(con, dbName, tableName, query);

		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");

	}
	
	/**
	 * This method executes the eighth exercise of the task
	 * @throws ClassNotFoundException 
	 */
	public static void executeExercise8() throws ClassNotFoundException {
		// We do the connection
		Connection con = jack.connection(ip, user, pass);

		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);
		
		// We create the tables
		query = "CREATE TABLE `cajeros` (`CODIGO` int NOT NULL, `NOMAPELS` nvarchar(255) NOT NULL, PRIMARY KEY (`CODIGO`));";
		String tableName = "cajeros";
		jack.createTable(con, dbName, tableName, query);

		query = "CREATE TABLE `maquinas_registradoras` (`CODIGO` int NOT NULL,`PISO` int NOT NULL, PRIMARY KEY (`CODIGO`));";
		tableName = "maquinas_registradoras";
		jack.createTable(con, dbName, tableName, query);
		
		query = "CREATE TABLE `productos` (`CODIGO` int NOT NULL,`NOMBRE` NVARCHAR(100) NOT NULL, `PRECIO` double NOT NULL, PRIMARY KEY (`CODIGO`));";
		tableName = "maquinas_registradoras";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "INSERT INTO `cajeros` VALUES (1, 'Sergio Mendez Coballos'),\r\n"
				+ "							  (2, 'Juan Cañiz Garrín'),\r\n"
				+ "                           (3, 'Gina Ortiz Arnan'),\r\n"
				+ "                           (4, 'Marta Valiente Santon'),\r\n"
				+ "                           (5, 'Ana Cabello Cortado'),\r\n"
				+ "							  (6, 'Manuel Gutierrez Postín'),\r\n"
				+ "                           (7, 'Fabián Franco Puigdemon');";
		tableName = "cajeros";
		jack.insertData(con, dbName, tableName, query);
		
		query = "INSERT INTO `maquinas_registradoras` VALUES (1, 1),\r\n"
				+ "											 (2, 1),\r\n"
				+ "											 (3, 2),\r\n"
				+ "											 (4, 3),\r\n"
				+ "											 (5, 4);";
		tableName = "maquinas_registradoras";
		jack.insertData(con, dbName, tableName, query);	
		
		query = "INSERT INTO `productos` VALUES (1, 'Bañera', 124.99),\r\n"
				+ "							   (2, 'bufanda', 11.99),\r\n"
				+ "							   (3, 'chubasquero', 100.69),\r\n"
				+ "							   (4, 'armilla', 599.99),\r\n"
				+ "							   (5, 'lavadora', 789.99),\r\n"
				+ "							   (6, 'almohada', 35.99),\r\n"
				+ "							   (7, 'crema arrugas', 189.99), \r\n"
				+ "							   (8, 'chubasquero', 100.69),\r\n"
				+ "							   (9, 'freidora', 599.99),\r\n"
				+ "							   (10, 'jamón serrano', 789.99),\r\n"
				+ "							   (11, 'leghuga', 35.99),\r\n"
				+ "							   (12, 'berenjena', 9.99);";
		tableName = "productos";
		jack.insertData(con, dbName, tableName, query);	
		
		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");

	}
	
	/**
	 * This method executes the nineth exercise of the task
	 */
	public static void executeExercise9() throws ClassNotFoundException{

		// We do the connection
		Connection con = jack.connection(ip, user, pass);
		
		// We initialize the query to create the database
		String query = "CREATE DATABASE  IF NOT EXISTS `TA18_DB`;";
		jack.createDB(dbName, con, query);

		// We create the tables
		query = "create table piezas (Codigo int auto_increment, Nombre nvarchar(100) not null, constraint pk_piezas primary key (codigo));";
		String tableName = "piezas";
		jack.createTable(con, dbName, tableName, query);

		query = "create table facultad (Codigo int auto_increment,  Nombre nvarchar(100) not null, constraint primary key (Codigo));";
		tableName = "facultad";
		jack.createTable(con, dbName, tableName, query);
		
		query = "create table investigadores ( 	DNI varchar(8), NomApels nvarchar(255), Facultad int, constraint primary key (DNI), constraint foreign key (Facultad) references facultad (Codigo) );";
		tableName = "investigadores";
		jack.createTable(con, dbName, tableName, query);
		
		query = "create table equipos ( NumSerie char(4), Nombre nvarchar(100), Facultad int, constraint primary key (NumSerie), constraint foreign key (Facultad) references facultad (Codigo) );";
		tableName = "equipos";
		jack.createTable(con, dbName, tableName, query);
		
		query = "create table reserva ( DNI varchar(8), NumSerie char(4), Comienzo datetime, Fin datetime, constraint foreign key (DNI) references investigadores (DNI) on delete cascade on update cascade, constraint foreign key (NumSerie) references equipos (NumSerie) on delete cascade on update cascade, primary key (DNI, NumSerie));";
		tableName = "reserva";
		jack.createTable(con, dbName, tableName, query);

		// Finally we insert registers into the tables
		query = "insert into facultad (Nombre) values ('Universidad de Harvard'), ('Universidad de Oxford'), ('Unbiversidad de Cambridge'), ('Universidad de Berlín'), ('Universidad de Guanajuato'), ('Universidad de Hamburgo'), ('Universidad de Múnich'), ('Universidad de Bonn'), ('Universidad de Friburgo'), ('Universidad de Aquisgrán');";
		tableName = "facultad";
		jack.insertData(con, dbName, tableName, query);

		query = "insert into investigadores (DNI, NomApels, Facultad) values ('0000000A', 'Ernst Müller', 4), ('1111111R', 'Edelina Shultz', 10), ('2222222H', 'Erika Bauer', 7), ('3333333Q', 'Pacheco Mendoza Moreno', 5), ('4444444T', 'Alexander Bonham', 2), ('5555555G', 'Hans Klein', 2), ('6666666K', 'Wanda Schwarz', 3), ('7777777D', 'Otilia Günther', 3), ('8888888P', 'Helmut Kraus', 7), ('9999999F', 'Emma Winter', 7);";
		tableName = "investigadores";
		jack.insertData(con, dbName, tableName, query);
		
		query = "insert into equipos (NumSerie, Nombre, Facultad) values ('T001','HARVAR_TEAM', 1), ('T002','OXFORD_TEAM', 2), ('T003','CAMBRI_TEAM', 3), ('T004','BERLIN_TEAM', 4), ('T005','GUANAJ_TEAM', 5), ('T006','HAMBUR_TEAM', 6), ('T007','MUNICH_TEAM', 7), ('T008','DEBONN_TEAM', 8), ('T009','FRIBUR_TEAM', 9), ('T010','AQUISG_TEAM', 10);";
		tableName = "equipos";
		jack.insertData(con, dbName, tableName, query);
		
		query = "insert into reserva (DNI, NumSerie, Comienzo, Fin) values ('0000000A', 'T004', '2021-01-07', '2021-07-22'), ('1111111R', 'T010', '2021-03-07', '2021-08-25'), ('2222222H', 'T007', '2019-03-31', '2021-12-18'), ('3333333Q', 'T005', '2018-06-23', '2021-07-28'), ('4444444T', 'T002', '2022-01-07', '2022-05-08'), ('5555555G', 'T002', '2020-02-12', '2021-07-22'), ('6666666K', 'T003', '2021-01-07', '2021-07-22'), ('7777777D', 'T003', '2017-04-09', '2019-03-12'), ('8888888P', 'T007', '2021-02-19', '2021-04-05'), ('9999999F', 'T007', '2021-01-18', '2021-11-14');";
		tableName = "reserva";
		jack.insertData(con, dbName, tableName, query);

		// Finally we close the connection
		jack.closeConnection(con);

		// We show a message if everything went well
		JOptionPane.showMessageDialog(null, "The inserts have been correct.");
	}
}
