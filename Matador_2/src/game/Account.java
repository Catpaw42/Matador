package game;

public class Account {
	private int balance;
	private final static int DEFAULT_START_BALANCE = 30000;
	
	public Account()
	{
		this(DEFAULT_START_BALANCE);
	}
	
	public Account(int startBalance)
	{
		this.balance = startBalance;
	}

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
	
	public int setBalance (int newBalance)
	{
		balance = newBalance;

		return balance;
	}
}
