package dbacces;

import java.sql.SQLException;

public class DBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DataAccess da = new DataAccess();
		String query = "SELECT * FROM fields";
		Object[][] temp = null;
		try {
			temp = da.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < temp.length; i++) {
			
			System.out.println(temp[i][1]);
		}

	}

	
}


