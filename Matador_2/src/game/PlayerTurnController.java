package game;

public class PlayerTurnController
{
	/**

	 * @param currentPlayer The active player
	 * @return true if the player has gone broke, false othervice
	 */
	private Die die1 = new Die();
	private Die die2 = new Die();
	private FieldController fc = new FieldController();
	
	public boolean playerTurn(Player currentPlayer)
	{
		die1.roll();
		die2.roll();
		
		int dieSum = die1.roll() + die2.roll();
		
		if (currentPlayer.getAccount().getBalance() > 40) {
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + dieSum - 40); 
		}
		else {
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + dieSum);
		}
		//moves the player
		
		// fc.LandOnField(currentPlayer, Field f);//do land on field
		//return
		return false;
	}
	

}
