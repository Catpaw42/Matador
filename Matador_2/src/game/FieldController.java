package game;

import gui.GUI;
import game.fields.*;

public class FieldController {

	GUI gui = new GUI();
	Board board = new Board();


	public void LandOnField(Player p, int nr)
	{
		//for all fields, do:

		//		if(f.getClass() == Refuge.class){
		//			
		//		}   Skal sansynligvis ikke bruges, men nu er den her, hvis det bliver nÃ¸dvendigt.

		// Controller for Tax-feltet. //
		// ------------------------- //
		// Controller for Tax-feltet. //
		if(board.getField(nr).getClass() == Tax.class){
			taxController(p, nr);

		}
		// Controller for Tax-feltet SLUT //


		// Controller for ownable-feltet. //
		// ------------------------------ //
		// Controller for ownable-feltet. //

		if(board.getField(nr).getClass() == Ownable.class){
			Ownable o = (Ownable) board.getField(nr);

		}
		// Controller for ownable-feltet SLUT //


		// Controller for Jail-feltet ligger i PlayerTurnController//


		// Controller for Chance-feltet. //
		// ------------------------- //
		// Controller for Chance-feltet. //
		if(board.getField(nr).getClass() == Chance.class){
			Chance c = (Chance) board.getField(nr);	
		}
		// Controller for Chance-feltet SLUT //
	}


	private void taxController(Player p, int nr) {
		Tax t = (Tax) board.getField(nr); // Type caster field til tax.
		if(p.getPosition() == 5) // hvis players position er 5, har han et valg. Ellers trÃ¦kkes der bare penge.
		{

			//		int i = gui.getUserYesNoCancelChoise("You have to options: A pay 10% of your current Balnce or B pay 4000kr");

			String[] options = {"Pay 4000kr.", "Pay 10%"}; // array with the options thats should be on the buttons
			int i = gui.getUserButtonPressed(options,"Choose Payment", "Tax"); //Added a method using buttons
			// Menu hvor player vÃ¦lger om han vil betale et fast belÃ¸b, eller i procent.

			if(i==0){
				p.getAccount().setBalance(p.getAccount().getBalance() - t.getRevenueRate()); // Ã¦ndre balancen med fast belÃ¸b	
			}
			else {
				p.getAccount().setBalance(p.getAccount().getBalance() * t.getTaxRate());	 // Ã¦ndre balancen med procent.
			}

		} // slut pÃ¥ position = 5
		else{
			p.getAccount().setBalance(p.getAccount().getBalance() - t.getExtraOrdinaryRate());
		} // trÃ¦kker penge, hvis feltet ikke er = 5
	}
}
