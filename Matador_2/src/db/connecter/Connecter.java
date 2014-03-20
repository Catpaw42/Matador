package db.connecter;

import java.sql.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;

//Building a connection to database
public class Connecter {

	//Klassevariabler
	private static String driver;
	private static String jdbc;
	private static String host;
	private static String db;
	private static String username;
	private static String password;

	public static Connection.getConnection()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException
			{
				setConnectionProperties("DBConnection.properties");
				
				//Indlæs driveren
				Class.forName(driver);
				
				//Opret en forbindkelse
				return DriverManager.getConnection(jdbc + host + "/" + db, username, password);
				
			}

	private static void setConnectonProperties(String propertyFileName)
			throws FileNotFoundException, IOException
			{
		//Indlæs properties fil
		FileInputStream input = new FileInputStream(propertyFileName);
		Properties prop = new Properties();
		prop.load(input);

		//Læs properties fil
		driver = prop.getProperty("driver");
		jdbc = prop.getProperty("jdbc");
		host = prop.getProperty("host");
		db = prop.getProperty("db");
		username = prop.getProperty("username");
		password = prop.getProperty("password");

			}
}
