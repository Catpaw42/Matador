package game;

import game.Account.IllegalAmountException;
import game.Account.InsufficientFundsException;
import gui.GUI;

public class MoveController
{
	private DiceCup diceCup;
	private GUI gui;
	private final int START_BONUS = 4000;
	private final int BAIL_PRICE = 1000;

	public MoveController(DiceCup dice)
	{
		diceCup = dice;
		gui = new GUI();
	}

	/**
	 * @param currentPlayer The active player.
	 * @return true if the player has gone broke, false otherwise.
	 */
	public boolean playerTurn(Player currentPlayer)
	{
		//Check if player is in prison
		if (currentPlayer.isInPrisson() == true)
		{
			//try to get him out (cards and cash)
			jailOptions(currentPlayer);
		}
		//then roll some dice
		diceCup.rollDice();

		//did he roll two of a kind?
		if (diceCup.isTwoOfAKind()) 
		{
			currentPlayer.setTwoOfAKindRollCount(currentPlayer.getTwoOfAKindRollCount() + 1);
		}

		//how many times did he roll two of a kind?
		if(currentPlayer.getTwoOfAKindRollCount() == 3)
		{
			currentPlayer.setPlayerPosition(11);
			currentPlayer.setInPrisson(true);
			currentPlayer.setTwoOfAKindRollCount(0);
			return false;
		}

		//Check again, did he get out?
		if (currentPlayer.isInPrisson() == false)
		{
			//If he did, move his ass.
			movePlayer(currentPlayer);
			//and then land him on the new field.
		}
		return false;	
	}

	private void jailOptions(Player currentPlayer)
	{
		{

			String[] options1 = { "Roll dices", "Pay 1000kr", "Use Chance Card"};

			int nrOfOptions = 1;
			if (currentPlayer.getAccount().getBalance() > BAIL_PRICE) // Changed from >= to >, shouldn't it be like that?
				nrOfOptions++;
			if(currentPlayer.getGetOutOfJailCards() > 0)
				nrOfOptions++;

			String[] options = new String[nrOfOptions];
			for (int i = 0; i < options.length; i++)
			{
				options[i] = options1[i];
			}

			int choise = -1;
			String message = "You're in jail. Choose between these " + options.length + " options";
			while((choise = gui.getUserButtonPressed(options, message, "Jail Options")) == -1);

			if (choise == 0 ) 
			{
				diceCup.rollDice();
				currentPlayer.setPrisonTurnCount(currentPlayer.getPrisonTurnCount() + 1);

				if (diceCup.isTwoOfAKind()) 
				{
					currentPlayer.setInPrisson(false);
				}

				if (currentPlayer.getPrisonTurnCount() == 3) {
					currentPlayer.setPrisonTurnCount(0);

					try
					{
						currentPlayer.getAccount().withdraw(1000);
						currentPlayer.setInPrisson(false);
					} 
					catch (InsufficientFundsException e)
					{
						//should not be possible
					} 
					catch (IllegalAmountException e)
					{
						System.err.println(e.getMessage());
						e.printStackTrace();
						System.exit(0);
					}
				}

			}

			if (choise == 1)
			{
				// Withdraws 1000 from currentPlayer
				try
				{
					currentPlayer.getAccount().withdraw(1000);
					currentPlayer.setInPrisson(false);
				} 
				catch (InsufficientFundsException e)
				{
					//should not be possible
				} 
				catch (IllegalAmountException e)
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
					System.exit(0);
				}
			}

			if (choise == 2)
			{
				currentPlayer.setInPrisson(false);
				currentPlayer.setGetOutOfJailCards(currentPlayer.getGetOutOfJailCards() - 1);
			}
		}
	}

	private void movePlayer(Player currentPlayer)
	{
		if (currentPlayer.getPosition() + diceCup.getSum() > 40)
		{
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + diceCup.getSum() - 40);
			try
			{
				currentPlayer.getAccount().deposit(START_BONUS);
			}
			catch (IllegalAmountException e)
			{
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(0);
			}
		}
		else
			currentPlayer.setPlayerPosition(currentPlayer.getPosition() + diceCup.getSum());
	}
}
