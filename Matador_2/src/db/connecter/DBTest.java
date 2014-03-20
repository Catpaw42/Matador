package db.connecter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class DBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		try {
			//Oprette forbindelse
			Connection conn = DBConnectionBuilder.getConnection();

			String sql = "SELECT felt.navn FROM felter WHERE Felt.nummer <= 40  "
					+ "ORDER BY felt.navn";

			//Hent data
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			//udskriv kolonnerne
			ResultSetMetaData rsMetaData = rs.getMetaData();

			String feltNavnColumn = rsMetaData.getColumnName(1);

			System.out.println(feltNavnColumn);

			//Udskriv data fra database
			while (rs.next())
			{
				//Hent data 
				String fieldName = rs.getString("felt.navn");

				//udskriv data
				System.out.println(fieldName);
			}
			//luk statements
			stmt.close();
			rs.close();

			//luk forbindelse til db
			conn.close();

		}

	}
}


