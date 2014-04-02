package db_connecter;

import java.sql.*;



//Building a connection to database
public class DBConnection {

	private Connection con;
	private Statement st;
	private ResultSet rs;


	public DBConnection(){

		try
		{				
			//Indlæs driveren
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/matador","root","Helena11");
			st = con.createStatement();
		}catch(Exception ex){
			System.out.println("Error: "+ ex);
		}


	}
	
	
	public static ResultSet doQuery(String cmd) throws Exception {
		Statement st = null;
		try {
			st = DBConnection().createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			return st.executeQuery(cmd);
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}

	private static Connection DBConnection() {
		// TODO Auto-generated method stub
		return null;
	}
	public static int doUpdate(String cmd) throws Exception {
		Statement st = null;
		try {
			st = DBConnection().createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			return st.executeUpdate(cmd);
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}

	public static int doDelete(String cmd) throws Exception {
		Statement st = null;
		try {
			st = DBConnection().createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			return st.executeUpdate(cmd);
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}

}


