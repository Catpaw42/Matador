package game;


public class GameOptions
{
	//private int startingCapital;
	private Player[] players;

	public Player[] getPlayers()
	{
		return players;
	}

	public void setPlayers(Player[] players)
	{
		this.players = players;
	}

	public int amountNotBroke(){
		int sum = 0;
		for (int i = 0; i < players.length; i++) {
			if(!players[i].isBroke())
				sum++;
		}
		return sum;
	}
}
