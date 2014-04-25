package dbacces;

import game.fields.Ownable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAccess
{
	private String driver = "com.mysql.jdbc.Driver";
	private String database_url = "jdbc:mysql://mysql7.gigahost.dk:3306/jakobsabinsky_matador";// Default
																								// schema:
																								// test.
	private String username = "jakobsabinsky";
	private String password = "Helena11";
	
	private int dropPlayers()
	{
		return 0;
	}
	
	public int insertPlayers(String player_name, int field_position,
			int account_balance, int prison_turn, int get_out_of_jail_cards,
			int car_color, int car_type, int player_turn) throws SQLException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int rows = 0;

		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(database_url, username, password);
			String sql = "INSERT INTO player (player_name, field_position, account_balance, prison_turn,"
										   + "get_out_of_jail_cards, car_color, car_type, player_turn)"
										   + "VALUES (?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, player_name);
			statement.setInt(2, field_position);
			statement.setInt(3, account_balance);
			statement.setInt(4, prison_turn);
			statement.setInt(5, get_out_of_jail_cards);
			statement.setInt(6, car_color);
			statement.setInt(7, car_type);
			statement.setInt(8, player_turn);

			rows = statement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			statement.close();
			connection.close();
		}
		
		return rows;
	}

	public int updateFieldOwner(String owner) throws SQLException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int rows = 0;
		
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(database_url, username, password);
			String sql = "UPDATE fields WHERE field_number="+ j+1 + " (field_owner) VALUES ("+((Ownable)fields[j]).getOwner()+" )";
		}
		catch (ClassNotFoundException e)
		{
			// TODO: handle exception
		}

		return 0;
	}
}
