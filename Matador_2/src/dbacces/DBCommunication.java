package dbacces;

import game.fields.Street;
import game.Board;
import game.FieldController;
import game.Player;
import game.GameController;
import game.fields.Field;
import game.fields.Ownable;
import game.GameData;
import java.io.Writer;
import java.sql.SQLException;
import java.util.LinkedList;
import startmenugui.StartMenuDialog;

public class DBCommunication 
{


	public void saveGame()
	{
		DataAccess da = new DataAccess();

		Player p;
		for (int i = 0; i < GameController.getInstance().getAllPlayers().length; i++)
		{
			p = GameController.getInstance().playerQueue.get(i);
			String sql = "INSERT INTO player VALUES ("+p.getid()+",'"+p.getName()+"',"+p.getPosition()+","+p.getAccount()+","+p.getPrisonTurnCount()+","+p.getGetOutOfJailCards()+",'"+p.getCarColour()+"',"+p.getCarType()+")";
			try {
				da.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Ownable o;
		Street s;
		GameData g = new GameData();
		Board b = new Board(g.getFields(),g.getCards());
		for (int j = 0; j < GameController.getInstance().getFields().length ; j++)
		{
			b.getField(j);
			o = (Ownable) b.getField(j);
			s = (Street) b.getField(j);
			String sql = "UPDATE fields (field_owner, number_of_houses) VALUES ("+o.getOwner()+", "+s.getHouses()+" )";
			try {
				da.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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



