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
		
		//Clear the database.
		try
		{
			dba.resetPlayers();
			dba.resetFields();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//Save player data to the database
		Player[] players = GameController.getInstance().getAllPlayers();
		for (int i = 0; i < players.length; i++)
		{	
			try
			{
				dba.insertPlayer(players[i],i);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		//save field data to the database
		Field[] fields = GameController.getInstance().getFields();
		for (int j = 0; j < fields.length ; j++)
		{
			if(fields[j] instanceof Ownable && ((Ownable)fields[j]).getOwner() != null)
			{
				try 
				{
					dba.updateFieldOwner(((Ownable) fields[j]).getOwner().getName(), fields[j].getFieldNumber());
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(fields[j] instanceof Street && ((Street)fields[j]).getHouses() != 0)
			{
				try 
				{
					dba.updateNumberOfHouses(((Street) fields[j]).getHouses(), fields[j].getFieldNumber());
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
		DBAccess dba = new DBAccess();

		//create new Gamedata, object.
		GameData data = new GameData();

		//get player data from DB and load them into Gamedata.
		try
		{
			Player[] players = dba.getAllPlayers();
			Object[][] fieldChanges = dba.getFieldChanges();
			Field[] fields = data.getFields();
			
			//for each of the changed fields
			for (int i = 0; i < fieldChanges.length; i++)
			{
				//find the coresponding fieldnumber in the field list
				for (int j = 0; j < fields.length; j++)
				{
					if(fields[j].getFieldNumber() == (int)fieldChanges[i][0] && fields[j] instanceof Ownable)
					{
						//find the matching player, and set him as the owner
						for (int k = 0; k < players.length; k++)
						{
							if(players[k].getName().equals((String)fieldChanges[i][1]))
								((Ownable)fields[j]).setOwner(players[k]);
						}
						//if in addition the field is a street, update the number of houses.
						if(fields[j] instanceof Street)
							((Street)fields[j]).setHouses((int)fieldChanges[i][2]);
					}
				}
			}
			
			data.setPlayers(players);
			data.setFields(fields);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		//return gamedata with the new playerlist + updated field list
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



