package test;

import static org.junit.Assert.*;
import game.Player;

import java.awt.Color;
import java.sql.SQLException;

import dbacces.DBAccess;

public class Test
{

	@org.junit.Test
	public void test()
	{
		System.out.println(Color.BLUE);
		Color t = new Color(000,000,255);
		System.out.println(t.getRGB());
		System.out.println(t);
		Color u = new Color(t.getRGB());
		System.out.println(u.getRGB());
		System.out.println(u);
		
	}
	@org.junit.Test
	public void test2()
	{
		DBAccess dba = new DBAccess();
		Player[] players = {new Player("magnus", Color.RED, 2)};
		players[0].setPlayerPosition(1);
		players[0].setGetOutOfJailCards(0);
		players[0].setPrisonTurnCount(0);
		
		try
		{
			dba.insertPlayers(players[0].getName(),
					  players[0].getPosition(),
					  players[0].getAccount().getBalance(),
					  players[0].getPrisonTurnCount(),
					  players[0].getGetOutOfJailCards(),
					  players[0].getCarColour().getRGB(),
					  players[0].getCarType(),
					  0);
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
