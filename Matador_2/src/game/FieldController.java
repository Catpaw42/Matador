package game;

import gui.GUI;
import game.fields.*;

public class FieldController {
	
	GUI gui = new GUI();

	public void LandOnField(Player p, Field f)
	{
		//for all fields, do:
		
//		if(f.getClass() == Refuge.class){
//			
//		}   Skal sansynligvis ikke bruges, men nu er den her, hvis det bliver nÃ¸dvendigt.
		
		// Controller for Tax-feltet. //
		// ------------------------- //
		// Controller for Tax-feltet. //
		if(f.getClass() == Tax.class){
		Tax t = (Tax) f; // Type caster field til tax.
		if(p.getPosition() == 5) // hvis players position er 5, har han et valg. Ellers trÃ¦kkes der bare penge.
		{
		
//		int i = gui.getUserYesNoCancelChoise("You have to options: A pay 10% of your current Balnce or B pay 4000kr");
			
		String[] options = {"Pay 10%", "Pay 4000kr."}; // array with the options thats should be on the buttons
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
		// Controller for Tax-feltet SLUT //
		
		
		// Controller for ownable-feltet. //
		// ------------------------------ //
		// Controller for ownable-feltet. //
		
		if(f.getClass() == Ownable.class){
		Ownable o = (Ownable) f;
		
		}
		// Controller for ownable-feltet SLUT //
		
		
		// Controller for Jail-feltet. //
		// --------------------------- //
		// Controller for Jail-feltet. //
		if(f.getClass() == Jail.class){
		Jail j = (Jail) f;	
			if(j.getFængslet() == true) {
				// Options menu. Man skal slå med terningerne uanset hvad, så vidt jeg forstår. 
				// Burde man derfor ikke bare bede player om at slå og så derefter, hvis player ikke slår to ens, give ham options der?
			}
			else {
			// Ikke noget så vidt jeg husker.	
			}
		}
		// Controller for Jail-feltet SLUT //
		
		
		// Controller for Chance-feltet. //
		// ------------------------- //
		// Controller for Chance-feltet. //
		if(f.getClass() == Chance.class){
		Chance c = (Chance) f;	
		}
		// Controller for Chance-feltet SLUT //
	}
}
