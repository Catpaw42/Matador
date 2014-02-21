package game;
import game.fields.*;
import gui.GUI;

public class PlayerTurnController
{
	/**

	 * @param currentPlayer The active player
	 * @return true if the player has gone broke, false othervice
	 */
	private Die die1 = new Die();
	private Die die2 = new Die();
	private int dieSum = die1.roll() + die2.roll();
	private FieldController fc = new FieldController();
	GUI gui = new GUI();

	public boolean playerTurn(Player currentPlayer)
	{
		// If fængslet
		if(currentPlayer.getPosition() == 12 &&  currentPlayer.isFængslet() == true) {
			System.out.println("Du er i fængsel.");

			String[] options = {"Pay or use Chance card", "Roll dices"}; // array with the options thats should be on the buttons
			int i = gui.getUserButtonPressed(options,"Choose Payment or card", "Roll dices"); //Added a method using buttons
			// Menu hvor player vÃ¦lger om han vil betale et fast belÃ¸b, eller i procent.

			if(i==0){
				// Ny valgmulighed
			}
			else {
				die1.roll();
				die2.roll();
				if(die1.roll() == die2.roll()){
					// affængsler player
					currentPlayer.setFængslet(false);
					
					// moves the player
					movePlayer(currentPlayer, dieSum);

					// do land on field
					fc.LandOnField(currentPlayer, currentPlayer.getPosition());	

				}
			}

			// Option 1: Betalt 1000 eller brug kort.
			// Option 2: Rull med terningerne
			// Præmis: rullet med terninger.
		}


		else{
			die1.roll();
			die2.roll();

			// moves the player
			movePlayer(currentPlayer, dieSum);

			// do land on field
			fc.LandOnField(currentPlayer, currentPlayer.getPosition());

		}
		//return
		return false;
	}

	private void movePlayer(Player currentPlayer, int dieSum) {
		if (currentPlayer.getAccount().getBalance() > 40) {
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + dieSum - 40); 
		}
		else {
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + dieSum);
		}
	}

}
