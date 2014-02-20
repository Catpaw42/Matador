package game;

import java.awt.Color;
import java.util.ArrayList;

public class GameOptions
{
	//private int startingCapital;
	private Player[] players;

	private GameOptions(Builder b)
	{

	}

	public Player[] getPlayers()
	{
		return players;
	}

	public void setPlayers(Player[] players)
	{
		this.players = players;
	}
	
	public static class Builder
	{
		private ArrayList<Player> players = new ArrayList<Player>();
		
		public Builder addPlayer(String playerName, Color carColor, int carType)
		{
			this.players.add(new Player(playerName, carColor, carType));
			return this;
		}
		
		public GameOptions build()
		{
			return new GameOptions(this);
		}
	}

}
