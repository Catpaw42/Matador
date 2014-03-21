package game;

import game.Account.IllegalAmountException;
import game.Account.InsufficientFundsException;
import game.fields.Chance;
import game.fields.Ownable;
import game.fields.Street;
import game.fields.Tax;
import gui.GUI;
import chance.cards.ChanceCard;
import chance.cards.Fine;
import chance.cards.GoToJail;
import chance.cards.JailSafed;
import chance.cards.MoneyGift;
import chance.cards.MovedToField;

public class FieldController
{
	private GUI gui = new GUI();
	private Board board;
	private GameOptions gameO;

	public FieldController(DiceCup dice)
	{
		board = new Board(dice);
	}

	public boolean LandOnField(Player p, int fieldNr)
	{
		gui.appendTextToTextArea(p.getName() +  " " + board.getField(fieldNr).getMessage());

		if (board.getField(fieldNr) instanceof Tax)
			return taxHandler(p, fieldNr);

		if (board.getField(fieldNr) instanceof Ownable)
			return ownableHandler(p, fieldNr);

		if (board.getField(fieldNr) instanceof Chance)
		{
			return chanceHandler(p, fieldNr);
		}

		return false;
	}

	private boolean chanceHandler(Player p, int nr)
	{
		ChanceCard chanceCard = board.getChanceCard();

		// Controller for chance cards that sends player to jail.
		if(chanceCard instanceof GoToJail)
		{
			p.setPlayerPosition(11);
			p.setInPrisson(true);
			board.appendChanceCard(chanceCard);
		}

		// Controller for chance cards that saves player from jail.
		if(chanceCard instanceof JailSafed)
		{
			// Remember to not put card back into queue.
			p.setGetOutOfJailCards(p.getGetOutOfJailCards() + 1);
		}

		// Controller for chance cards that gives money to the player.
		if(chanceCard instanceof MoneyGift)
		{
			chanceMoneyGiftHandler(p, (MoneyGift) chanceCard);
			board.appendChanceCard(chanceCard);
		}

		// Controller for chance cards that moves player an fixed amount or to a specific field.
		if(chanceCard instanceof MovedToField)
		{
			board.appendChanceCard(chanceCard);
			return chanceMoveToFieldHandler(p, (MovedToField) chanceCard);
		}

		//Handler for chance cards that costs money.
		if(chanceCard instanceof Fine)
		{
			board.appendChanceCard(chanceCard);
			return fineHandler(p, ((Fine) chanceCard));
		}
		return false;
	}

	private boolean fineHandler(Player p, Fine chanceCard)
	{
		if(chanceCard.getCardNumber() == 13 || chanceCard.getCardNumber() == 25)
		{
			if(chanceCard.getCardNumber() == 13)
			{
				//Card number == 13

				try {
					p.getAccount().withdraw(calculateTotalFine(p,800,2300));
				} catch (InsufficientFundsException e) {
					e.printStackTrace();
					return true;
				} catch (IllegalAmountException e) {
					e.printStackTrace();
				}	
			}
			else
			{
				// Card number == 27
				try {
					p.getAccount().withdraw(calculateTotalFine(p,500,2000));
				} catch (InsufficientFundsException e) {
					e.printStackTrace();
					return true;
				} catch (IllegalAmountException e) {
					e.printStackTrace();
				}
			}
		}
		// All other Fine Chancecards
		else {
			try {
				p.getAccount().withdraw(chanceCard.getFine());
			} 
			catch (InsufficientFundsException e) 
			{
				e.printStackTrace();
				return true;
			} 

			catch (IllegalAmountException e)
			{
				e.printStackTrace();
			}	

		}
		return false;
	}

	private int calculateTotalFine(Player p, int houseFine, int hotelFine) {
		Ownable[] fields = Board.getFieldsByPlayer(p);
		int totalFine = 0;
		for (int i = 0; i < fields.length; i++)
		{
			if(fields[i] instanceof Street){
				if(((Street) fields[i]).getHouses() > 4)
					totalFine = totalFine + hotelFine;
				else
				{
					totalFine = totalFine + (((Street) fields[i]).getHouses() * houseFine);	
				}
			}


		}
		return totalFine;
	}




	private boolean chanceMoveToFieldHandler(Player p, MovedToField chanceCard)
	{
		if(chanceCard.getCardNumber() == 24)
		{
			p.setPlayerPosition(p.getPosition() - 3);
		}

		else
		{
			if(chanceCard.getCardNumber() == 15 || chanceCard.getCardNumber() == 16)
			{
				// move player to nearest Shipping and withdraw double rent if owned.¨
				// Shipping numbers are: 5, 15, 25, 35
				Ownable o = (Ownable) board.getField(p.getPosition());

				int n =-1;
				if(p.getPosition() > 35 && p.getPosition() < 5) 	{ n = 0;}
				if(p.getPosition() > 5 && p.getPosition() < 15) 	{ n = 1;}
				if(p.getPosition() > 15 && p.getPosition() < 25) 	{ n = 2;}
				if(p.getPosition() > 25 && p.getPosition() < 35)	{ n = 3;}

				p.setPlayerPosition(5+10*n); 

				// withdraws 2 times rent if the field has an owner.
				if (o.getOwner() != null)
				{
					try
					{
						p.getAccount().withdraw(o.getRent() * 2);
						o.getOwner().getAccount().deposit(o.getRent() * 2);
					} 
					catch (InsufficientFundsException e)
					{
						// we end here if the player does'nt have enough money to pay rent
						return true;
					} 
					catch (IllegalAmountException e)
					{
						// A real error happened, stop execution
						System.err.println(e.getMessage());
						e.printStackTrace();
						System.exit(0);
					}
				}
				else
				{
					p.setPlayerPosition(chanceCard.getMoveAmount());
				}
			}
		}
		return false;
	}
	private void chanceMoneyGiftHandler(Player p, MoneyGift chanceCard)
	{
		if(chanceCard.getCardNumber() == 10 || chanceCard.getCardNumber() == 27)
		{
			if(chanceCard.getCardNumber() == 10)
			{
				//Card number == 10
				try
				{
					p.getAccount().deposit(200*gameO.getPlayers().length); // Depositing 200 times the amount of players in game
				} catch (IllegalAmountException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				// Card number == 27
				if(p.getAccount().getBalance() > 15000)
				{
					// do nothing
				}
				else
				{
					try
					{
						p.getAccount().deposit(40000);
					}
					catch (IllegalAmountException e) 
					{
						e.printStackTrace();
					}
				}
			}
			// All other MoneyGift Chancecards
			try
			{
				p.getAccount().deposit(chanceCard.getReward());
			} 
			catch (IllegalAmountException e)
			{
				e.printStackTrace();
			}
		}
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
				return true;
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
					return true;
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
