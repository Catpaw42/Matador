package test;

import game.GameData;
import game.Player;
import game.fields.Ownable;
import game.fields.Street;

import java.awt.Color;
import java.sql.SQLException;

import dbacces.DBAccess;
import dbacces.DBCommunication;

public class DatabaseTest
{
	@org.junit.Test
	public void loadGameTest()
	{
		GameData data = DBCommunication.loadGame();
		for (int i = 0; i < data.getPlayers().length; i++)
		{
			System.out.println("Player[" + i + "] = " + data.getPlayers()[i].getName());
		}
		System.out.println();
		for (int i = 0; i < data.getFields().length; i++)
		{
			if(data.getFields()[i] instanceof Ownable && ((Ownable)data.getFields()[i]).getOwner() != null)
				System.out.println(data.getFields()[i].getName() + " : " + ((Ownable) data.getFields()[i]).getOwner().getName());
			if(data.getFields()[i] instanceof Street && ((Ownable)data.getFields()[i]).getOwner() != null)
				System.out.println("houses: " + ((Street)data.getFields()[i]).getHouses());
		}
		
	}
	
//	@org.junit.Test
//	public void testFields()
//	{
//		DBAccess dba = new DBAccess();
//		try
//		{
//			Object[][] fieldData = dba.getFieldChanges();
//			for (int i = 0; i < fieldData.length; i++)
//			{
//				System.out.println((int)fieldData[i][0]);
//				System.out.println((String)fieldData[i][1]);
//				System.out.println((int)fieldData[i][2]);
//			}
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	@org.junit.Test
//	public void test()
//	{
//		DBAccess dba = new DBAccess();
//		Player[] players = {new Player("magnus", Color.RED, 2)};
//		players[0].setPlayerPosition(1);
//		players[0].setGetOutOfJailCards(0);
//		players[0].setPrisonTurnCount(0);
//		
//		try
//		{
//			dba.insertPlayer(players[0], 0);
//			Player[] playersFromDB = dba.getAllPlayers();
//			
//			System.out.println(playersFromDB.length);
//			
//			for (int i = 0; i < playersFromDB.length; i++)
//			{
//				System.out.println(i);
//				System.out.println(playersFromDB[i].getName());
//			}
//			
//			dba.resetPlayers();
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}

}
