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
	Board board;

	public FieldController(DiceCup dice)
	{
		board = new Board(dice);
	}

	public boolean LandOnField(Player p, int fieldNr)
	{
		gui.appendTextToTextArea(board.getField(fieldNr).getMessage());

		if (board.getField(fieldNr).getClass() == Tax.class)
			return taxHandler(p, fieldNr);

		if (board.getField(fieldNr).getClass() == Ownable.class)
			return ownableHandler(p, fieldNr);

		if (board.getField(fieldNr).getClass() == Chance.class)
		{
			Chance c = (Chance) board.getField(fieldNr);
		}

		return false;
	}

	private boolean ownableHandler(Player p, int nr)
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
				// we end here if the player does'nt have enough money to pay rent

			} 
			catch (IllegalAmountException e)
			{
				// A real error happened, stop execution
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
					// we end here if the player does'nt have enough money to buy the field.
					// maybe this should be checked before the option is given.
				} 
				catch (IllegalAmountException e)
				{
					// A real error happened, stop execution
					System.err.println(e.getMessage());
					e.printStackTrace();
					System.exit(0);
				}

			} else
			{
				// if player chooses not to buy the field.
			}
		}
		return false;
	}

	private boolean taxHandler(Player p, int nr)
	{
		//Cast field to tax
		Tax t = (Tax) board.getField(nr); 
		
		// if we're at field 5 we have a choice, else we just withdraw cash.
		if (p.getPosition() == 5) 
		{
			 // array with the options thats should be on the buttons.
			String[] options = { "Pay 4000kr.", "Pay 10%" };
			// show a choice menu, with the above options.
			int i = -1;
			while((i = gui.getUserButtonPressed(options, "Choose Payment", "Tax")) == -1); 

			if (i == 0)
			{
				try
				{
					p.getAccount().withdraw(t.getRevenueRate());
				} 
				catch (InsufficientFundsException e)
				{
					return true;
				} 
				catch (IllegalAmountException e)
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
					System.exit(0);
				}
			}
			else
			{
				try
				{
					p.getAccount().withdraw((p.getTotaltAssets()) * t.getTaxRate());
				} 
				catch (InsufficientFundsException e)
				{
					return true;
				} 
				catch (IllegalAmountException e)
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
		else
		{
			try
			{
				p.getAccount().withdraw(t.getExtraOrdinaryRate());
			} 
			catch (InsufficientFundsException e)
			{
				return true;
			} 
			catch (IllegalAmountException e)
			{
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(0);
			}
		}
		return false;
	}
}
