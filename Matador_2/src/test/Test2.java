package test;

import game.GameController;
import game.Player;
import startmenugui.StartMenuDialog;

public class Test2
{
	public static void main(String[] args)
	{
		GameController.createInstance(new StartMenuDialog().startDialog());
		Player[] players = GameController.getInstance().getAllPlayers();
		
		for (int i = 0; i < players.length; i++)
		{
			System.out.println(players[i].getName());
		}
	}
}
