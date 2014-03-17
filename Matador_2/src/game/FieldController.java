package game;

import java.util.LinkedList;

import chance.cards.*;
import game.Account.IllegalAmountException;
import game.Account.InsufficientFundsException;
import game.fields.Chance;
import game.fields.Ownable;
import game.fields.Tax;
import gui.GUI;
import game.GameOptions;

public class FieldController
{
	GUI gui = new GUI();
	Board board;
	private ChanceCard chanceCard;
	private LinkedList<ChanceCard> chanceQue;
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
			chanceHandler(p, fieldNr);
		}

		return false;
	}

	private void chanceHandler(Player p, int nr) {
		// Chance c = (Chance) board.getField(nr);
		
		// for all instances DO.....................
		
		// que for chancecards, that extracts, chanceCard to work with, and puts it on the bottom of the que.
		chanceQue = new LinkedList<ChanceCard>();
		for (int i = 0; i < board.getAllChanceCards().length; i++)
		{
			chanceQue.add(board.getChanceCard(i));
		}

		chanceCard = chanceQue.remove();

		// Controller for chance cards that saids player to jail. [done]
		if(chanceCard instanceof GoToJail)
		{
			p.setPlayerPosition(11);
			p.setInPrisson(true);	
		}

		// Controller for chance cards that saves player from jail. [WIP]
		if(chanceCard instanceof JailSafed)
		{
			// Remember to not put card back into que.	
			p.setGetOutOfJailCards(p.getGetOutOfJailCards() + 1);
			
		}

		// Controller for chance cards that gives money to the player [done]
		if(chanceCard instanceof MoneyGift)
		{
			chanceMoneyGiftHandler(p);

		}

		// Controller for chance cards that moves player an fixed amount or to an specific field [done]
		if(chanceCard instanceof MovedToField)
		{
			chanceMoveToFieldHandler(p, nr);

		}

	}

	private void chanceMoveToFieldHandler(Player p, int nr) {
		MovedToField Mtf = (MovedToField) board.getChanceCard(chanceCard.getCardNumber());	
		if(chanceCard.getCardNumber() == 24) {
			p.setPlayerPosition(p.getPosition() - 3);
		}

		else {
			if(chanceCard.getCardNumber() == 15 || chanceCard.getCardNumber() == 16) {
				// move player to nearest Shipping and withdraw double rent if owned.¨
				// Shipping numbers are: 5, 15, 25, 35
				Ownable o = (Ownable) board.getField(nr);

				int n =-1;
				if(p.getPosition() > 35 && p.getPosition() < 5) 	{ n = 0;}
				if(p.getPosition() > 5 && p.getPosition() < 15) 	{ n = 1;}
				if(p.getPosition() > 15 && p.getPosition() < 25) 	{ n = 2;}
				if(p.getPosition() > 25 && p.getPosition() < 35)	{ n = 3;}

				p.setPlayerPosition(5+10*n); 
				n = 0;
				// withdraws rent the second time, if the felt is owned.
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
				else {
					p.setPlayerPosition(Mtf.getMoveAmount());
				}
			}
		}
	}
	private void chanceMoneyGiftHandler(Player p) {
		MoneyGift M = (MoneyGift) board.getChanceCard(chanceCard.getCardNumber());	
		if(chanceCard.getCardNumber() == 10 || chanceCard.getCardNumber() == 27){
			if(chanceCard.getCardNumber() == 10) {
				//Card number == 10
				try {
					p.getAccount().deposit(200*gameO.getPlayers().length); // Depositing 200 times the amount of players in game
				} catch (IllegalAmountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				// Card number == 27

				if(p.getAccount().getBalance() > 15000)
				{
					// do nothing
				}
				else {
					try {p.getAccount().deposit(40000);
					}
					catch (IllegalAmountException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			// All other MoneyGift Chancecards
			try {
				p.getAccount().deposit(M.getReward());
			} catch (IllegalAmountException e) {
				// TODO Auto-generated catch block
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
