package game;

import gui.GUI;
import game.fields.*;

public class FieldController {

	public void LandOnField(Player p, Field f)
	{
		//for all fields, do:
		
//		if(f.getClass() == Refuge.class){
//			
//		}   Skal sansynligvis ikke bruges, men nu er den her, hvis det bliver nødvendigt.
		
		// Controler for Tax-feltet.
		if(f.getClass() == Tax.class){
		Tax t = (Tax) f; // Type caster field til tax.
		if(p.getPosition() == 5) // hvis players position er 5, har han et valg. Ellers trækkes der bare penge.
		{
		
		int i = new GUI().getUserYesNoCancelChoise("You have to options: A pay 10% of your current Balnce or B pay 4000kr");	
		// Menu hvor player vælger om han vil betale et fast beløb, eller i procent.
		
		if(i==0){
			p.getAccount().setBalance(p.getAccount().getBalance() - t.getRevenueRate()); // ændre balancen med fast beløb	
		}
		else {
			p.getAccount().setBalance(p.getAccount().getBalance() * t.getTaxRate());	 // ændre balancen med procent.
		}
		
		} // slut på position = 5
		else{
			p.getAccount().setBalance(p.getAccount().getBalance() - t.getExtraOrdinaryRate());
		} // trækker penge, hvis feltet ikke er = 5
			
		}
		
		if(f.getClass() == Ownable.class){
			
		}
		
		if(f.getClass() == Jail.class){
			
		}
		
		if(f.getClass() == Chance.class){
			
		}
	}
}
