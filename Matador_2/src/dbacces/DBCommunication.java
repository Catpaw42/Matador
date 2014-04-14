package dbacces;

import game.GameController;
import game.Player;
import game.fields.Field;
import game.fields.Ownable;
import game.fields.Street;

import java.sql.SQLException;

public class DBCommunication 
{
	public static void saveGame()
	{
		DataAccess da = new DataAccess();

		//Save player data to the database
		Player[] players = GameController.getInstance().getAllPlayers();
		for (int i = 0; i < players.length; i++)
		{
			Player p = players[i];
			String sql = "INSERT INTO player VALUES ("+p.getid()+",'"
													  +p.getName()+"',"
													  +p.getPosition()+","
													  +p.getAccount()+","
													  +p.getPrisonTurnCount()+","
													  +p.getGetOutOfJailCards()+",'"
													  +p.getCarColour()+"',"
													  +p.getCarType()+","
													  +/*get turn*/")";
			try
			{
				da.executeUpdate(sql);
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
					da.executeUpdate(sql);
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
					da.executeUpdate(sql);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static void loadGame()
	{
		
	}
	
	public void collectFromPlayer()
	{
		DataAccess da = new DataAccess();

		String query = "SELECT * FROM player";
		try {
			da.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void collectFromFields()
	{
		DataAccess da = new DataAccess();

		String query = "SELECT FROM fields WHERE field_owner ARE NOT NULL";

		try{
			da.executeQuery(query);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}



