package game;

public class Account {
	private int balance;
	private int startbalance = 30000;

	//-----------------------------------------------------------------
	//  Laver en forøgelse af en konto.
	//-----------------------------------------------------------------
	public int deposit (int amount)
	{
		if (amount < 0)  // deposit value is negative
		{
			System.out.println("You cannot deposit negative amounts");
		}
		else
			balance += amount;

		return balance;
	}

	//-----------------------------------------------------------------
	//  Trækker fra kontoen.
	//-----------------------------------------------------------------
	public int withdraw (int amount)
	{
		if (amount > balance)  // Sørger for den ikke trækker mere end den kan.
		{
			balance = 0;
		}
		else
			balance -= amount;

		return balance;
	}

	public int getBalance ()
	{
		return balance;
	}	
	public int setBalance (int k)
	{
		balance = k;

		return balance;
	}
}
