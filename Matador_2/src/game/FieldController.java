package game;

import game.Account.IllegalAmountException;
import game.Account.InsufficientFundsException;
import game.fields.Chance;
import game.fields.Ownable;
import game.fields.Tax;
import gui.GUI;

public class FieldController
{

	GUI gui = new GUI();
	Board board = new Board();

	public void LandOnField(Player p, int nr)
	{
		
		gui.appendTextToTextArea(board.getField(nr).getMessage());
		
		
		// consider printing an extra  message when landing on a refuge.
		
		if (board.getField(nr).getClass() == Tax.class)
		{
			taxHandler(p, nr);
		}

		if (board.getField(nr).getClass() == Ownable.class)
		{
			ownableHandler(p, nr);
		}

		if (board.getField(nr).getClass() == Chance.class)
		{
			Chance c = (Chance) board.getField(nr);

		}

	}

	private void ownableHandler(Player p, int nr)
	{
		Ownable o = (Ownable) board.getField(nr);

		// someone owns this field
		if (o.getOwner() != null)
		{
			try
			{
				p.getAccount().withdraw(o.getRent());
				o.getOwner().getAccount().deposit(o.getRent());
			}
			catch (InsufficientFundsException e)
			{
				//we end here if the player does'nt have enough money to pay rent
				
			}
			catch (IllegalAmountException e)
			{
				//A real error happened, stop execution
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(0);
			}
			
		}
		// nobody owns this field
		else
		{
			String[] options = { "Buy (" + o.getPrice() + ")", "No" };
			if (gui.getUserButtonPressed(options, "Nobody owns " + o.getName() + ". Would you like to buy it?", "Field for sale!") == 0)
			{
				try
				{
					p.getAccount().withdraw(o.getPrice());
					o.setOwner(p);
				}
				catch (InsufficientFundsException e)
				{
					//we end here if the player does'nt have enough money to buy the field.
					//maybe this should be checked before the option is given.
				}
				catch (IllegalAmountException e)
				{
					//A real error happened, stop execution
					System.err.println(e.getMessage());
					e.printStackTrace();
					System.exit(0);
				}
				
			} else
			{
				//if player chooses not to buy the field.
			}
		}
	}

	private void taxHandler(Player p, int nr)
	{
		Tax t = (Tax) board.getField(nr); // Type caster field til tax.
		if (p.getPosition() == 5) // hvis players position er 5, har han et valg. Ellers trækkes der bare penge.
		{
			String[] options = { "Pay 4000kr.", "Pay 10%" }; // array with the options thats should be on the buttons.
			int i = gui.getUserButtonPressed(options, "Choose Payment", "Tax"); // show a choice menu, with the above options.

			if (i == 0)
			{
				try {
					p.getAccount().withdraw(t.getRevenueRate());
				} catch (InsufficientFundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAmountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // update balance
			} else
			{
				try {
					p.getAccount().withdraw((p.getTotaltAssets()) * t.getTaxRate());
				} catch (InsufficientFundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAmountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // update balance
			} // FÅ TIL AT PASSE MED AT MAN OGSÅ EJER HVAD ENS FELTER ER VÆRD.
		} else
		{
			try {
				p.getAccount().withdraw(t.getExtraOrdinaryRate());
			} catch (InsufficientFundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAmountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
