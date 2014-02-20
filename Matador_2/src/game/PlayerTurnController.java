package game;
import game.fields.*;

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
		
		// moves the player
		if (currentPlayer.getAccount().getBalance() > 40) {
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + dieSum - 40); 
		}
		else {
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + dieSum);
		}
		
		// do land on field
		// fc.LandOnField(currentPlayer, currentField); <--- Er det nødvendigt at udvidde playerTurn med et en field parameter?? Den klager uden.
		
		
		//return
		return false;
	}
	

}
