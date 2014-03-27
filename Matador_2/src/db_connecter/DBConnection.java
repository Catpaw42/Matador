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
	public void getData(){
try{
	
	String query = "SELECT * FROM player";
	rs = st.executeQuery(query);
	System.out.println("Oprettede spillere");
	while(rs.next()){
		String player_name = rs.getString("player_name");
		String account_balance = rs.getString("account_balance");
		System.out.println("Name: " + player_name + " " + "Balance: " + account_balance);
		
		
	}

}catch(Exception ex){
	System.out.println(ex);
}
	}

}

