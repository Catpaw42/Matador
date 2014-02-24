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
	private GUI gui = new GUI();
	private final int START_BONUS = 4000;

	public boolean playerTurn(Player currentPlayer)
	{

		// TODO: Method that sends player to jail if the roll the same eyes three rounds in a row.
		// TODO: Return TRUE if player is broke.

		// If fængslet
		Jail j = (Jail) fc.board.getField(12);

		if(currentPlayer.getPosition() == 12 &&  currentPlayer.isFængslet() == true)
			inJail(currentPlayer, j);
		else{
			// moves the player
			movePlayer(currentPlayer, dieSum);
		}
		//return
		return false;
	}

	private void inJail(Player currentPlayer, Jail j) {
		{
			System.out.println("Du er i fængsel.");

			String[] options1 = {"Pay 1000kr", "Use Chance Card", "Roll dices"}; // array with the options thats should be on the buttons
			int i = gui.getUserButtonPressed(options1,"You're in jail. Choose between these three options", "Jail Options"); //Added a method using buttons
			// Menu hvor player vÃ¦lger om han vil betale et fast belÃ¸b, eller i procent.

			if(i==0){
				if(i==0){
					// Withdraws 1000 kr from currentPlayer
					currentPlayer.getAccount().setBalance(currentPlayer.getAccount().getBalance() - j.getFængselstakst());
					currentPlayer.setFængslet(false);
					movePlayer(currentPlayer, dieSum);
				}
				if(i==1){
					// TODO: method to verify that use has chance card
					currentPlayer.setFængslet(false);
					movePlayer(currentPlayer, dieSum);
				}

				if(i==2) {
					// Player chooses to rolls dices.
					prisonerRollsDices(currentPlayer);
				}
			}

		}
	}

	private void prisonerRollsDices(Player currentPlayer) {
		die1.roll();
		die2.roll();
		if(die1.roll() == die2.roll()){
			// affængsler player
			currentPlayer.setFængslet(false);

			// moves the player
			movePlayer(currentPlayer, dieSum);
		}
		else {
			if (true){
				// TODO: method that forces player to pay if player hasn't rolled the smae eyes in three rounds.
			}
		}
	}

	private void movePlayer(Player currentPlayer, int dieSum) {
		this.dieSum = dieSum;
		die1.roll();
		die2.roll();
		if (currentPlayer.getPosition() + dieSum > 40) {
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + dieSum - 40); 
			currentPlayer.getAccount().setBalance(currentPlayer.getAccount().getBalance() + START_BONUS);
		}
		else {
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + dieSum);
		}

		// do land on field
		fc.LandOnField(currentPlayer, currentPlayer.getPosition());

	}

}
