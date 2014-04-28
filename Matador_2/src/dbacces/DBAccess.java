package dbacces;

import game.Player;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBAccess
{
	private String driver = "com.mysql.jdbc.Driver";
	private String database_url = "jdbc:mysql://mysql7.gigahost.dk:3306/jakobsabinsky_matador";
	private String username = "jakobsabinsky";
	private String password = "Helena11";
	
	/**
	 * Deletes all players from the "player" table
	 * @return the number of tuples changed in the DataBase
	 * @throws SQLException
	 */
	public void resetPlayers() throws SQLException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;

		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(database_url, username, password);
			String sql = "DELETE FROM player";
			
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
			
			sql = "ALTER TABLE player AUTO_INCREMENT = 1";
			
			statement2 = connection.prepareStatement(sql);
			statement2.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			statement.close();
			connection.close();
		}
	}
	
	/**
	 * Resets the data in the "fields" table.
	 * Set its back to: field_owner = NULL and number_of_houses = NULL
	 * @return The number of tuples changed.
	 * @throws SQLException
	 */
	public int resetFields() throws SQLException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int rows = 0;

		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(database_url, username, password);
			String sql = "UPDATE fields SET field_owner = NULL , number_of_houses = NULL";
			statement = connection.prepareStatement(sql);

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
	
	/**
	 * Inserts a new tuple in the player-table, representing the given player.
	 * 
	 * @param player the player to save in the database.
	 * @param player_turn the players position in the turn-queue.
	 * @return The number of tuples changed.
	 * @throws SQLException
	 */
	public int insertPlayer(Player player, int player_turn) throws SQLException
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

			statement.setString(1, player.getName());
			statement.setInt(2, player.getPosition());
			statement.setInt(3, player.getAccount().getBalance());
			statement.setInt(4, player.getPrisonTurnCount());
			statement.setInt(5, player.getGetOutOfJailCards());
			statement.setInt(6, player.getCarColour().getRGB());
			statement.setInt(7, player.getCarType());
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
	/**
	 * Changes the owner of a given field
	 * @param owner The name of the owner, this player must exist in the player-table
	 * @param fieldNr The number of the field (1-40)
	 * @return The number of tuples changed.
	 * @throws SQLException
	 */
	public int updateFieldOwner(String owner, int fieldNr) throws SQLException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int rows = 0;
		
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(database_url, username, password);
			String sql = "UPDATE fields SET field_owner = (SELECT id_player FROM player where player_name = ?) WHERE field_number = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, owner);
			statement.setInt(2, fieldNr);
			
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
	
	/**
	 * Changes the number of houses build on a given field (0-5).
	 * @param houses the number of houses
	 * @param fieldNr The number of the field (1-40)
	 * @return The number of tuples changed.
	 * @throws SQLException
	 */
	public int updateNumberOfHouses(int houses, int fieldNr) throws SQLException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int rows = 0;
		
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(database_url, username, password);
			String sql = "UPDATE fields SET number_of_houses = ? WHERE field_number = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, houses);
			statement.setInt(2, fieldNr);

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
	
	/**
	 * returns all the players currently in the database
	 * @return An array of players
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public Player[] getAllPlayers() throws SQLException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		Player[] result = null;
		
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(database_url, username, password);
			String sql = "SELECT * FROM player";
			statement = connection.prepareStatement(sql);
			
			
			ResultSet data = statement.executeQuery();

			//get the data out into a format we can work with.
			ArrayList<Player> players = new ArrayList<Player>();
			ArrayList<Integer> playerTurn = new ArrayList<Integer>();
			while(data.next())
			{
				Player temp = new Player(data.getString(2), new Color(data.getInt(7)), data.getInt(8));
				temp.setPlayerPosition(data.getInt(3));
				temp.getAccount().setBalance(data.getInt(4));
				temp.setPrisonTurnCount(data.getInt(5));
				temp.setGetOutOfJailCards(data.getInt(6));
				players.add(temp);
				playerTurn.add(data.getInt(9));
			}
			
			//sort the players according to their turn
			result = new Player[players.size()];
			for (int i = 0; i < result.length; i++)
			{
				result[playerTurn.get(i)] = players.get(i);
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			statement.close();
			connection.close();
		}
		
		//return the sorted list of players
		return result;
	}
	
	/**
	 * returns a double Object array, containing the field_number, owner_name and number_of_houses in that order.
	 * for all field where the owner is not null.
	 * @return A double object array, with the field data
	 * @throws SQLException
	 */
	public Object[][] getFieldChanges() throws SQLException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		Object[][] result = null;
		
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(database_url, username, password);
			String sql = "SELECT field_number, player_name, number_of_houses FROM fields JOIN player ON fields.field_owner = player.id_player WHERE field_owner IS NOT null";
			statement = connection.prepareStatement(sql);
			
			//format of resultset: field_number, player_name, number_of_houses
			ResultSet data = statement.executeQuery();
			
			int columnCount = data.getMetaData().getColumnCount();
			ArrayList<Object[]> fieldArrayList = new ArrayList<Object[]>();
			while(data.next())
			{
				Object[] row = new Object[columnCount];
				row[0] = data.getInt(1);
				row[1] = data.getString(2);
				row[2] = data.getInt(3);
				
				fieldArrayList.add(row);
			}
			result = fieldArrayList.toArray(new Object[0][0]);
			
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			statement.close();
			connection.close();
		}
		//return a double array containing the data from the fields
		return result;
	}
}
