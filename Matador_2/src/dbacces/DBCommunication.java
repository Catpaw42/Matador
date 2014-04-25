package dbacces;

import game.GameController;
import game.GameData;
import game.Player;
import game.fields.Field;
import game.fields.Ownable;
import game.fields.Street;

import java.sql.SQLException;

public class DBCommunication 
{
	public static void saveGame()
	{
		DBAccess dba = new DBAccess();

		//Save player data to the database
		Player[] players = GameController.getInstance().getAllPlayers();
		for (int i = 0; i < players.length; i++)
		{	
			try
			{
				dba.insertPlayers(players[i].getName(),
						players[i].getPosition(),
						players[i].getAccount().getBalance(),
						players[i].getPrisonTurnCount(),
						players[i].getGetOutOfJailCards(),
						players[i].getCarColour().getRGB(),
						players[i].getCarType(),
						i);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		Field[] fields = GameController.getInstance().getFields();
		for (int j = 0; j < fields.length ; j++)
		{
			if(fields[j] instanceof Ownable && ((Ownable)fields[j]).getOwner() != null)
			{
				String sql = "UPDATE fields WHERE field_number="+ j+1 + " (field_owner) VALUES ("+((Ownable)fields[j]).getOwner()+" )";
				try 
				{
					
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(fields[j] instanceof Street && ((Street)fields[j]).getHouses() != 0)
			{
				String sql = "UPDATE fields WHERE field_number="+ j+1 + " (number_of_houses) VALUES ("+((Street)fields[j]).getHouses()+" )";
				try 
				{
					
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static GameData loadGame()
	{
		DataAccess da = new DataAccess();

		//create new Gamedata, object.
		GameData data = new GameData();

		//get player data from DB
		String sql = "SELECT * FROM players";
		Object[][] sqlPlayerData = null;
		try
		{
			sqlPlayerData = da.executeQuery(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		//load into gamedata
		Player[] players = new Player[sqlPlayerData.length];
		for (int i = 0; i < players.length; i++)
		{

			players[i] = new Player((String)sqlPlayerData[i][1], carColor, carType)
		}

		//get fields from DB

		//load into gamedata

		//return gamedata for use in setup.
		return data;
	}


	public void collectFromFields()
	{
		DataAccess da = new DataAccess();

		String query = "SELECT FROM fields WHERE field_owner ARE NOT NULL";

		try
		{
			da.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}



